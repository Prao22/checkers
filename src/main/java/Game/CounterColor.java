package Game;

import java.awt.*;

public enum CounterColor {
    BLUE(1), GREEN(2), MAGENTA(3), RED(4), YELLOW(5), CYAN(6);

    private final int number;

    private CounterColor(int number) {
        this.number = number;
    }

    public Color getJavaColor() {
        switch (this) {
            case BLUE: return Color.BLUE;
            case RED: return Color.RED;
            case CYAN: return Color.CYAN;
            case GREEN: return Color.GREEN;
            case YELLOW: return Color.YELLOW;
            case MAGENTA: return Color.MAGENTA;
            default: return null;
        }
    }

    public static CounterColor getFromNumber(int i) {
        switch (i) {
            case 1: return BLUE;
            case 2: return GREEN;
            case 3: return MAGENTA;
            case 4: return RED;
            case 5: return YELLOW;
            case 6: return CYAN;
            default: return null;
        }
    }

}
