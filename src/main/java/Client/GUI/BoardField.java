package Client.GUI;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Klasa pola planszy.
 *
 */
public class BoardField extends Ellipse2D.Double {
	/**
	 * średnica pola planszy.
	 */
    private static final int DIAMETER = 60;
    
    /**
     * Kolor na jaki pole zostanie podświetlone.
     */
    private static final Color HIGHLIGHT_COLOR = Color.ORANGE;
    
    /**
     * Koordynaty pola.
     */
    private final int[] coordinates;
    
    /**
     * Kolor pola.
     */
    private Color color;
    
    /**
     * Informacja czy pole jest podświetlone, czy nie.
     */
    private boolean highlighted = false;

    public BoardField(int x, int y, Color c) {
        super(0, 0, DIAMETER, DIAMETER);
        coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        this.color = c;
    }

    /**
     * Rysuje pole.
     * @param g2 kontekst graficzny w którym należy malować.
     * @param border kolor obramowania pola.
     */
    public void draw(Graphics2D g2, Color border) {
        g2.setColor(color);
        g2.fill(this);

        if (highlighted) {
            g2.setStroke(new BasicStroke((float) (DIAMETER / 10.0)));
        }

        g2.setColor(border);
        g2.draw(this);
    }

    /**
     * Rysuje pole.
     * @param g2 kontekst graficzny w którym należy malować.
     */
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

