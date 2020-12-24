package Game.Judge;

import Game.Field;

public abstract class JudgeDecorator extends Judge {
    protected final Judge decoratedJudge;

    public JudgeDecorator(Judge decoratedJudge) {
        super(decoratedJudge.board);
        this.decoratedJudge = decoratedJudge;
    }

    public boolean checkIfMoveIsValid(Field from, Field to) {
        return decoratedJudge.checkIfMoveIsValid(from, to);
    }
}
