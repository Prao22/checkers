/*
*@author Karol Melanczuk
*/

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
public class TextComponent extends JComponent
{
public JPanel panel;
	public TextComponent()
	{
		JTextField ipField = new JTextField();
		JTextField portField = new JTextField();
		ipField.setPreferredSize(new Dimension(200,24));
		portField.setPreferredSize(new Dimension(200,24));

		panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(new JLabel("Ip addres: ", SwingConstants.RIGHT));
		panel.add(ipField);
		panel.add(new JLabel("Port addres: ", SwingConstants.RIGHT));
		panel.add(portField);
		//add(panel,BorderLayout.CENTER);
	}
	public JPanel getPanel()
	{
		return panel;
	}
}
