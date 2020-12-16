package Client.GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class BoardField extends Ellipse2D.Double {
    private static final int DIAMETER = 60;
    private final int[] coordinates;
    private Color color;

    BoardField(int x, int y, Color c) {
        super(0, 0, DIAMETER, DIAMETER);
        coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        this.color = c;
    }

    void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.draw(this);
        g2.fill(this);
    }


    int[] getCoordinates() {
        return coordinates;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    Color getColor() {
        return this.color;
    }


    public static int getDiameter() {
        return DIAMETER;
    }
}

