/*
*@author Karol Melanczuk
*/
import java.awt.*;
import javax.swing.*;


public class Client 
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
			{
				ClientFrame frame = new ClientFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			});
	}
}
