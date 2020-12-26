package Game;

public class LastMove {
    private final boolean wasJump;
    private final int who;
    private final Field from;
    private final Field to;

    public LastMove(Field from, Field to, int who, boolean jump) {
        this.who = who;
        this.wasJump = jump;
        this.from = from;
        this.to = to;
    }

    public Field getFrom() {
        return from;
    }

    public Field getTo() {
        return to;
    }

    public boolean wasJump() {
        return wasJump;
    }

    public int getWho() {
        return who;
    }
}
