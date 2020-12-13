package Server;

import Communication.Message;

public interface Sender {
    boolean send(Message message, int clientId);
    void sendToAll(Message message);
}
