package Communication;

public class Move extends Message{

    private final int from;
    private final int to;

    public Move(int from, int to) {
        super(MessageType.MOVE);
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
