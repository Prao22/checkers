import Communication.End;
import Communication.Information;
import Communication.Message;
import Communication.MessageType;
import Connection.ConnectionService;
import Connection.IConnectionService;
import Server.ClientHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class ClientHandlerTest {

    @Test
    public void receiveNotAMessage() {
        IConnectionService service = mock(ConnectionService.class);
        when(service.receiveObject()).thenReturn("abc");

        ClientHandler clientHandler = new ClientHandler(1, service, new Object());
        clientHandler.start();

        when(service.receiveObject()).thenReturn(null);
    }

    @Test
    public void unexpectedDisconnect() throws InterruptedException {
        IConnectionService service = mock(ConnectionService.class);
        when(service.receiveObject()).thenReturn(null);

        ClientHandler client = new ClientHandler(1, service, new Object());
        client.run();

        verify(service).closeConnection();
    }

    @Test
    public void lastInsertIsFirst() throws InterruptedException {

        Message last = new Information("last");
        final Message[] message = {last, new Information("AVC"), new Communication.Answer(true)};
        final int[] receive = {-1};

        IConnectionService service = mock(IConnectionService.class);
        Object lock = mock(Object.class);

        when(service.receiveObject()).thenAnswer(new Answer<Object>() {

            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                receive[0]++;
                if (receive[0] > 2) {
                    Thread.sleep(2000);
                    return null;
                }
                System.out.println("Wstawiam");
                return message[receive[0]];
            }
        });

        ClientHandler handler = new ClientHandler(1, service, lock);
        handler.start();

        Thread.sleep(1000);
        assert handler.getNextMessage().equals(last);
        assert handler.isAnyMessage();
    }

    @Test
    public void expectedDisconnect() {
        IConnectionService service = mock(IConnectionService.class);

        ClientHandler client = new ClientHandler(1, service, new Object());
        client.disconnect();
        verify(service).sendObject(any(End.class));
        verify(service).closeConnection();
    }
}
