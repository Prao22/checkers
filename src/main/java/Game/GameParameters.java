package Game;

import java.io.Serializable;

public class GameParameters implements Serializable {
    private int numberPlayers = 2;
    private int numberFields = 1;
    private int numberCounter = 1;
    private boolean blocks = true;

    public void setNumberFields(int numberFields) {
        this.numberFields = numberFields;
    }

    public void setBlocks(boolean blocks) {
        this.blocks = blocks;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public void setNumberCounter(int numberCounter) {
        this.numberCounter = numberCounter;
    }

    public int getNumberCounter() {
        return numberCounter;
    }

    public int getNumberFields() {
        return numberFields;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public boolean isBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "GameParameters{" +
                "numberPlayers=" + numberPlayers +
                ", numberFields=" + numberFields +
                ", numberCounter=" + numberCounter +
                ", blocks=" + blocks +
                '}';
    }
}
