/*
*@author Karol Melanczuk
*/

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
public class TextComponent extends JComponent
{

public JPanel panel;
public JTextField ipField;
public JTextField portField;
	public TextComponent()
	{
		ipField = new JTextField();
		portField = new JTextField();
		ipField.setPreferredSize(new Dimension(200,24));
		portField.setPreferredSize(new Dimension(200,24));
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(new JLabel("Ip addres: ", SwingConstants.RIGHT));
		panel.add(ipField);
		panel.add(new JLabel("Port addres: ", SwingConstants.RIGHT));
		panel.add(portField);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	public String getIp()
	{
		return ipField.getText();
	}
	
	public String getPort()
	{
		return portField.getText();
	}
}
