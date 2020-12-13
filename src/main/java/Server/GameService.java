package Server;

import Communication.GameMessage;

public interface GameService {
    int serviceMessage(GameMessage message, int playerId);
}
