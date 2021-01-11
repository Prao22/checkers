import Client.GameController;
import Client.GUIController;
import Client.Sender;
import Communication.*;
import Game.CounterColor;
import Game.GameParameters;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    @Mock
    public Sender sender;
    @Mock
    public GUIController guiController;

    @Before
    public void init() {
        sender = mock(Sender.class);
        guiController = mock(GUIController.class);

        when(sender.send(any())).thenReturn(true);
        when(sender.isConnected()).thenReturn(true);
    }

    @Test
    public void nullMessageTest() {
        GameController gameController = new GameController();
        gameController.setGuiController(guiController);
        gameController.setSender(sender);

        gameController.serviceMessage(new GameInformation(null));
        gameController.serviceMessage(new Move(null));
    }

    @Test
    public void serviceTest() {
        GameController gameController = new GameController();
        gameController.setGuiController(guiController);
        gameController.setSender(sender);

        gameController.serviceMessage(new Parameters(new GameParameters()));
        gameController.serviceMessage(new Start(1));
        assert gameController.getColor() == CounterColor.BLUE;
        gameController.serviceMessage(new Move(new
                Game.Move(new int[] {1, 2},new int[] {1, 2})));
        gameController.serviceMessage(new Answer(true));
        gameController.serviceMessage(new YourTurn());

        gameController.serviceMessage(new Winner(1, 1));
        gameController.close();

        gameController.showError("err");

    }
}
