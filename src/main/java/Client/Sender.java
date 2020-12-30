package Client;

import Communication.Message;

/**
 * Interfejs umożliwiający wysyłanie wiadomości do serwera.
 */
public interface Sender {
    /**
     * Wysyła wiadomość do serwera.
     * @param message wiadomość którą chcemy wysłać
     * @return czy wysyłka zakończyła się powodzeniem.
     */
    boolean send(Message message);

    /**
     * Zwraca informacje czy jest połączony z serwerem.
     * @return czy jest połączony z serwerem
     */
    boolean isConnected();

    /**
     * Rozłacza się z serwerem powiadamiając go o tym.
     */
    void disconnect();
}
