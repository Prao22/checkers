package Server;

import Communication.Information;
import Connection.ConnectionService;
import Game.GameParameters;
import Server.ConsoleUI.ServerConsoleUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Server {

    private int port = 59001;
    private int maxClients = 2;
    private int onlineClients = 0;
    private ServerSocket serverSocket;
    private ClientHandler[] clients;
    private GameParameters gameParameters = new GameParameters();
    private boolean running = false;
    private boolean end = false;

    public Server() {
        Log.log("Konstruktor serwera");
    }

    public static void main(String[] args) {
        Server server = new Server();
        ServerConsoleUI ui = new ServerConsoleUI(server);

        Thread uiThread = new Thread(ui);
        uiThread.start();

        server.run();
    }

    public void waitForPlayers() throws IOException {
        while (onlineClients < maxClients) {
            clients[onlineClients] = new ClientHandler(new ConnectionService(serverSocket.accept()));
            Thread thread = new Thread(clients[onlineClients]);
            thread.start();
            onlineClients++;
            Log.log("Dołączył gracz.");
        }

        Log.log("Wszyscy dołączyli.");
    }

    public void run() {
        if (waitForStart() != 0) {
            Log.err("Błąd podczas czekania - przerwanie");
            return;
        }

        try {
            waitForPlayers();
        } catch (IOException exception) {
            Log.err("Błąd podczas oczekiwanie na połączenie z graczami");
            return;
        }

        int returnValue = mainLoop();
    }

    private int mainLoop() {

        while (true) {

            try {
                Thread.sleep(3000);

                for (int i = 0; i < 2; i++) {
                    clients[i].sendMessage(new Information("hello" + i));
                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //return 0;
    }

    private int waitForStart() {
        while (!isRunning()) {
            try {
                Thread.sleep(100);

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
        for (ClientHandler c : clients) {
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
            clients = new ClientHandler[maxClients];
            running = true;
            return true;

        } catch (IOException exception) {
            Log.err("Nie moge otworzyć serverSocket!");
            return false;
        }
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
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
        return maxClients;
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

    @Override
    public String toString() {
        return "Server{" +
                "port=" + port +
                ", maxClients=" + maxClients +
                ", onlineClients=" + onlineClients +
                ", serverSocket=" + serverSocket +
                ", clients=" + Arrays.toString(clients) +
                ", gameParameters=" + gameParameters +
                ", running=" + running +
                ", logFlag=" + Log.logFlag +
                '}';
    }

    protected static class Log {

        public static boolean logFlag = true;

        public static void log(String log) {
            if (logFlag) System.out.println(log);
        }

        public static void err(String err) {
            System.out.println("[error] " + err);
        }
    }
}
