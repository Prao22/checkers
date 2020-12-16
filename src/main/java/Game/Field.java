package Game;

import java.util.Arrays;

public class Field {
    public static final int MAX_NEIGHBOURS = 6;

    protected Counter counter = null;
    protected Field[] neighbours = null;

    Field() {
    }

    public boolean hasCounter() {
        return counter != null;
    }
    public Counter getCounter() {
        return counter;
    }
    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public void setNeighbours(Field[] neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public String toString() {
        for(int i = 0; i < MAX_NEIGHBOURS; i++) {
            if(neighbours[i] != null) {
                System.out.println(i);
            }
        }

        return "";
    }

    public static class Direction {
        public static final int NE = 0;
        public static final int E = 1;
        public static final int SE = 2;
        public static final int SW = 3;
        public static final int W = 4;
        public static final int NW = 5;
    }
}
