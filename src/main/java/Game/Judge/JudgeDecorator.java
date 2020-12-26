package Game.Judge;

import Game.Field;
import Game.LastMove;

public abstract class JudgeDecorator extends Judge {
    protected final Judge decoratedJudge;

    public JudgeDecorator(Judge decoratedJudge) {
        super(decoratedJudge.board);
        this.decoratedJudge = decoratedJudge;
    }

    public boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove) {
        return decoratedJudge.checkIfMoveIsValid(from, to, who, lastMove);
    }
}
