import Game.*;
import org.junit.Test;

public class GameTest {

    @Test
    public void initTest() {
        Game game = new Game();
        GameParameters parameters = new GameParameters();
        parameters.setNumberFields(4);
        parameters.setNumberCounter(10);
        parameters.setNumberPlayers(2);
        parameters.setBlocks(true);

        game.addPlayer(1);
        game.addPlayer(2);
        game.init(parameters);
        game.removePlayer(1);
        game.removePlayer(2);
    }

    @Test
    public void moveTest() {
        Game game = new Game();
        GameParameters parameters = new GameParameters();
        parameters.setNumberFields(1);
        parameters.setNumberCounter(1);
        parameters.setNumberPlayers(2);
        parameters.setBlocks(true);

        game.addPlayer(1);
        game.addPlayer(2);
        game.init(parameters);

        game.makeMove(null, 1);
        game.makeMove(new Move(new int[] {1, 2},new int[] {1, 2}), 1);
        game.makeMove(new Move(new int[] {0, 2},new int[] {1, 2}), 1);
        game.makeMove(new Move(new int[] {2, 2},new int[] {2, 2}), 1);
    }

    @Test
    public void lastTest() {
        LastMove move = new LastMove(new Field(Field.Direction.E,
                Field.Direction.NW - Field.Direction.W), new Field(Field.Direction.SW, 1), 1, true);                                                                                                                                                     Field.Direction a = new Field.Direction();

        Game game = new Game();
        GameParameters parameters = new GameParameters();
        parameters.setNumberFields(1);
        parameters.setNumberCounter(1);
        parameters.setNumberPlayers(2);
        parameters.setBlocks(true);

        boolean[][] board1 = {
                {false, false, true, false},
                {true, true, true, true},
                {false, true, true, true},
                {true, true, true, true},
                {false, false, true, false}};
        assert board1[move.getFrom().getRow()][move.getFrom().getCol()];
        assert board1[move.getTo().getRow()][move.getTo().getCol()];
        assert move.wasJump();
        assert move.getWho() == 1;
    }
}
