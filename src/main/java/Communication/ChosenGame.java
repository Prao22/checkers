package Communication;

public class ChosenGame extends ReplayMessage {

    private final int gameId;

    public ChosenGame(int gameId) {
        super(ReplayMessageType.GAME_CHOOSE);
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
