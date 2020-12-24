package Game.Judge;

import Game.Field;
import Utility.Log;

public class CheckOverlappingCounters extends JudgeDecorator {

    public CheckOverlappingCounters(Judge decoratedJudge) {
        super(decoratedJudge);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to) {
        boolean ch = super.checkIfMoveIsValid(from, to);

        if(!ch) {
            return false;
        }
        return !to.hasCounter();
    }
}
