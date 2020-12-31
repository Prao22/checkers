package Client;

import Communication.*;
import Game.CounterColor;
import Utility.Log;


public class Client implements Sender {

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

            while (handler.isAnyMessage()) {
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
            serviceCommunicationMessage((CommunicationMessage) message);

        } else if (message.getType() == MessageType.GAME) {
            gameService.serviceMessage((GameMessage) message);
        }
    }

    private void serviceCommunicationMessage(CommunicationMessage message) {
        switch (message.getCommunicationMessageType()) {
            case INFORMATION: {
                Log.log("Otrzymałem informacje dotyczaca komunikacji " + message.toString());
                break;
            }

            case DISCONNECTION: {
                Disconnection disconnection = (Disconnection) message;
                Log.err("Gracz o id " + disconnection.getPlayerId() + " i kolorze " +
                        CounterColor.getFromNumber(disconnection.getPlayerId()) + " się rozłączył!");
                gameService.showError("Gracz o id " + disconnection.getPlayerId() + " i kolorze " +
                        CounterColor.getFromNumber(disconnection.getPlayerId()) + " się rozłączył!");
                break;
            }

            case END: {
                Log.err("Sygnał END");
                gameService.showError("Serwer się rozłączył!");
                disconnect();
                break;
            }
        }
    }

    public void setHandler(ServerHandler handler) {
        this.handler = handler;

        if (handler.isConnected()) {
            this.handler.start();
            Log.log("Klient zostal polaczony ze serwerem");
            connected = true;
        } else {
            connected = false;
        }
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Rozłącza się z serwerem bez powiadomienia.
     */
    private void disconnectWithoutNotifying() {
        connected = false;
        handler.closeConnection();
    }

    /**
     * Zwraca informacje czy jest połączony z serwerem.
     *
     * @return czy jest połączony z serwerem
     */
    @Override
    public boolean isConnected() {
        return connected;
    }

    /**
     * Rozłacza się z serwerem powiadamiając go o tym.
     */
    @Override
    public void disconnect() {
        if (connected) {
            handler.disconnect();
            connected = false;
        }
    }

    /**
     * Wysyła wiadomość do serwera.
     *
     * @param message wiadomość którą chcemy wysłać
     * @return czy wysyłka zakończyła się powodzeniem.
     */
    @Override
    public boolean send(Message message) {
        if (connected) {
            return handler.sendMessage(message);
        } else {
            return false;
        }
    }

    public Object getLock() {
        return lock;
    }
}
