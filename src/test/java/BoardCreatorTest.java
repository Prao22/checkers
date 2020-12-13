import Utility.BoardCreator;
import org.junit.Test;

import java.util.Arrays;

public class BoardCreatorTest {

    final boolean[][] board4 = {
            {false, false, false, false, false, false, true, false, false, false, false, false, false},
            {false, false, false, false, false, true, true, false, false, false, false, false, false},
            {false, false, false, false, false, true, true, true, false, false, false, false, false},
            {false, false, false, false, true, true, true, true, false, false, false, false, false},
            {true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, false},
            {false, true, true, true, true, true, true, true, true, true, true, true, false},
            {false, true, true, true, true, true, true, true, true, true, true, false, false},
            {false, false, true, true, true, true, true, true, true, true, true, false, false},
            {false, true, true, true, true, true, true, true, true, true, true, false, false},
            {false, true, true, true, true, true, true, true, true, true, true, true, false},
            {true, true, true, true, true, true, true, true, true, true, true, true, false},
            {true, true, true, true, true, true, true, true, true, true, true, true, true},
            {false, false, false, false, true, true, true, true, false, false, false, false, false},
            {false, false, false, false, false, true, true, true, false, false, false, false, false},
            {false, false, false, false, false, true, true, false, false, false, false, false, false},
            {false, false, false, false, false, false, true, false, false, false, false, false, false}};
    boolean[][] board1 = {
            {false, false, true, false},
            {true, true, true, true},
            {false, true, true, true},
            {true, true, true, true},
            {false, false, true, false}
    };

    @Test
    public void creatingBoard() {
        if (!Arrays.deepEquals(BoardCreator.createBoard(4), board4)) throw new AssertionError();
        if (!Arrays.deepEquals(BoardCreator.createBoard(1), board1)) throw new AssertionError();
    }
}
