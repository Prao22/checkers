package Server;


import Communication.GameInformation;
import Communication.GameMessage;
import Game.Game;
import Game.GameParameters;

public class GameManager implements GameService {

    private Sender sender;
    private GameParameters gameParameters;

    public GameManager() {

    }

    public GameManager(Sender sender, GameParameters gameParameters) {
        this.sender = sender;
        this.gameParameters = gameParameters;
    }

    @Override
    public int serviceMessage(GameMessage message, int playerId) {

        switch (message.getGameMessageType()) {

            case INFORMATION: {
                informationHandler((GameInformation) message, playerId);
                break;
            }

            case MOVE: {
                break;
            }
        }

        return 0;
    }

    public void informationHandler(GameInformation information, int playerId) {
        System.out.println(information.getMessage());
        sender.send(new GameInformation("Odebrlaem od ciebee " + information.getMessage()), playerId);
        sender.sendToAll(new GameInformation("Mam nowa wiadomosc od kogos z was"));
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
    }
}
