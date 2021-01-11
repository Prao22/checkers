package Server;

import Communication.*;
import Connection.ConnectionService;
import Game.GameParameters;
import Server.ConsoleUI.ServerConsoleUI;
import Server.Database.DatabaseConnector;
import Utility.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

import Game.Game;

/**
 * Klasa serwera komunikującego się z graczami.
 * Odebrane wiadomości nie dotyczące komunikacji rozdziela
 * do odpowiednich klas.
 */
public class Server implements Sender {

    /**
     * Port pod jakim serwer będzie działał.
     */
    private int port = 59001;

    /**
     * Ile aktualnie jest połączonych graczy.
     */
    private int onlineClients = 0;

    /**
     * Socket do odbierania chcących się połączyć klinetów.
     */
    private ServerSocket serverSocket;

    /**
     * Mapa połączonych klientów.
     * Klucz to identyfikator klienta,
     * a wartość to obiekt odbierający
     * wiadomości od niego.
     */
    private Map<Integer, ClientHandler> clients;

    /**
     * Parametry gry którą serwer będzie obsługiwał.
     */
    private final GameParameters gameParameters;

    /**
     * Klasa która obsługuje wiadomości dotyczące gry.
     */
    private GameService gameService = null;

    /**
     * Klasa która obsługuje tryb wysyłania powtórek gier.
     */
    private ReplayService replayService = null;

    /**
     * Czy serwer jest dostępny.
     */
    private boolean running = false;

    /**
     * Czy serwer kończy swoją prace.
     */
    private boolean end = false;

    /**
     * Lock potrzebny do komunikacji z wątkami które nasłuchują
     * na wiadomości od klientów.
     */
    private final Object lock = new Object();

    /**
     * Tryb gry czy ogladania.
     */
    private boolean isGame = true;

    public Server() {
        Log.log("Konstruktor serwera");
        gameParameters = new GameParameters();
    }

    public static void main(String[] args) {
        Server server = new Server();

        ServerConsoleUI ui = new ServerConsoleUI(server, server.getLock());
        Thread uiThread = new Thread(ui);
        uiThread.start();

        server.run();
        server.turnOff();
        System.exit(0);
    }

    /**
     * Oczekiwanie na podłączenie się wymaganej przez
     * parametry gry liczby graczy.
     *
     * @throws IOException rzucany jeśli połącznie zawiodło.
     */
    private void waitForPlayers() throws IOException {
        while (onlineClients < gameParameters.getNumberPlayers()) {
            addClient();
            gameService.addPlayer(onlineClients + 1);
            send(new Parameters(gameParameters), onlineClients + 1);
            onlineClients++;
        }

        Log.log("Wszyscy dołączyli.");
    }

    /**
     * Dodaje oczkującego gracza i tworzy nasłuchujący go wątek.
     * Wysyła nowemu graczowi informacje o rozgrywce.
     *
     * @throws IOException rzucany jeśli połączenie zawiodło.
     */
    private void addClient() throws IOException {
        clients.put(onlineClients + 1, new ClientHandler(onlineClients + 1,
                new ConnectionService(serverSocket.accept()), lock));
        clients.get(onlineClients + 1).start();
        Log.log("Dołączył klient.");
    }

    /**
     * Funkcja przygotowuje się do startu serwera i rozpoczęcia gry.
     */
    public void run() {
        if (waitForStart() != 0) {
            Log.err("Błąd podczas czekania - przerwanie");
            return;
        }

        if(isGame) {
            gameRun();
        } else {
            replayRun();
        }

    }

    private void gameRun() {
        setGameService(new GameManager(this, getGameParameters(), new Game(), DatabaseConnector.getInstance()));

        try {
            waitForPlayers();
        } catch (IOException exception) {
            Log.err("Błąd podczas oczekiwania na połączenie z graczami");
            return;
        }

        for (ClientHandler c : clients.values()) {
            c.sendMessage(new Start(c.getClientId()));
        }

        gameService.start();

        mainLoop();
    }

