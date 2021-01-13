package Game;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * Parametry gry które można ustawiać z poziomu serwera.
 * Są wysyłane do klienta po połączeniu.
 */
@Embeddable
public class GameParameters implements Serializable {

    /**
     * Liczba graczy biorących udział w grze.
     * Poprawmymi wartościami są 2, 3, 4, 6
     */
    @Column(name = "players")
    private int numberPlayers = 2;

    /**
     * Długość ramienia planszy w polach. (Plansza to gwiazda)
     * Wartość >= 1
     */
    @Column(name = "fields")
    private int numberFields = 4;

    /**
     * Liczba pionków dla każdego gracza.
     * Wartość powinna się znajdować miedzy 1 a (numberFields + 1) * numberFields) / 2,
     * gdzie ta druga wartość to wzór na liczbe pól w ramieniu gwiazdy.
     */
    @Column(name = "counters")
    private int numberCounter = 10;

    /**
     * Czy blokownie w ramieniu gwiazdy jest dozwolone.
     * Nie zaimplementowano
     */
    @Transient
    private boolean blocks = true;

    public GameParameters () {

    }

    public GameParameters(int numberPlayers, int numberFields, int numberCounter) {
        this.numberPlayers = numberPlayers;
        this.numberFields = numberFields;
        this.numberCounter = numberCounter;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameParameters that = (GameParameters) o;
        return numberPlayers == that.numberPlayers && numberFields == that.numberFields && numberCounter == that.numberCounter && blocks == that.blocks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberPlayers, numberFields, numberCounter, blocks);
    }
}
