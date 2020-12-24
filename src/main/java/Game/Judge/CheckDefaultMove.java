package Game.Judge;

import Game.Field;
import Utility.Log;

import static java.lang.Math.abs;

public class CheckDefaultMove extends JudgeDecorator {
    public CheckDefaultMove(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to) {
        boolean ch = super.checkIfMoveIsValid(from, to);

        if (!ch) {
            return false;
        }

        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        if (to.isNeighbour(from)) {
            return true;
        }

        Field buff;

        if (rowDiff != 0) {
            buff = board.getField(from.getRow() + rowDiff / 2, from.getCol() + (colDiff + (from.getRow() % 2 == 1 ? 1 : -1)) / 2);
        } else {
            buff = board.getField(from.getRow(), from.getCol() + colDiff / 2);
        }

        return buff != null && buff.hasCounter();
    }
}
