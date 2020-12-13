package Communication;

public class GameInformation extends GameMessage {

    private final String message;

    public GameInformation(String message) {
        super(GameMessageType.INFORMATION);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
