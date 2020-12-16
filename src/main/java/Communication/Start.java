package Communication;

public class Start extends GameMessage {

    private final int playerId;

    public Start(int playerId) {
        super(GameMessageType.START);
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
