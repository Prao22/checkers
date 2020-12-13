package Client;

import Communication.Message;

public interface Sender {
    boolean send(Message message);
    boolean isConnected();
    void disconnect();
}
