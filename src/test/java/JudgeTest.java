import Game.Board;
import Game.Counter;
import Game.Field;
import Game.Judge.*;
import Game.Move;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class JudgeTest {

    @Test
    public void nullMoveTest() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        assert !judge.checkIfMoveIsValid(board.getField(0,6), board.getField(0, 7), 0, null);
    }

    @Test
    public void correctJump() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        assert judge.checkIfMoveIsValid(board.getField(2,5), board.getField(4, 4), 0, null);
        assert judge.isWasJump();
    }

    @Test
    public void correctMove() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        assert judge.checkIfMoveIsValid(board.getField(3,4), board.getField(4, 4), 0, null);
    }

    @Test
    public void invalidJump() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        assert !judge.checkIfMoveIsValid(board.getField(2,5), board.getField(6, 4), 0, null);
    }

    @Test
    public void invalidMove() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        assert !judge.checkIfMoveIsValid(board.getField(3,4), board.getField(4, 3), 0, null);
    }

    @Test
    public void getNoWinner() {
        Board board = new Board(1, 2 ,1);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        assert judge.getWinner() == -1;
        assert judge.getPlace() == 0;
    }

    @Test
    public void getWinner() {
        Board board = new Board(1, 2 ,1);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        board.moveCounter(new Move(new int[]{0, 2}, new int[]{4, 2}));

        assert judge.getWinner() == 1;
        assert judge.getPlace() == 1;
    }

    @Test
    public void getTwoWinners() {
        Board board = new Board(1, 2 ,1);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
        judge = new CheckOnRightCorner(judge);

        board.moveCounter(new Move(new int[]{0, 2}, new int[]{4, 2}));
        assert judge.getWinner() == 1;
        assert judge.getPlace() == 1;
        assert judge.getWinner() == 2;
        assert judge.getPlace() == 2;
    }

    @Test
    public void escapeFromCorner() {
        Judge judge = mock(DefaultJudge.class);
        when(judge.checkIfMoveIsValid(any(Field.class), any(Field.class), anyInt(), any())).thenReturn(true);
        judge = new CheckOnRightCorner(judge);

        Field from = mock(Field.class);
        Field to = mock(Field.class);
        Counter counter = mock(Counter.class);

        when(from.getCounter()).thenReturn(counter);
        when(counter.getPlayerId()).thenReturn(1);
        when(counter.onRightPlace()).thenReturn(true);
        when(to.getDestination()).thenReturn(-1);

        assert !judge.checkIfMoveIsValid(from, to, 1, null);
    }

    @Test
    public void noEscapeFromCorner() {
        Judge judge = mock(DefaultJudge.class);
        when(judge.checkIfMoveIsValid(any(Field.class), any(Field.class), anyInt(), any())).thenReturn(true);
        judge = new CheckOnRightCorner(judge);

        Field from = mock(Field.class);
        Field to = mock(Field.class);
        Counter counter = mock(Counter.class);
        when(from.getCounter()).thenReturn(counter);
        when(counter.getPlayerId()).thenReturn(1);
        when(counter.onRightPlace()).thenReturn(false);
        when(to.getDestination()).thenReturn(-1);

        assert judge.checkIfMoveIsValid(from, to, 1, null);
    }
}
