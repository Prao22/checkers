package Game.Judge;

import Game.Board;
import Game.Counter;
import Game.Field;
import Game.LastMove;

import java.util.List;

/**
 * Klasa sędziego który sprawdza czy ktoś wygrał oraz poprawność ruchu.
 */
public abstract class Judge {

    /**
     * Plansza na której toczy się gra.
     */
    protected Board board;

    /**
     * Czy w ostatnim ruchu był skok.
     */
    protected static boolean wasJump = false;

    public Judge(Board board) {
        this.board = board;
    }

    /**
     * Funkcja sprawdzająca czy ruch jest poprawny.
     *
     * @param from     pole z którego ruch jest wykonywany
     * @param to       pole na które jest przesunięcie pionka
     * @param who      kto wykonuje ruch
     * @param lastMove ostatni wykonany ruch
     * @return czy ruch jest poprawny
     */
    public abstract boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove);

    /**
     * Zwraca zwyciężce gry.
     *
     * @return id wygranego lub -1 jeśli takiego nie ma.
     */
    public int getWinner() {

        int player = -1;
        boolean allOnRightPlace = true;

        for (List<Counter> counterList : board.getCounters().values()) {
            for (Counter counter : counterList) {

                player = counter.getPlayerId();

                if (!counter.onRightPlace()) {
                    allOnRightPlace = false;
                    break;
                }
            }

            if (allOnRightPlace) {
                return player;
            }

            allOnRightPlace = true;
        }

        return -1;
    }

    /**
     * Czy w ostatnim ruchu był skok.
     *
     * @return czy w ostatnim ruchu był skok
     */
    public boolean isWasJump() {
        return wasJump;
    }
}
