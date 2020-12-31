package Game.Judge;

import Game.Field;
import Game.LastMove;

/**
 * Abstrakcyjna klasa do dekorowania sędziego dodajemy zachowanie klasy
 * checkIfMoveIsValid.
 * Kolejność dekorownia jest ważna.
 * Kolejne warunki powinny być co raz bardziej zawężające.
 */
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
