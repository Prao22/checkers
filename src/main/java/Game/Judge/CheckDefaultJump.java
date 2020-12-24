package Game.Judge;

import Game.Field;
import Utility.Log;

import static java.lang.Math.abs;

public class CheckDefaultJump extends JudgeDecorator {
    public CheckDefaultJump(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to) {

        boolean ch = super.checkIfMoveIsValid(from, to);

        if(!ch) {
            return false;
        }

        int rowDiff = from.getRow() - to.getRow();
        int colDiff = from.getCol() - to.getCol();

        if(abs(rowDiff) + abs(colDiff) <= 2 && (abs(rowDiff) == 1 || abs(colDiff) == 1)) {
            return true;
        }

        return (abs(rowDiff) == 2 && abs(colDiff) == 1) || (abs(rowDiff) == 0 && abs(colDiff) == 2);
    }
}
