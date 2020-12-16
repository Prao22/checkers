package Board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JOptionPane;



public class BoardField{
	private final Ellipse2D circle;
	private final int[] Coordinates;
	private Color color;
	BoardField(int x, int y, Ellipse2D circle,Color c)
	{
		Coordinates = new int[2];
		Coordinates[0]=x;
		Coordinates[1]=y;
		this.circle=circle;
		this.color = c;
	}
	int[] getCoordinates()
	{
		return Coordinates;
	}
	
	
	Ellipse2D getCircle()
	{
		return this.circle;
	}
	
	
	
	Color getColor()
	{
		return this.color;
	}
	
	
	void setColor(Color c)
	{
		this.color =c;
	}
	
}

