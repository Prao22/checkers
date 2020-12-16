package Board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import Utility.BoardCreator;

/*
 * 
 * Obsluga przycisku znajduje sie w klasie GameBoardFrame
 * 
 * 
 * 
 */

public class Whiteboard extends JComponent{
	private BoardCreator boardCreator;
	private boolean[][] template;
	final int size = 5;
	private BoardField[][] fields = new BoardField[4*size+1] [3*size+1];
	Whiteboard(int x, int y)
	{
		boardCreator = new BoardCreator();
		template=BoardCreator.createBoard(size);
		ClickAction click =new ClickAction();
		addMouseListener(click);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D circle2 = new Ellipse2D.Double(120+100*size,80,2*size*100,2*size*100);
		g2.draw(circle2);
		Color defaultColor = this.getBackground();
		for(int i=0;i<3*size +1;i++)
		{
			
			for(int j = 0; j<4*size +1;j++)
			{
				Ellipse2D circle;
				if(template[j][i])
				{
					g2.setPaint(Color.LIGHT_GRAY);
					if(j%2==0)
					{
						circle = new Ellipse2D.Double(630+i*45,100+45*j,40,40);
						fields[j][i]=new BoardField(j,i,circle);
						g2.draw(circle);
						g2.fill(circle);
					}
					else
					{
						
						circle = new Ellipse2D.Double(650+i*45,100+45*j,40,40);
						fields[j][i]=new BoardField(j,i,circle);
						g2.draw(circle);
						g2.fill(circle);
					}
				}
				else
				{
					fields[j][i]=null;
				}
			}
			
		}
		paintSideA(g);
		paintSideB(g);
		paintSideC(g);
		paintSideD(g);
		paintSideE(g);
		paintSideF(g);
	}
	private class ClickAction extends MouseAdapter
	{
		private int counter = 0;
		private int[] setA = new int[2];
		private int[] setB = new int[2];
		private int[][] pair = new int[1][1];
		
		/*
		 * po nacisnieciu myszy tutaj sie przetwarza
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		public void mousePressed(MouseEvent event)
		{
			for(int i =0;i<size*3+1;i++)
			{
				for(int j=0;j<size*4+1;j++)
				{
					if(fields[j][i]!=null)
					{
						if(fields[j][i].getCircle().contains(event.getPoint()))
						{
							if(counter==0)
							{
								System.out.println("Pair one: " +j+" " + i);
								counter++;
								setA[0]=j;
								setA[1]=i;
								break;
							}
							else if(counter==1)
							{
								System.out.println("Pair two: "+j + " " +i);
								counter=0;
								setB[0]=j;
								setB[1]=i;
								break;
							}
							
							}
						}
					}
				}
			}
		
			
}
	/*
	 * Ponizej znajduje sie jedynie rysowanie pionkow
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	void paintSideA(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for(int i=0;i<size;i++)
		{
			for(int j=size-1;j<2*size+1;j++)
				if(fields[i][j]!=null)
				{
					g2.setPaint(Color.RED);
					g2.fill(fields[i][j].getCircle());
				}
		}
	}
	void paintSideB(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		int counter;
		for(int i=size;i<2*size;i++)
		{
			counter=size-i%size;
			for(int j=0;j<size&&counter!=0;j++)
				if(fields[i][j]!=null)
				{
					g2.setPaint(Color.BLUE);
					g2.fill(fields[i][j].getCircle());
					counter--;
					
				}
		}
	}
	void paintSideC(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		int counter=0;
		int set = 0;
		for(int i=2*size+1;i<3*size+1;i++)
		{
			set++;
			counter=set;
			for(int j=0;j<size&&counter!=0;j++)
			{
				if(fields[i][j]!=null)
				{
					g2.setPaint(Color.MAGENTA);
					g2.fill(fields[i][j].getCircle());
					counter--;
				}
			}
		}
		
	}
	void paintSideD(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for(int i=3*size+1;i<4*size+1;i++)
		{
			for(int j=4;j<2*size;j++)
			{
				if(fields[i][j]!=null)
				{
					g2.setPaint(Color.GREEN);
					g2.fill(fields[i][j].getCircle());
				}
			}
		}
	}
	void paintSideE(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		int counter=0;
		int set = 0;
		for(int i=2*size+1;i<3*size+1;i++)
		{
			set++;
			counter=set;
			for(int j=3*size;j>2*size&&counter!=0;j--)
			{
				if(fields[i][j]!=null)
				{
					g2.setPaint(Color.YELLOW);
					g2.fill(fields[i][j].getCircle());
					counter--;
				}
			}
		}
	}
	void paintSideF(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		int counter;
		for(int i=size;i<2*size;i++)
		{
			counter=size-i%size;
			for(int j=3*size;j>2*size&&counter!=0;j--)
				if(fields[i][j]!=null)
				{
					g2.setPaint(Color.CYAN);
					g2.fill(fields[i][j].getCircle());
					counter--;
					
				}
		}
	}
}


