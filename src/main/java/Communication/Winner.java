package Communication;

/**
 * Informacja o wygranej któregoś z graczy.
 */
public class Winner extends GameMessage {

    /**
     * Gracz który wygrał gre.
     */
    private final int winner;

    public Winner(int winner) {
        super(GameMessageType.WINNER);
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }
}
