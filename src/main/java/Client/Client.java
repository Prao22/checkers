package Client;

import Communication.*;
import Connection.ConnectionService;
import Utility.Log;

import java.io.IOException;
import java.net.Socket;


public class Client implements Sender, Connectable {

    private ServerHandler handler;
    private boolean connected = false;
    private final Object lock = new Object();
    private GameService gameService;

    public Client() {
    }

    public void mainLoop() {

        while (connected) {
            waitForEvent();
            checkDisconnection();

            if (handler.isAnyMessage()) {
                serviceMessage(handler.getNextMessage());
            }
        }
    }

    private void waitForEvent() {
        synchronized (lock) {
            try {
                lock.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkDisconnection() {
        if (!handler.isAlive() || handler.isEnd()) {
            Log.err("Disconnection!");
            gameService.showError("Utracono połączenie z serwerem!");
            disconnectWithoutNotifying();
        }
    }

    private void serviceMessage(Message message) {
        if (message == null) {
            return;
        }

        if (message.getType() == MessageType.COMMUNICATION) {
            //klient sie tym zajmuje
            serviceCommunicationMessage((CommunicationMessage) message);

        } else if (message.getType() == MessageType.GAME) {
            //przekazuje do managera gry
            gameService.serviceMessage((GameMessage) message);
        }
    }

    private void serviceCommunicationMessage(CommunicationMessage message) {
        switch (message.getCommunicationMessageType()) {
            case INFORMATION: {

            }

            case END: {
                break;
            }
        }
    }

    public boolean setHandler(String ip, int port) {
        try {
            this.handler = new ServerHandler(new ConnectionService(new Socket(ip, port)), lock);
            this.handler.start();
            Log.log("Klient zostal polaczony ze serwerem");
            connected = true;
            return true;
        } catch (IOException exception) {
            Log.err("Nie udalo sie nawiazac polaczenia z serwerem!");
            connected = false;
            return false;
        }
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    private void disconnectWithoutNotifying() {
        connected = false;
        handler.closeConnection();
    }

    @Override
    public boolean connect(String ip, int port) {
        return setHandler(ip, port);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void disconnect() {
        if(connected) {
            handler.disconnect();
            connected = false;
        }
    }

    @Override
    public boolean send(Message message) {
        if (connected) {
            return handler.sendMessage(message);
        } else {
            return false;
        }
    }
}
