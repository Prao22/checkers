package Server;

import Communication.GameMessage;

/**
 * Interfejs który obsługuje wiadomości dotyczące gry.
 */
public interface GameService {

    /**
     * Obsługa wiadomości dotyczącej gry.
     *
     * @param message  otrzymana wiadomość.
     * @param playerId gracz który ją wysłał
     */
    void serviceMessage(GameMessage message, int playerId);

    /**
     * Dodaje gracza do rozgrywki.
     * @param playerId numer gracza
     */
    void addPlayer(int playerId);

    /**
     * Usuwa gracza z rozgrywki.
     * @param playerId numer gracza
     */
    void removePlayer(int playerId);

    /**
     * Inicjalizuje gre.
     */
    void start();
}
