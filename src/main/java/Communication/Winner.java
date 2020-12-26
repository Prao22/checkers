package Communication;

public class Winner extends GameMessage {

    private final int winner;

    public Winner(int winner) {
        super(GameMessageType.WINNER);
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }
}
