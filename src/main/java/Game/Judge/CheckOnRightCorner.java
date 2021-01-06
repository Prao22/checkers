package Game.Judge;

import Game.Counter;
import Game.Field;
import Game.LastMove;
import Utility.Log;

/**
 * Sprawdzanie czy gracz nie opuszcza rogu do którego ma dotrzeć.
 */
public class CheckOnRightCorner extends JudgeDecorator {
    public CheckOnRightCorner(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove) {
        Log.log("corner");
        boolean ch = super.checkIfMoveIsValid(from, to, who, lastMove);
        Log.log("corner");
        if (!ch) {
            return false;
        }

        Counter fromCounter = from.getCounter();

        return !fromCounter.onRightPlace() || to.getDestination() == fromCounter.getPlayerId();
    }
}
