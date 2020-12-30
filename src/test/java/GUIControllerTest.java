import Client.GUI.BoardField;
import Client.GUI.GameWindow;
import Client.GUIController;
import Client.GameController;
import Client.GUI.*;
import Game.CounterColor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GUIControllerTest {

    @Mock
    public GameController gameController;
    @Mock
    public GameWindow window;
    @Mock
    public Board board;


    @Before
    public void init() {
        window = mock(GameWindow.class);
        gameController = mock(GameController.class);
        board = mock(Board.class);

        when(gameController.getColor()).thenReturn(CounterColor.BLUE);
        when(board.getColorOfField(anyInt(), anyInt())).thenReturn(CounterColor.BLUE.getJavaColor());
    }

    @Test
    public void clickTest() {
        GUIController controller = new GUIController(gameController);
        controller.setWindow(window);
        controller.setBoardParameters(1, 2, 3);
        controller.attach(board);
        controller.resetClicks();
        controller.updateTurnInfo(true);
        controller.clickNotify(1, 1);
        controller.clickNotify(1, 2);
        controller.clickNotify(1, 3);
    }

    @Test
    public void updateInfoTest() {
        GUIController controller = new GUIController(gameController);
        controller.setWindow(window);

        controller.updateTurnInfo(true);
        controller.updateFooter("aa");
        controller.updateFooter(1, 1,"a");
        controller.updatePlayerInfo(1);
    }

    @Test
    public void miscTest() {
        GUIController controller = new GUIController(gameController);
        controller.setWindow(window);
        controller.close();
        controller.buttonClicked();
        controller.repack();
    }
}
