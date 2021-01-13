package Game;

import java.io.Serializable;
import java.util.Arrays;

public class Move implements Serializable {

    public static final int ROW = 0;
    public static final int COLUMN = 1;

    private final int[] from;
    private final int[] to;

    public Move(int[] from, int[] to) {
        this.from = from;
        this.to = to;
    }

    public int[] getFrom() {
        return from;
    }

    public int[] getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + Arrays.toString(from) +
                ", to=" + Arrays.toString(to) +
                '}';
    }
}
