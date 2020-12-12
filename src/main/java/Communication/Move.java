package Communication;


public class Move extends GameMessage {

    private final int[] from;
    private final int[] to;

    public Move(int[] from, int[] to) {
        super(GameMessageType.MOVE);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
