package Server;

import Communication.End;
import Communication.Message;
import Connection.Handler;
import Connection.IConnectionService;
import Utility.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Wątek do obsługi klienta/gracza.
 * Jako wątek czeka na wiadomość od klienta i
 * jeśli coś się pojawi to powiadamia przez lock
 * zainteresowanego.
 */
public class ClientHandler extends Handler {

    /**
     * Id gracza którego klasa obsługuje
     */
    private final int clientId;

    /**
     * Kolejka odebranych wiadomości.
     * Każdą wiadomość która obiekt odbierze
     * kolejkuje.
     */
    private final Queue<Message> receive;

    /**
     * Obiekt który służy do powiadamiania serwera
     * o oczekujących wiadomościach.
     */
    private final Object lock;


    public ClientHandler(int clientId, IConnectionService service, Object lock) {
        super(service);
        this.clientId = clientId;
        receive = new ConcurrentLinkedQueue<>();
        this.lock = lock;
    }


    /**
     * Nasłuchiwanie wiadomości od obsługiwanego klienta.
     * Jeśli klient się rozłączy wątek kończy prace.
     * Po odebraniu wiadomości klasa kolejkuje ją i powiadamia serwer.
     */
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

    /**
     * Powiadamianie serwera przez lock'a.
     */
    private void notifyServer() {
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * Pobierz następną skolejkowaną wiadomość.
     *
     * @return najdłużej czekająca wiadomość lub null jeśli nic nie ma.
     */
    public Message getNextMessage() {
        return receive.poll();
    }

    /**
     * Sprawdza czy są jakieś oczekujące wiadomości.
     *
     * @return czy są jakieś oczekujące wiadomości.
     */
    public boolean isAnyMessage() {
        return !receive.isEmpty();
    }

    /**
     * Zwraca id klienta którego klasa obsługuje.
     *
     * @return id klienta którego klasa obsługuje.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Rozłącza się z klientem wysyłając mu informacje o tym.
     */
    @Override
    public void disconnect() {
        isEnd = true;
        connectionService.sendObject(new End());
        closeConnection();
    }
}
