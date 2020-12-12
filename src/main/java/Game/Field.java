package Game;

import java.util.Arrays;

public class Field {
    private final int MAX_NEIGHBOURS = 6;
    protected Counter counter = null;
    protected Field[] neighbours = null;

    Field() {
        neighbours = new Field[MAX_NEIGHBOURS];
    }

}
