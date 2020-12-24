package Client.GUI;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class BoardField extends Ellipse2D.Double {
    private static final int DIAMETER = 60;
    private static final Color HIGHLIGHT_COLOR = Color.ORANGE;
    private final int[] coordinates;
    private Color color;
    private boolean highlighted = false;

    public BoardField(int x, int y, Color c) {
        super(0, 0, DIAMETER, DIAMETER);
        coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        this.color = c;
    }

    public void draw(Graphics2D g2, Color border) {
        g2.setColor(color);
        g2.fill(this);

        if (highlighted) {
            g2.setStroke(new BasicStroke((float) (DIAMETER / 10.0)));
        }

        g2.setColor(border);
        g2.draw(this);
    }

    public void draw(Graphics2D g2) {
        if (highlighted) {
            draw(g2, HIGHLIGHT_COLOR);
        } else {
            draw(g2, color);
        }
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }


    public static int getDiameter() {
        return DIAMETER;
    }
}

