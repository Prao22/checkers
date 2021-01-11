package Server;

import Communication.ReplayMessage;

public interface ReplayService {
    void serviceMessage(ReplayMessage message, int playerId);
    void start();
    void setClient(int id);
}
