import Client.Client;
import Client.Connectable;
import Client.InitConnection.ServerConnector;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.Mockito.mock;

public class ServerConnectorTest {

    @Test
    public void invalidPortTest() {
        Connectable client = mock(Connectable.class);
        ServerConnector connector = new ServerConnector(client);
        assert !connector.connectionRequest("1234", "abc");
    }

    @Test
    public void correctPortTest() throws IOException {
        Client client = new Client();
        ServerConnector connector = new ServerConnector(client);
        assert !connector.connectionRequest("127.0.0.1", "59001");
    }
}
