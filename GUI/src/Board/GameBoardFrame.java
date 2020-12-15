package Board;

import java.awt.Dimension;
import java.awt.Toolkit;

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
		add(new Whiteboard(DEFAULT_WIDTH/2,DEFAULT_HEIGHT/2));
	}
}
