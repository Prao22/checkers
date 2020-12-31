package Game.Judge;

import Game.Field;
import Game.LastMove;
import Utility.Log;

/**
 * Sprawdzanie czy pionki na siebie nie beda nachodzic.
 */
public class CheckOverlappingCounters extends JudgeDecorator {

    public CheckOverlappingCounters(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove) {
        Log.log("overlaping");

        boolean ch = super.checkIfMoveIsValid(from, to, who, lastMove);

        Log.log("overlaping");

        if(!ch) {
            return false;
        }


        return !to.hasCounter();
    }
}
