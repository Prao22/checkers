package Game.Judge;

import Game.Board;
import Game.Field;

public abstract class Judge {

    protected Board board;

    public Judge(Board board) {
        this.board = board;
    }

    public abstract boolean checkIfMoveIsValid(Field from, Field to);
}
