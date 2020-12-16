package Board;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GameBoardTest {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
			{
				GameBoardFrame frame = new GameBoardFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			});
	}

}
