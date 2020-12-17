import Client.Client;
import Client.Connectable;
import Client.InitConnection.ServerConnector;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerConnectorTest {

    @Test
    public void invalidPortTest() {
        Connectable client = mock(Connectable.class);
        ServerConnector connector = new ServerConnector(client);
        assert !connector.connectionRequest("1234", "abc");
    }

    @Test
    public void correctPortTest() {
        Connectable client = mock(Connectable.class);
        when(client.connect(anyString(), anyInt())).thenReturn(true);
        ServerConnector connector = new ServerConnector(client);
        assert connector.connectionRequest("127.0.0.1", "59001");
    }
}
