package Communication;


public class Move extends GameMessage {

    private final Game.Move move;

    public Move(Game.Move move) {
        super(GameMessageType.MOVE);
        this.move = move;
    }

    public Game.Move getMove() {
        return move;
    }
}
