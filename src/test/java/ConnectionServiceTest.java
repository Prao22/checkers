import Connection.ConnectionService;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class ConnectionServiceTest {

    @Test
    public void wrongIpTest() {
        ConnectionService service = new ConnectionService();
        if(service.connect("asd", 324)) {
            throw new AssertionError();
        }

        try {
            service = new ConnectionService(new Socket("abc", 2));
            service.sendObject(new Object());
            service.receiveObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assert !service.isConnected();
    }
}
