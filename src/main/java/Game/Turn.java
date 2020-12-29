package Game;

import java.util.*;

public class Turn {

    private int currentPlayer;
    private final Map<Integer, Player> players;

    public Turn(Map<Integer, Player> players) {
        this.players = players;
        randomFirst();
    }

    public int nextTurn() {

        if(players.size() == 0) {
            currentPlayer = -1;
            return -1;
        }

        do {
            currentPlayer++;

            if(currentPlayer > 6) {
                currentPlayer = 0;
            }
        } while (!players.containsKey(currentPlayer));

        return currentPlayer;
    }

    public int whoseTurn() {
        return currentPlayer;
    }

    private void randomFirst() {
        Random random = new Random();
        Object[] values = players.values().toArray();
        Player randomValue = (Player) values[random.nextInt(values.length)];
        currentPlayer = randomValue.getPlayerId();
    }
}
