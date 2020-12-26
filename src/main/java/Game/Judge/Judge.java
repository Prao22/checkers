package Game.Judge;

import Game.Board;
import Game.Counter;
import Game.Field;
import Game.LastMove;

import java.util.List;

public abstract class Judge {

    protected Board board;
    protected static boolean wasJump = false;

    public Judge(Board board) {
        this.board = board;
    }

    public abstract boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove);

    public int getWinner() {

        int player = -1;
        boolean allOnRightPlace = true;

        for(List<Counter> counterList : board.getCounters().values()) {
            for(Counter counter : counterList) {

                player = counter.getPlayerId();

                if(!counter.onRightPlace()) {
                    allOnRightPlace = false;
                    break;
                }
            }

            if(allOnRightPlace) {
                return player;
            }

            allOnRightPlace = true;
        }

        return -1;
    }

    public boolean isWasJump() {
        return wasJump;
    }
}
