import Communication.Information;
import Communication.Message;
import Communication.Move;
import Server.GameService;
import Server.Server;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ServerTest {

    @Test
    public void serviceMessageTest() {
        GameService gameService = mock(GameService.class);
        Server server = new Server();
        server.setGameService(gameService);

        server.serviceMessage(new Move(new Game.Move(new int[] {1, 1}, new int[] {1, 1})), 1);
        verify(gameService).serviceMessage(any(), anyInt());
        server.serviceMessage(new Information("abc"), 1);
        verify(gameService, times(1)).serviceMessage(any(), anyInt());
    }
    @Test
    public void sendTest() {
        GameService gameService = mock(GameService.class);
        Server server = new Server();
        server.setGameService(gameService);
        if(server.changeState()) {
            assert server.isRunning();
            assert !server.isEnd();
            server.send(new Information("abc"), 1);
            server.sendToAll(new Information("abc"));
            server.turnOff();
        }
        server.setPort(1234);
        server.setEnd(true);
    }

    @Test
    public void initTest() {
        GameService gameService = mock(GameService.class);
        Server server = new Server();
        server.setGameService(gameService);
        server.setPort(57353);
        if(server.turnOn()) {
            assert server.isRunning();
            assert !server.isEnd();
        }
        assert server.getLock() != null;
        assert server.getGameParameters() != null;
        assert server.getMaxClients() == server.getGameParameters().getNumberPlayers();
        assert server.getOnlineClients() == 0;
    }

}
