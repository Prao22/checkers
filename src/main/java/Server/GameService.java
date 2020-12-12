package Server;

import Communication.GameMessage;
import Communication.Message;

public interface GameService {
    int serviceMessage(GameMessage message, int playerId);
}
