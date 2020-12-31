package Server;

import Communication.Message;

/**
 * Interfejs który umożliwia komunikacje z graczami.
 */
public interface Sender {

    /**
     * Wysyłanie wiadomości do konkretnego gracza.
     *
     * @param message  wiadomość którą chcemy wysłać.
     * @param clientId gracz do którego checmy wysłać wiadomość
     * @return czy udało się wysłać wiadomość
     */
    boolean send(Message message, int clientId);

    /**
     * Wysyłanie wiadomości do wszystkich graczy.
     *
     * @param message wiadomość którą chcemy wysłać
     */
    void sendToAll(Message message);
}
