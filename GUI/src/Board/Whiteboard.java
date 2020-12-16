package Board;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Ellipse2D;


import javax.swing.JComponent;

import Utility.BoardCreator;


public class Whiteboard extends JComponent{
	
	private BoardCreator boardCreator;
	private boolean[][] template;
	final int size;
	final int numberOfPlayers;
	private BoardField[][] fields;
	
	Whiteboard(int size,int numberOfPlayers)
	{
		this.size=size;
		this.numberOfPlayers=numberOfPlayers;
		
		boardCreator = new BoardCreator();
		template=BoardCreator.createBoard(size);
		
		fields = new BoardField[4*size+1] [3*size+1];
		
		
		ClickAction click =new ClickAction();
		addMouseListener(click);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//Color defaultColor = this.getBackground();
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
						fields[j][i]=new BoardField(j,i,circle,Color.LIGHT_GRAY);
						g2.draw(circle);
						g2.fill(circle);
					}
					else
					{
						
						circle = new Ellipse2D.Double(650+i*45,100+45*j,40,40);
						fields[j][i]=new BoardField(j,i,circle,Color.LIGHT_GRAY);
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
		switch(numberOfPlayers) {
		case 2:
			paintSideA(g);
			paintSideD(g);
			break;
			
		case 4:
			paintSideF(g);
			paintSideC(g);
			paintSideB(g);
			paintSideE(g);
			break;
			
		case 6: 
			paintSideA(g);
			paintSideB(g);
			paintSideC(g);
			paintSideD(g);
			paintSideE(g);
			paintSideF(g);
			break;
			
		default:
			System.exit(0);
		}
	}
	private class ClickAction extends MouseAdapter
	{
		private boolean counter = true;
		private int[] setA = new int[2];//wspolrzedne pola A 
		private int[] setB = new int[2];//wspolrzedne pola B
		private int[][] pair = new int[1][1];
		
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
							if(counter)
							{
								System.out.println("Pair one: " +j+" " + i);
								counter=false;
								setA[0]=j;
								setA[1]=i;
								break;
							}
							else
							{
								System.out.println("Pair two: "+j + " " +i);
								counter=true;
								setB[0]=j;
								setB[1]=i;
								
								
								/*
								 * tutaj jest funkcja move
								 */
								move(setA,setB,getGraphics());
								break;
							}
							
							}
						}
					}
				}
			}
		
			
}
	void move(int[] pairA,int[] pairB, Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		//zapisujemy kolor pola A
		Color temporary = fields[pairA[0]][pairA[1]].getColor();
		
		//zamnieniamy kolor pola A
		fields[pairA[0]][pairA[1]].setColor(fields[pairB[0]][pairB[1]].getColor());
		
		//malujemy zmiane
		g2.setPaint(fields[pairA[0]][pairA[1]].getColor());
		g2.fill(fields[pairA[0]][pairA[1]].getCircle());
		
		//zapisany kolor pola A przypisujemy do pola B
		fields[pairB[0]][pairB[1]].setColor(temporary);
		
		//malujemy zmiane
		g2.setPaint(fields[pairB[0]][pairB[1]].getColor());
		g2.fill(fields[pairB[0]][pairB[1]].getCircle());
	}
	void paintSideA(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.RED);
		for(int i=0;i<size;i++)
		{
			for(int j=size-1;j<2*size+1;j++)
				if(fields[i][j]!=null)
				{
					g2.fill(fields[i][j].getCircle());
					fields[i][j].setColor(Color.RED);
					
				}
		}
	}
	void paintSideB(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.BLUE);
		int counter;
		for(int i=size;i<2*size;i++)
		{
			counter=size-i%size;
			for(int j=0;j<size&&counter!=0;j++)
				if(fields[i][j]!=null)
				{
					fields[i][j].setColor(Color.BLUE);
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
		g2.setPaint(Color.MAGENTA);
		for(int i=2*size+1;i<3*size+1;i++)
		{
			set++;
			counter=set;
			for(int j=0;j<size&&counter!=0;j++)
			{
				if(fields[i][j]!=null)
				{
					fields[i][j].setColor(Color.MAGENTA);
					g2.fill(fields[i][j].getCircle());
					counter--;
				}
			}
		}
		
	}
	void paintSideD(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GREEN);
		for(int i=3*size+1;i<4*size+1;i++)
		{
			for(int j=4;j<2*size;j++)
			{
				if(fields[i][j]!=null)
				{
					fields[i][j].setColor(Color.GREEN);
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
		g2.setPaint(Color.YELLOW);
		for(int i=2*size+1;i<3*size+1;i++)
		{
			set++;
			counter=set;
			for(int j=3*size;j>2*size&&counter!=0;j--)
			{
				if(fields[i][j]!=null)
				{
					fields[i][j].setColor(Color.YELLOW);
					g2.fill(fields[i][j].getCircle());
					counter--;
				}
			}
		}
	}
	void paintSideF(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.CYAN);
		int counter;
		for(int i=size;i<2*size;i++)
		{
			counter=size-i%size;
			for(int j=3*size;j>2*size&&counter!=0;j--)
				if(fields[i][j]!=null)
				{
					fields[i][j].setColor(Color.CYAN);
					g2.fill(fields[i][j].getCircle());
					counter--;
					
				}
		}
	}

}

