package Client;

import Communication.End;
import Communication.Information;
import Communication.Message;
import Communication.MessageType;
import Connection.Handler;
import Connection.IConnectionService;
import Server.Server;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public class ServerHandler extends Handler {

    private final Queue<Message> receive;

    public ServerHandler(IConnectionService service) {
        super(service);
        receive = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {

        while (!isEnd) {
            try {
                Message message = (Message) connectionService.receiveObject();
                if (message == null) {
                    System.out.println("Stracono połączenie z serwerem!");
                    isEnd = true;
                    closeConnection();
                    return;
                }

                receive.add(message);
                System.out.println("{CLIENT} Otrzymał wiadomość, dodano do kolejki");

            } catch (ClassCastException exception) {
                // nie wiadomo co zostalo wyslane
                System.out.println("Otrzymałem nie zrozumiała wiadomość!");
            }
        }
    }

    @Override
    public void disconnect() {
        isEnd = true;
        connectionService.sendObject(new End());
        closeConnection();
    }
}
