package Server;


import Communication.GameMessage;
import Game.GameParameters;

public class GameManager implements GameService {

    Sender sender;

    public GameManager(Sender sender, GameParameters gameParameters) {
        this.sender = sender;

    }
    @Override
    public int serviceMessage(GameMessage message, int playerId) {
        return 0;
    }
}
