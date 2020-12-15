package Board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Whiteboard2 extends JComponent{
	public static int CenterX=900;
	public static int CenterY=400;
	Whiteboard2(int x, int y)
	{
		CenterX=x;
		CenterY=y;
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		/*Ellipse2D circle4 = new Ellipse2D.Double(780,320,340,340);
		g2.draw(circle4);
		g2.fill(circle4);*/
		int diameter = 40;
		int X = 900;
		int Y = 400;
		paintBattlefield(g,diameter,X,Y);
		paintSideA(g,diameter,X,Y);
		paintSideB(g,diameter,X,Y);
		paintSideC(g,diameter,X,Y);
		paintSideD(g,diameter,X,Y);
		paintSideE(g,diameter,X,Y);
		paintSideF(g,diameter,X,Y);
		
		
	}
	public void paintBattlefield(Graphics g,int diameter,int X,int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GRAY);
		for(int i=0;i<5;i++)
		{
			if(i<5)
			{
				for(int j=0;j<5+i;j++)
				{
					Ellipse2D circle = new Ellipse2D.Double(X+j*diameter-i*(diameter/2),Y+i*diameter,diameter,diameter);
					g2.draw(circle);
					g2.fill(circle);
				}
			}
		}
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<5+i;j++)
			{
				Ellipse2D circle = new Ellipse2D.Double(X+j*diameter-i*(diameter/2),Y+8*diameter-i*diameter,diameter,diameter);
				//Pole pole = new Pole (x,y,diameter,numer);
				//tablica.add(pole);
				//tabloca - > na sertwer
				g2.draw(circle);
				g2.fill(circle);
			}
		}
		
	}
	public void paintSideA(Graphics g,int diameter,int X, int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.RED);
		for(int i = 0;i<4;i++)
		{
			for(int j=0;j<1+i;j++)
			{
			Ellipse2D circle2 = new Ellipse2D.Double(X+2*diameter-(diameter/2)*i+j*diameter,Y-4*diameter+i*diameter,diameter,diameter);
			g2.draw(circle2);
			g2.fill(circle2);
			}
		}
	}
	public void paintSideB(Graphics g,int diameter,int X, int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.BLUE);
		for(int i = 0;i<4;i++)
		{
			for(int j=0;j<4-i;j++)
			{
				Ellipse2D circle3 = new Ellipse2D.Double(X-4*diameter+j*diameter+i*(diameter/2),Y+diameter*i,diameter,diameter);
				g2.draw(circle3);
				g2.fill(circle3);
			}
		}
		
	}
	public void paintSideC(Graphics g,int diameter,int X, int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.YELLOW);
		for(int i = 0;i<4;i++)
		{
			for(int j=0;j<4-(3-i);j++)
			{
				Ellipse2D circle = new Ellipse2D.Double(X-2.5*diameter-(diameter/2)*i+j*diameter,Y+5*diameter+i*diameter,diameter,diameter);
				g2.draw(circle);
				g2.fill(circle);
			}
		}
		
	}
	public void paintSideD(Graphics g,int diameter,int X,int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GREEN);
		for(int i = 0;i<4;i++)
		{
			for(int j=0;j<4-(3-i);j++)
			{
				Ellipse2D circle = new Ellipse2D.Double(X+2*diameter-(diameter/2)*i+j*diameter,Y+12*diameter-i*diameter,diameter,diameter);
				g2.draw(circle);
				g2.fill(circle);
			}
		}
	}
	public void paintSideE(Graphics g,int diameter,int X, int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.MAGENTA);
		for(int i = 0;i<4;i++)
		{
			for(int j=0;j<4-(3-i);j++)
			{
				Ellipse2D circle = new Ellipse2D.Double(X+6.5*diameter-(diameter/2)*i+j*diameter,Y+5*diameter+i*diameter,diameter,diameter);
				g2.draw(circle);
				g2.fill(circle);
			}
		}
	}
	public void paintSideF(Graphics g,int diameter,int X, int Y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.CYAN);
		for(int i = 0;i<4;i++)
		{
			for(int j=0;j<4-i;j++)
			{
				Ellipse2D circle = new Ellipse2D.Double(X+8*diameter-j*diameter-i*(diameter/2),Y+diameter*i,diameter,diameter);
				g2.draw(circle);
				g2.fill(circle);
			}
		}
	}
}
