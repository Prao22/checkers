package Client;

import Communication.GameMessage;

/**
 * Interfejs który obsługuje wiadomości dotyczące gry.
 */
public interface GameService {

    /**
     * Obsługa wiadomości dotyczącej gry.
     * @param message otrzymana wiadomość.
     */
    void serviceMessage(GameMessage message);

    /**
     * Poinformowanie graczaa o błędzie (zazwyczaj rozłączenie innego gracza).
     * @param err string opisujący błąd
     */
    void showError(String err);
}
