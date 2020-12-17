import Communication.Answer;
import Communication.Move;
import Communication.Start;
import Communication.YourTurn;
import Server.GameManager;
import Server.Sender;
import Game.GameParameters;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameManagerTest {

    @Test
    public void moveTest() {

        Sender sender = mock(Sender.class);
        GameParameters gameParameters = new GameParameters();

        GameManager manager = new GameManager(sender, gameParameters);
        manager.addPlayer(1);
        manager.addPlayer(2);
        manager.start();
        Game.Move move = new Game.Move(new int[]{4, 0}, new int[]{5, 0});
        manager.serviceMessage(new Communication.Move(move), 1);
        verify(sender).send(any(Answer.class), anyInt());
        verify(sender, times(2)).send(any(YourTurn.class), anyInt());
    }


}
