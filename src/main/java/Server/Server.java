package Server;

import Communication.*;
import Connection.ConnectionService;
import Game.GameParameters;
import Server.ConsoleUI.ServerConsoleUI;
import Utility.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

public class Server implements Sender {

    private int port = 59001;
    private int onlineClients = 0;
    private ServerSocket serverSocket;
    private Map<Integer, ClientHandler> clients;
    private final GameParameters gameParameters;
    private GameService gameService;
    private boolean running = false;
    private boolean end = false;
    private final Object lock = new Object();

    public Server() {
        Log.log("Konstruktor serwera");
        gameParameters = new GameParameters();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.setGameService(new GameManager(server, server.getGameParameters()));

        ServerConsoleUI ui = new ServerConsoleUI(server, server.getLock());
        Thread uiThread = new Thread(ui);
        uiThread.start();

        server.run();
    }

    public void waitForPlayers() throws IOException {
        while (onlineClients < gameParameters.getNumberPlayers()) {
            addClient();
            onlineClients++;
        }

        Log.log("Wszyscy dołączyli.");
    }

    private void addClient() throws IOException {
        clients.put(onlineClients + 1, new ClientHandler(onlineClients + 1,
                new ConnectionService(serverSocket.accept()), lock));

        //sendToAll(new GameInformation("Dołączył kolejny gracz aktualny stan: " + (onlineClients + 1) + "/" + gameParameters.getNumberPlayers()));
        clients.get(onlineClients + 1).start();
        gameService.addPlayer(onlineClients + 1);
        send(new Parameters(gameParameters), onlineClients + 1);
        Log.log("Dołączył gracz.");
    }

    public void run() {
        if (waitForStart() != 0) {
            Log.err("Błąd podczas czekania - przerwanie");
            return;
        }

        try {
            waitForPlayers();
        } catch (IOException exception) {
            Log.err("Błąd podczas oczekiwania na połączenie z graczami");
            return;
        }

        int returnValue = mainLoop();
    }

    private int mainLoop() {

        for(ClientHandler c : clients.values()) {
            c.sendMessage(new Start(c.getClientId()));
        }

        gameService.start();

        while (!isEnd() && isRunning() && onlineClients > 0 ) {

            Log.log("Klinetow: " + onlineClients);
            waitForEvent();

            if (checkDisconnection()) {
                setEnd(true);
                break;
                //trzeba wyjsc bylo disconnect i glosowanie za wyjsciem z gry
            }

            for (ClientHandler h : clients.values()) {
                if (h.isAnyMessage()) {
                    Message message = h.getNextMessage();
                    serviceMessage(message, h.getClientId());
                }
            }
        }

        return 0;
    }

    private void waitForEvent() {
        try {
            synchronized (lock) {
                lock.wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean serviceMessage(Message message, int who) {
        if (message == null) {
            return true;
        }

        if (message.getType() == MessageType.COMMUNICATION) {
            //serwer sie tym zajmuje
            serviceCommunicationMessage((CommunicationMessage) message, who);

        } else if (message.getType() == MessageType.GAME) {
            //przekazuje do managera gry
            gameService.serviceMessage((GameMessage) message, who);
        }

        return true;
    }

    public boolean serviceCommunicationMessage(CommunicationMessage message, int who) {

        switch (message.getCommunicationMessageType()) {
            case INFORMATION: {
                Log.log(((Information) message).toString());
                break;
            }

            case END: {
                Log.log("Klient " + who + " chce wyjść!");
                handleDisconnection(who);
                return true;
            }
        }


        return false;
    }

    public void disconnectWith(int clientId) {
        clients.remove(clientId);
        onlineClients--;
        // daj info do game managera
    }

    public boolean handleDisconnection(int clientId) {
        disconnectWith(clientId);
        sendToAll(new Disconnection(clientId));
        sendToAll(new Voting("Czy chcesz kontynuować gre?"));
        return onlineClients == 0;//resultOfVoting;
    }

    public boolean checkDisconnection() {
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
        if (!clients.containsKey(clientId)) {
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

    public boolean changeState() {

        if (isRunning()) {
            return turnOff();
        } else {
            return turnOn();
        }
    }

    public boolean turnOff() {
        for (ClientHandler c : clients.values()) {
            c.sendMessage(null);
            c.disconnect();
            c = null;
        }

        clients = null;

        try {
            serverSocket.close();
            serverSocket = null;
            running = false;
            return true;
        } catch (IOException exception) {
            Log.err("Nie moge zamknąć serverSocket!");
            return false;
        }
    }


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

    public void setLogFlag(boolean logFlag) {
        Log.logFlag = logFlag;
    }

    public boolean getLogFlag() {
        return Log.logFlag;
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
