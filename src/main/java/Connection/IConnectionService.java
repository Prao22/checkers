package Connection;

/**
 * Podstawowy interfejs przy komunikacji.
 */
public interface IConnectionService extends Connectable {

    /**
     * Wysłanie obiektu do połączonego z nami socketu.
     *
     * @param object obiekt który chcemy wysłać.
     * @return czy wysyłka się powiodła
     */
    boolean sendObject(Object object);

    /**
     * Odbiór obiektu wysłanego przez połączonego
     * z nami socketa. Gdy nie ma żadnego obieku w buforze
     * funkcja blokuje wątek dopóki żaden się nie pojawi.
     *
     * @return obiekt który został wysłany
     */
    Object receiveObject();

    /**
     * Zamyka połączenie z połączonym z nami socketem.
     */
    void closeConnection();

    /**
     * Sprawdza czy jest połączony.
     *
     * @return czy jest połączony
     */
    boolean isConnected();

}
