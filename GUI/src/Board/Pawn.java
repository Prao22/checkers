package Board;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Pawn {
private Color color;
private String owner;
private Ellipse2D representation;
	Pawn(Color color, Ellipse2D representation)
	{
		this.color=color;
		this.representation=representation;
	}
}
