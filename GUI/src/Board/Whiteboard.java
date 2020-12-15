package Board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
//import java.Toolkit.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

//import com.sun.media.sound.Toolkit;

import Utility.BoardCreator;

public class Whiteboard extends JComponent{
	public static int CenterX=900;
	public static int CenterY=400;
	private BoardCreator boardCreator;
	private boolean[][] template;
	private ArrayList<BoardField> fields = new ArrayList<BoardField>();
	Whiteboard(int x, int y)
	{
		boardCreator = new BoardCreator();
		template=BoardCreator.createBoard(4);
		CenterX=x;
		CenterY=y;
		ClickAction click =new ClickAction();
		addMouseListener(click);
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Color defaultColor = this.getBackground();
		for(int i=0;i<13;i++)
		{
			
			for(int j = 0; j<17;j++)
			{
				Ellipse2D circle;
				if(template[j][i])
				{
					g2.setPaint(Color.LIGHT_GRAY);
					if(j%2==0)
					{
						circle = new Ellipse2D.Double(630+i*45,100+45*j,40,40);
						fields.add(new BoardField(j,i,circle));
						g2.draw(circle);
						g2.fill(circle);
					}
					else
					{
						
						circle = new Ellipse2D.Double(650+i*45,100+45*j,40,40);
						fields.add(new BoardField(j,i,circle));
						g2.draw(circle);
						g2.fill(circle);
					}
				}
			}
			
		}
		paintPlayerA(g);
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

	private class ClickAction extends MouseAdapter
	{
		public void mousePressed(MouseEvent event)
		{
			for(BoardField field: fields)
			{
				if(field.getCircle().contains(event.getPoint()))
				{
					int[] temporary2 = field.getCoordinates();
					System.out.println(temporary2[0]+ " " + temporary2[1]);
					break;
				}
			}
		}
	}
	public void paintPlayerA(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.RED);
		//Ellipse2D[] player = new Ellipse2D[10];
		int[] temporary=new int [2];
		//Pawn playerA1;
		temporary=fieldCoordinates(0,6);
		//temporary=fieldCoordinates(1,6);
	/*	//player[0] = new Ellipse2D.Double(900,100,40,40);
		BoardField f= null;
		temporary = fieldCoordinates(1,5);
		//f=findField(1,5);
		player[1] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		player[1] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		//playerA1 = new Pawn(Color.RED,player[1]);
		//updateField(f,playerA1);
		temporary=fieldCoordinates(1,6);
		player[2] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary=fieldCoordinates(2,5);
		player[3] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary=fieldCoordinates(2,6);
		player[4] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary = fieldCoordinates(2,7);
		player[5] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary = fieldCoordinates(3,4);
		player[6] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary=fieldCoordinates(3,5);
		player[7] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary=fieldCoordinates(3,6);
		player[8] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		temporary=fieldCoordinates(3,7);
		player[9] = new Ellipse2D.Double(temporary[0],temporary[1],40,40);
		for(int i=0;i<10;i++)
		{
			
			g2.draw(player[i]);
			g2.fill(player[i]);
		}*/
		
		
		
	}
	public int[] fieldCoordinates(int i,int j)
	{
		int[] temporary=new int[2];
		for(BoardField field: fields)
		{
			temporary = field.getCoordinates();
			if(temporary[0]==i&&temporary[1]==j)
			{
				temporary[0]=(int) field.getCircle().getX();
				temporary[1]=(int) field.getCircle().getY();
				break;
			}
		}
		System.out.println(temporary[0]+ " "+temporary[1]);
		return temporary;
	}	
	public BoardField findField(int i, int j)
	{
		int[] temporary=null;
		BoardField test = null;
		for(BoardField field: fields)
		{
			temporary = field.getCoordinates();
			if(temporary[0]==i&&temporary[1]==j)
			{
				test=field;
				break;
			}
		}
		return test;
	}
	public void updateField(BoardField field,Pawn pawn)
	{
		field.setPawn(pawn);
		field.setTaken(true);	
	}
	
}

