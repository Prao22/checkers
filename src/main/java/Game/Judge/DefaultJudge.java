package Game.Judge;

import Game.Board;
import Game.Field;
import Utility.Log;

public class DefaultJudge extends Judge {

    public DefaultJudge(Board board) {
        super(board);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to) {
        return from != null && to != null && from.hasCounter();
    }
}
