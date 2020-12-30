package Client;

import Client.GUI.Board;

/**
 * Obserwator dla planszy zainteresowany które pole zostało kliknięte.
 */
public interface BoardObserver {

    /**
     * Powiadomienie że pole o podanych wspołrzędnych zostało kliknięte.
     * @param row wiersz kilkniętego pola
     * @param col kolumna klikniętego pola
     */
    void clickNotify(int row, int col);

    /**
     * Podłączenie obserwatora do planszy.
     * @param board plansza którą obserwator jest zainteresowany.
     */
    void attach(Board board);
}
