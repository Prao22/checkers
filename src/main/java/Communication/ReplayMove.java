package Communication;

public class ReplayMove extends ReplayMessage {

    private final Game.Move move;

    public ReplayMove(Game.Move move) {
        super(ReplayMessageType.MOVE);
        this.move = move;
    }

    public Game.Move getMove() {
        return move;
    }
}
