/*
*@author Karol Melanczuk
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CommitButton extends JButton
{
	
	CommitButton(String string)
	{
		super(string);
		this.setPreferredSize(new Dimension(200,100));
		Click joinAction = new Click();
		this.addActionListener(joinAction);
	}
	private class Click implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String ip = ClientFrame.textField.getIp();
			String port = ClientFrame.getTextField().getPort();
			if(ip.equals("")&&port.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Wprowadz adres IP oraz numer PORT'u!");
			}
			else if (ip.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Wprowadz adres IP!");
			}
			else if (port.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Wprowadz numer PORT'u!");
			}
			else
			{
				boolean succesful = ClientFrame.serverInteraction.connectionRequest(ip,port);
				if(succesful)
				{
					JOptionPane.showMessageDialog(null, "Nie mozna dolaczyc do gry o podanym adresie IP i PORT");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wprowadzone dane nie moga byc IP lub Portem serwera.\nSproboj ponownie.");
				}
			}
			
			
		}
	}
	public JButton getButton()
	{
		return this;
	}
}
