package Game;

import java.util.*;

/**
 * Obsługuje kto powinien teraz wykonać ruch.
 */
public class Turn {

    /**
     * Gracz którego jest teraz kolej.
     */
    private int currentPlayer;

    /**
     * Wszyscy gracze razem z id.
     */
    private final Map<Integer, Player> players;

    public Turn(Map<Integer, Player> players) {
        this.players = players;
        randomFirst();
    }

    /**
     * Przesuwa kolejke o 1.
     */
    public void nextTurn() {

        if (players.size() == 0) {
            currentPlayer = -1;
            return;
        }

        do {
            currentPlayer++;

            if (currentPlayer > 6) {
                currentPlayer = 0;
            }
        } while (!players.containsKey(currentPlayer));

    }

    /**
     * Sprawdza kogo jest teraz tura.
     *
     * @return id gracza który teraz powinnien wykonać ruch.
     * -1 jeśli nie ma graczy.
     */
    public int whoseTurn() {
        return currentPlayer;
    }

    /**
     * Losuje kto zaczyna grę.
     */
    private void randomFirst() {
        Random random = new Random();
        Object[] values = players.values().toArray();
        Player randomValue = (Player) values[random.nextInt(values.length)];
        currentPlayer = randomValue.getPlayerId();
    }
}
