package Communication;

public abstract class GameMessage extends Message {

    private GameMessageType gameMessageType;

    GameMessage(GameMessageType gameMessageType) {
        super(MessageType.GAME);
        this.gameMessageType = gameMessageType;
    }

    public GameMessageType getGameMessageType() {
        return gameMessageType;
    }

    @Override
    public String toString() {
        return "GameMessage{" +
                "gameMessageType=" + gameMessageType +
                '}';
    }
}
