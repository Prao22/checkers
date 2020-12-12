/*
*@author Karol Melanczuk
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CommitButton 
{
	JButton joinButton;
	CommitButton(String string)
	{
		joinButton = new JButton(string);
		joinButton.setPreferredSize(new Dimension(200,100));
		Click joinAction = new Click();
		joinButton.addActionListener(joinAction);
	}
	private class Click implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//sentDataToServer()
		}
	}
	public JButton getButton()
	{
		return joinButton;
	}
}
