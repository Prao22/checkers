import Client.Controller;
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

public class ControllerTest {

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
        Controller controller = new Controller();
        controller.setGuiController(guiController);
        controller.setSender(sender);

        controller.serviceMessage(new GameInformation(null));
        controller.serviceMessage(new Move(null));
    }

    @Test
    public void serviceTest() {
        Controller controller = new Controller();
        controller.setGuiController(guiController);
        controller.setSender(sender);

        controller.serviceMessage(new Parameters(new GameParameters()));
        controller.serviceMessage(new Start(1));
        assert controller.getColor() == CounterColor.BLUE;
        controller.serviceMessage(new Move(new
                Game.Move(new int[] {1, 2},new int[] {1, 2})));
        controller.serviceMessage(new Answer(true));
        controller.serviceMessage(new YourTurn());

        controller.serviceMessage(new Winner(1));
        controller.close();

        controller.showError("err");

    }
}
