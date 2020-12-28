import Game.Board;
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

        assert !judge.checkIfMoveIsValid(board.getField(0,6), board.getField(0, 7), 0, null);
    }

    @Test
    public void correctJump() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);

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

        assert judge.checkIfMoveIsValid(board.getField(3,4), board.getField(4, 4), 0, null);
    }

    @Test
    public void invalidJump() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);

        assert !judge.checkIfMoveIsValid(board.getField(2,5), board.getField(6, 4), 0, null);
    }

    @Test
    public void invalidMove() {
        Board board = new Board(4, 2 ,10);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);

        assert !judge.checkIfMoveIsValid(board.getField(3,4), board.getField(4, 3), 0, null);
    }

    @Test
    public void getNoWinner() {
        Board board = new Board(1, 2 ,1);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);

        assert judge.getWinner() == -1;
    }

    @Test
    public void getWinner() {
        Board board = new Board(1, 2 ,1);
        Judge judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);

        board.moveCounter(new Move(new int[]{0, 2}, new int[]{4, 2}));

        assert judge.getWinner() == 1;
    }
}
