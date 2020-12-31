package Game.Judge;

import Game.Field;
import Game.LastMove;
import Utility.Log;

import static java.lang.Math.abs;

/**
 * Sprawdzenie czy ruch jest poprawny (czy na dozwolone pole).
 */
public class CheckDefaultJump extends JudgeDecorator {
    public CheckDefaultJump(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove) {
        Log.log("jump");
        boolean ch = super.checkIfMoveIsValid(from, to, who, lastMove);
        Log.log("jump");
        if(!ch) {
            return false;
        }

        int rowDiff = from.getRow() - to.getRow();
        int colDiff = from.getCol() - to.getCol();

        if(to.isNeighbour(from)) {
            return true;
        }

        return (abs(rowDiff) == 2 && abs(colDiff) == 1) || (abs(rowDiff) == 0 && abs(colDiff) == 2);
    }
}
