package Game;

import java.io.Serializable;

/**
 * Parametry gry które można ustawiać z poziomu serwera.
 * Są wysyłane do klienta po połączeniu.
 */
public class GameParameters implements Serializable {

    /**
     * Liczba graczy biorących udział w grze.
     * Poprawmymi wartościami są 2, 3, 4, 6
     */
    private int numberPlayers = 6;

    /**
     * Długość ramienia planszy w polach. (Plansza to gwiazda)
     * Wartość >= 1
     */
    private int numberFields = 1;

    /**
     * Liczba pionków dla każdego gracza.
     * Wartość powinna się znajdować miedzy 1 a (numberFields + 1) * numberFields) / 2,
     * gdzie ta druga wartość to wzór na liczbe pól w ramieniu gwiazdy.
     */
    private int numberCounter = 1;

    /**
     * Czy blokownie w ramieniu gwiazdy jest dozwolone.
     * <<<Nie zaimplementowano>>>
     */
    private boolean blocks = true;

    public void setNumberFields(int numberFields) {
        this.numberFields = numberFields;
    }

    public void setBlocks(boolean blocks) {
        this.blocks = blocks;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public void setNumberCounter(int numberCounter) {
        this.numberCounter = numberCounter;
    }

    public int getNumberCounter() {
        return numberCounter;
    }

    public int getNumberFields() {
        return numberFields;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public boolean isBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "GameParameters{" +
                "numberPlayers=" + numberPlayers +
                ", numberFields=" + numberFields +
                ", numberCounter=" + numberCounter +
                ", blocks=" + blocks +
                '}';
    }
}
