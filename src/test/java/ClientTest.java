import Client.*;
import Communication.*;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    @Test
    public void disconnectedTest() {
        Client client = new Client();
        assert !client.isConnected();
        client.send(new Information("abc"));
        client.disconnect();
        client.setMessageController(null);
    }

    @Test
    public void connectionTest() {
        ServerHandler serverHandler = mock(ServerHandler.class);
        MessageController gameService = mock(MessageController.class);

        when(serverHandler.isAnyMessage()).thenReturn(false, true, true, true, true, true, true, false);
        when(serverHandler.getNextMessage()).thenReturn(new Information("asd"), new Start(1), new YourTurn(),
                new Move(null), new Answer(true), new Disconnection(2), null);
        when(serverHandler.isConnected()).thenReturn(true);
        when(serverHandler.isEnd()).thenReturn(false);

        Client client = new Client();
        client.setHandler(serverHandler);
        client.setMessageController(gameService);
        client.send(new Information("dsf"));
        assert client.getLock() != null;
        assert client.isConnected();
        client.mainLoop();
        client.disconnect();
    }

}
