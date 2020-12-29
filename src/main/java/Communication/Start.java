package Communication;

/**
 * Rozpoczyna rozgrywkę oraz nadaje każdemu
 * id (kolor) tak aby każdy wiedział jakimi pionkami gra.
 */
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
