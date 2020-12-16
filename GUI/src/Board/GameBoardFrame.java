package Board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameBoardFrame extends JFrame {
	int DEFAULT_WIDTH;
	int DEFAULT_HEIGHT;
	GameBoardFrame()
	{
		Toolkit kit  = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		DEFAULT_WIDTH = screenSize.width;
		DEFAULT_HEIGHT=screenSize.height;
		
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setTitle("Chinese Checkers");
		//add(new Whiteboard(DEFAULT_WIDTH/2,DEFAULT_HEIGHT/2));
		setLayout(new BorderLayout());
		JButton button = new JButton("Wykonaj ruch");
		add(button,BorderLayout.EAST);
		Click clickButton = new Click();
		button.addActionListener(clickButton);
		add(new Whiteboard(DEFAULT_WIDTH/2,DEFAULT_HEIGHT/2),BorderLayout.CENTER);
	}
	private class Click implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Wysylam dane do serwera");
			
		}
	}
}
