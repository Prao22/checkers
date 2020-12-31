package Game.Judge;

import Game.Field;
import Game.LastMove;
import Utility.Log;

import static java.lang.Math.abs;

/**
 * Sprawdzenie czy ruch jest poprawny (czy pole nie zajęte czy skok ma skoczyc przez kogo)
 */
public class CheckDefaultMove extends JudgeDecorator {
    public CheckDefaultMove(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove) {
        Log.log("move");
        boolean ch = super.checkIfMoveIsValid(from, to, who, lastMove);
        Log.log("move");
        if (!ch) {
            return false;
        }

        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        if (to.isNeighbour(from)) {
            return lastMove == null || !lastMove.wasJump() || lastMove.getWho() != who;
        }

        Field buff;
        wasJump = true;

        if (lastMove != null && lastMove.getWho() == who && from.getCounter() != lastMove.getTo().getCounter()) {
            return false;
        }

        if (rowDiff != 0) {
            buff = board.getField(from.getRow() + rowDiff / 2, from.getCol() + (colDiff + (from.getRow() % 2 == 1 ? 1 : -1)) / 2);
        } else {
            buff = board.getField(from.getRow(), from.getCol() + colDiff / 2);
        }

        return buff != null && buff.hasCounter();
    }
}
