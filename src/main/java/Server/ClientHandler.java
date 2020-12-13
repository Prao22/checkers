package Server;

import Communication.End;
import Communication.Message;
import Connection.Handler;
import Connection.IConnectionService;
import Utility.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientHandler extends Handler {

    private final int clientId;
    private final Queue<Message> receive;
    private final Object lock;


    public ClientHandler(int clientId, IConnectionService service, Object lock) {
        super(service);
        this.clientId = clientId;
        receive = new ConcurrentLinkedQueue<>();
        this.lock = lock;
    }


    @Override
    public void run() {

        while (!isEnd) {
            try {
                Message message = (Message) connectionService.receiveObject();
                if (message == null) {
                    Log.err("{CLIENT " + clientId + "} Rozłączył się!");
                    isEnd = true;
                    closeConnection();
                    return;
                }


                receive.add(message);

                Log.log("{CLIENT " + clientId + "} Otrzymał wiadomość, dodano do kolejki");
                notifyServer();

            } catch (ClassCastException exception) {
                // nie wiadomo co zostalo wyslane
                Log.err("{CLIENT " + clientId + "} Otrzymał nie zrozumiała wiadomość!");
            }
        }
    }

    private void notifyServer() {
        synchronized (lock) {
            lock.notify();
        }
    }

    public Message getNextMessage() {
        return receive.poll();
    }

    public boolean isAnyMessage() {
        return !receive.isEmpty();
    }

    public int getClientId() {
        return clientId;
    }

    public void disconnect() {
        isEnd = true;
        connectionService.sendObject(new End());
        closeConnection();
    }
}
