package Communication;

public class Disconnection extends CommunicationMessage {

    private final int playerId;

    public Disconnection(int playerId) {
        super(CommunicationMessageType.DISCONNECTION);
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
