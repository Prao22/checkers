package Communication;

/**
 * Wiadomość dotycząca gry.
 */
public abstract class GameMessage extends Message {

    /**
     * Szczegółowa informacja czego dotyczy wiadomość.
     */
    private final GameMessageType gameMessageType;

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
