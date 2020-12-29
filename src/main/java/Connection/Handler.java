package Connection;

import Communication.Message;

/**
 * Wątek który nasłuchuje wiadomości i umożliwia ich wysyłanie
 * do klienta/serwera.
 */
public abstract class Handler extends Thread {

    /**
     * Interfejs umożliwiający odbiór i wysyłanie wiadomości.
     */
    protected IConnectionService connectionService;

    /**
     * Czy wątek zakończył prace.
     */
    protected boolean isEnd = false;

    public Handler(IConnectionService service) {
        this.connectionService = service;
    }

    /**
     * Wysłanie wiadomości do połączonego klineta/serwera.
     *
     * @param message wiadomość którą chcemy wysłać
     * @return czy wysyłka się powiodła
     */
    public boolean sendMessage(Message message) {
        return connectionService.sendObject(message);
    }

    /**
     * Rozłącza się z klientem/serwerem wysyłając mu o tym wiadomość.
     */
    public abstract void disconnect();

    /**
     * Sprawdza czy wątek zakończył prace.
     *
     * @return czy wątek zakończył juz prace.
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * Zamyka połączenie i kończy wątek.
     */
    public void closeConnection() {
        isEnd = true;
        //Log.log("Zamykam połączenie...");
        connectionService.closeConnection();
        //Log.log("Zamknięto!");
    }
}
