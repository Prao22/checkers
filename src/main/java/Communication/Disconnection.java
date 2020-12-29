package Communication;

/**
 * Informacja o rozłączeniu się gracza.
 * Id gracza jest w wiadomości.
 */
public class Disconnection extends CommunicationMessage {

    /**
     * Id gracza który się rozłączył.
     */
    private final int playerId;

    public Disconnection(int playerId) {
        super(CommunicationMessageType.DISCONNECTION);
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