    private void replayRun() {
        try {
            addClient();
            onlineClients++;
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        send(new ReplayMode(), 1);
        setReplayService(new ReplayManager(this, DatabaseConnector.getInstance()));
        replayService.setClient(1);

        replayService.start();

        mainLoop();
    }


    /**
     * Główna pętla serwera, działająca podczas gry i odtwarzania powtorek.
     */
    private void mainLoop() {

        while (!isEnd() && isRunning() && onlineClients > 0) {

            waitForEvent();

            if (checkDisconnection()) {
                setEnd(true);
                Log.log("Wychodze z głównej petli!");
                break;
            }

            for (ClientHandler h : clients.values()) {
                while (h.isAnyMessage()) {
                    Message message = h.getNextMessage();
                    serviceMessage(message, h.getClientId());
                }
            }
        }

    }

    /**
     * Czeka sekunde na powiadomienie.
     * Jeśli nic się nie dzieje sam wznawia prace.
     */
    private void waitForEvent() {
        try {
            synchronized (lock) {
                lock.wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rozpoznaje typ wiadomości i oddelegowuje jej obsługę
     * odpowiednim klasom.
     *
     * @param message odebrana wiadomość
     * @param who     nadawca wiadomości
     */
    public void serviceMessage(Message message, int who) {

        if (message.getType() == MessageType.COMMUNICATION) {
            //serwer sie tym zajmuje
            serviceCommunicationMessage((CommunicationMessage) message, who);

        } else if (message.getType() == MessageType.GAME && gameService != null) {
            //przekazuje do managera gry
            gameService.serviceMessage((GameMessage) message, who);
        } else if (message.getType() == MessageType.REPLAY && replayService != null) {
            //przekazuje do managera powtorek
            replayService.serviceMessage((ReplayMessage) message, who);
        }
    }

    /**
     * Obsługa wiadomości dotyczących komunikacji między klientem a serwerem.
     *
     * @param message odebrana wiadomość
     * @param who     kto wysłał wiadomość
     */
    private void serviceCommunicationMessage(CommunicationMessage message, int who) {

        switch (message.getCommunicationMessageType()) {
            case INFORMATION: {
                Log.log(message.toString());
                break;
            }

            case END: {
                Log.log("Klient " + who + " chce wyjść!");
                handleDisconnection(who);
                break;
            }
        }
    }

    /**
     * Rozłącza się z podanym graczem.
     *
     * @param clientId identyfikator gracza z którym chcemy się rozłączyć
     */
    private void disconnectWith(int clientId) {
        clients.remove(clientId);
        onlineClients--;

        if(gameService != null) {
            gameService.removePlayer(clientId);
        }
    }

    /**
     * Obsługuje rozłączenie gracza.
     *
     * @param clientId gracz który się rozłączył
     * @return czy dostępny jest choć jeden gracz
     */
    private boolean handleDisconnection(int clientId) {
        disconnectWith(clientId);
        sendToAll(new Disconnection(clientId));
        //sendToAll(new Voting("Czy chcesz kontynuować gre?"));
        return onlineClients == 0;//resultOfVoting;
    }

    /**
     * Sprawdza czy któryś z klientów się rozłączył.
     * Jeśli tak rozpoczyna obsługę tego rozłączenia.
     *
     * @return czy dostępny jest choć jeden gracz.
     */
    private boolean checkDisconnection() {
        //noinspection ForLoopReplaceableByForEach
        for (Iterator<ClientHandler> it = clients.values().iterator(); it.hasNext(); ) {
            ClientHandler h = it.next();
            if (!h.isAlive() || h.isEnd()) {
                Log.err("Gracz " + h.getClientId() + " rozłączył się!");
                return handleDisconnection(h.getClientId());
            }
        }

        return false;
    }

    @Override
    public boolean send(Message message, int clientId) {
        if (clients == null || !clients.containsKey(clientId)) {
            return false;
        }

        ClientHandler handler = clients.get(clientId);
        return handler.sendMessage(message);
    }

    @Override
    public void sendToAll(Message message) {
        for (ClientHandler clientHandler : clients.values()) {
            clientHandler.sendMessage(message);
        }
    }


    /**
     * Oczekiwanie na znak do startu.
     *
     * @return 0 jeśli normalny start
     * < 0 jeśli zakończyć prace
     */
    private int waitForStart() {
        while (!isRunning()) {
            try {
                synchronized (lock) {
                    lock.wait(100);
                }

                if (isEnd()) {
                    return -1;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -2;
            }
        }

        return 0;
    }


    /**
     * Rozłączenie z klientami i zamknięcie wszystkich socketów.
     *
     */
    public void turnOff() {
        if (clients != null) {
            for (ClientHandler c : clients.values()) {
                c.sendMessage(null);
                c.disconnect();
            }

            clients.clear();
        }

        try {
            if(serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            running = false;
        } catch (IOException exception) {
            Log.err("Nie moge zamknąć serverSocket!");
        }
    }


    /**
     * Otwiera socket który umożliwia łączenie się klientów z serwerem.
     *
     * @return true jeśli nie było błędów
     * false jeśli wystąpił jakiś błąd
     */
    public boolean turnOn() {
        try {
            serverSocket = new ServerSocket(port);
            clients = new HashMap<>(gameParameters.getNumberPlayers());
            running = true;
            return true;

        } catch (IOException exception) {
            Log.err("Nie moge otworzyć serverSocket!");
            return false;
        }
    }

    public void changeMode() {
        isGame = !isGame;

        if(!isGame) {
            gameParameters.setNumberPlayers(1);
        } else {
            gameParameters.setNumberPlayers(2);
        }
    }

    public void setReplayService(ReplayService replayService) {
        this.replayService = replayService;
    }

    public boolean isGame() {
        return isGame;
    }

    public int getOnlineClients() {
        return onlineClients;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public GameParameters getGameParameters() {
        return gameParameters;
    }

    public int getMaxClients() {
        return gameParameters.getNumberPlayers();
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Object getLock() {
        return lock;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public String toString() {
        return "Server{" +
                "port=" + port +
                ", onlineClients=" + onlineClients +
                ", serverSocket=" + serverSocket +
                ", clients=" + Arrays.toString(clients.values().toArray()) +
                ", gameParameters=" + gameParameters +
                ", running=" + running +
                ", logFlag=" + Log.logFlag +
                '}';
    }
}
