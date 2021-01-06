package Communication;

/**
 * Informacja o wygranej któregoś z graczy.
 */
public class Winner extends GameMessage {

    /**
     * Gracz który zajął jakieś miejsce.
     */
    private final int winner;

    /**
     * Miejsce które zajął gracz.
     */
    private final int place;

    public Winner(int winner, int place) {
        super(GameMessageType.WINNER);
        this.winner = winner;
        this.place = place;
    }

    public int getWinner() {
        return winner;
    }

    public int getPlace() {
        return place;
    }
}
