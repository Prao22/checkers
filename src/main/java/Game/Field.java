package Game;

import java.util.Arrays;

public class Field {
    private final int MAX_NEIGHBOURS = 6;
    protected Counter counter = null;
    protected Field[] neighbours = null;

    public enum Type {
        REAL, FAKE;
    }

    protected Type type;

    Field(Type type) {
        this.type = type;

        if (type == Type.REAL) {
            neighbours = new Field[MAX_NEIGHBOURS];
        }
    }

    @Override
    public String toString() {
        return type == Type.REAL ? "*" : "-";
//        return "Field{" +
//                "MAX_NEIGHBOURS=" + MAX_NEIGHBOURS +
//                ", counter=" + counter +
//                ", neighbours=" + Arrays.toString(neighbours) +
//                ", type=" + type +
//                '}';
    }
}
