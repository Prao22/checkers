package Server;

import Connection.ConnectionService;
import Server.ConsoleUI.ServerConsoleUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

//serwer tez jako singleton
public class Server implements Runnable {

    private int port;
    private int maxClients;
    private int onlineClients = 0;
    private ServerSocket serverSocket;
    private ClientHandler[] clients;
    private GameParameters gameParameters = new GameParameters();
    private boolean running = false;

    public Server() {

    }

    public Server(int port, int maxClients) throws IOException {

        this.port = port;
        serverSocket = new ServerSocket(port);

        clients = new ClientHandler[maxClients];
        this.maxClients = maxClients;

    }

    public static void main(String[] args) throws InterruptedException {


        Server server = new Server();
        ServerConsoleUI ui = new ServerConsoleUI(server);

        Thread serverThread = new Thread(server);
        serverThread.start();
        ui.loop();


    }

    public void waitForPlayers() throws IOException {
        while (onlineClients < maxClients) {
            clients[onlineClients] = new ClientHandler(new ConnectionService(serverSocket.accept()));
            Thread thread = new Thread(clients[onlineClients]);
            thread.start();
            onlineClients++;
        }
    }

    @Override
    public void run() {

        if (waitForStart() == -1) {
            return;
        }

        int returnValue = mainLoop();
    }

    private int mainLoop() {

        while (true) {

        }

        //return 0;
    }

    private int waitForStart() {
        while (!isRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -1;
            }
        }

        return 1;
    }

    public boolean changeState() {

        if (isRunning()) {
            for (ClientHandler c : clients) {
                c.sendMessage(null);
                c.disconnect();
                c = null;
            }

            clients = null;

            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException exception) {
                return false;
            }
        } else {
            try {
                serverSocket = new ServerSocket(port);
                clients = new ClientHandler[maxClients];
            } catch (IOException exception) {
                return false;
            }
        }

        running = !running;
        return true;
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
    }
}
