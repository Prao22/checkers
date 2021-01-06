import Communication.*;
import Game.Game;
import Game.Move;
import Server.GameManager;
import Server.Sender;
import Game.GameParameters;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameManagerTest {

    @Test
    public void informationTest() {
        Sender sender = mock(Sender.class);
        GameParameters parameters = new GameParameters();
        Game game = mock(Game.class);
        GameManager manager = new GameManager(sender, parameters, game);
        manager.serviceMessage(new GameInformation("adsf"), 1);
    }

    @Test
    public void winnerTest() {
        Sender sender = mock(Sender.class);
        GameParameters parameters = new GameParameters();
        Game game = mock(Game.class);

        when(game.makeMove(any(Move.class), anyInt())).thenReturn(true);
        when(game.whoseTurn()).thenReturn(1);
        when(game.isWinner()).thenReturn(1);
        GameManager manager = new GameManager(sender, parameters, game);
        manager.serviceMessage(new Communication.Move(new Move(new int[]{4, 4}, new int[]{5, 6})), 1);
    }


    @Test
    public void moveTest() {

        Sender sender = mock(Sender.class);
        GameParameters gameParameters = new GameParameters();
        gameParameters.setNumberFields(4);

        GameManager manager = new GameManager(sender, gameParameters, new Game());
        manager.addPlayer(1);
        manager.addPlayer(2);
        manager.start();
        Move move = new Move(new int[]{4, 4}, new int[]{5, 6});
        manager.serviceMessage(new Communication.Move(move), 1);
        //verify(sender, times(3)).send(any(Message.class), anyInt());
    }

    @Test
    public void addTest() {
        Sender sender = mock(Sender.class);
        Game game = mock(Game.class);
        GameParameters gameParameters = new GameParameters();
        gameParameters.setNumberFields(4);

        GameManager manager = new GameManager(sender, gameParameters, game);
        manager.addPlayer(1);
        manager.addPlayer(2);

        verify(game, times(2)).addPlayer(anyInt());
    }

    @Test
    public void addRemoveTest() {
        Sender sender = mock(Sender.class);
        Game game = mock(Game.class);

        when(game.removePlayer(anyInt())).thenReturn(true, true, false);
        when(game.whoseTurn()).thenReturn(2, -1, -1);

        GameParameters gameParameters = new GameParameters();
        gameParameters.setNumberFields(4);

        GameManager manager = new GameManager(sender, gameParameters, game);
        manager.addPlayer(1);
        manager.addPlayer(2);
        manager.start();
        manager.removePlayer(1);
        manager.removePlayer(2);
        manager.removePlayer(3);

        verify(game, times(2)).addPlayer(anyInt());
        verify(game, times(3)).removePlayer(anyInt());
        verify(game, times(3)).whoseTurn();
    }


}
