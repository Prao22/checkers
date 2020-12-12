import java.awt.*;
import java.awt.font.*;
import javax.swing.*;
import java.awt.geom.*;
class FontClass extends JComponent
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		String Title = "Chinese Cheekers";
		Font bigFont = new Font("Serif", Font.BOLD,12);
		g2.setFont(bigFont);



		FontRenderContext context = g2.getFontRenderContext();	
		Rectangle2D bounds = bigFont.getStringBounds(Title, context);
		
		double x =(getWidth()-bounds.getWidth())/2;
		double y =(getHeight() - bounds.getHeight())/2;
		
		g.drawString(Title,75,100); //(int) x, (int) y);
		
	}
	public Dimension getPrefferedSize()
	{
		return new Dimension(300,200);
	}
}


