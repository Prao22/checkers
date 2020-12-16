package Board;

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
	private Ellipse2D circle;
	private final int[] Coordinates;
	private boolean isTaken;

	BoardField(int x, int y, Ellipse2D circle)
	{
		Coordinates = new int[2];
		Coordinates[0]=x;
		Coordinates[1]=y;
		this.circle=circle;
		isTaken=false;
	}
	
	int[] getCoordinates()
	{
		return Coordinates;
	}
	Ellipse2D getCircle()
	{
		return this.circle;
	}
	boolean isTaken()
	{
		return isTaken;
	}
	
	
	void setTaken(boolean bool)
	{
		this.isTaken = bool;
	}
	
	}

