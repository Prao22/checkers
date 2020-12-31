package Game.Judge;

import Game.Board;
import Game.Field;
import Game.LastMove;
import Utility.Log;

/**
 * Domyślny sędzia z niewielkimi zasadami.
 * Przeznaczony do dekorowania.
 * [Konkretny Komponent]
 */
public class DefaultJudge extends Judge {

    public DefaultJudge(Board board) {
        super(board);
    }

    @Override
    public boolean checkIfMoveIsValid(Field from, Field to, int who, LastMove lastMove) {
        wasJump = false;
        Log.log("daje false");
        return from != null && to != null && from.hasCounter();
    }
}
