package Board;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Circle2D {

	public Color color;
	public int X;
	public int Y;
	public int size;
	public Ellipse2D circle;
	Circle2D(int x, int y,Color color, int size)
	{
		circle = new Ellipse2D.Double(100,100,size,size);
		X=x;
		Y=y;
		this.color=color;
	}
	Ellipse2D getCircle()
	{
		return this.circle;
	}
}
