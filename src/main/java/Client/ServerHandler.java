package Client;

import Communication.End;
import Communication.Message;
import Connection.Handler;
import Connection.IConnectionService;
import Utility.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerHandler extends Handler {

    private final Queue<Message> receive;
    private final Object lock;

    public ServerHandler(IConnectionService service) {
        super(service);
        receive = new ConcurrentLinkedQueue<>();
        this.lock = new Object();
    }

    @Override
    public void run() {

        while (!isEnd) {
            try {
                Message message = (Message) connectionService.receiveObject();

                if (message == null) {
                    Log.err("Stracono połączenie z serwerem!");
                    closeConnection();
                    notifyClient();
                    return;
                }

                receive.add(message);
                Log.log("{CLIENT} Otrzymał wiadomość, dodano do kolejki");
                notifyClient();

            } catch (ClassCastException exception) {
                Log.err("Otrzymałem nie zrozumiała wiadomość!");
            }
        }
    }

    private void notifyClient() {
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void disconnect() {
        Log.log("Rozlaczam sie");
        connectionService.sendObject(new End());
        closeConnection();
    }

    public Message getNextMessage() {
        return receive.poll();
    }

    public Object getLock() {
        return lock;
    }

    public boolean isAnyMessage() {
        return !receive.isEmpty();
    }

    public boolean isConnected() {
        return connectionService.isConnected();
    }
}
