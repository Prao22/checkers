/*
*@author Karol Melanczuk
*/

import java.awt.*;
import javax.swing.*;



class ClientFrame extends JFrame
{
	static int DEFAULT_X;
	static int DEFAULT_Y;
	public ClientFrame()
	{
		Toolkit kit  = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		DEFAULT_X = screenSize.width;
		DEFAULT_Y=screenSize.height;
		setSize(DEFAULT_X,DEFAULT_Y);
		setTitle("Chinese Cheekers");
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		JLabel labelTitle = new JLabel("Chinese Cheekers");
		JLabel labelAuthors = new JLabel("by Sebastian i Karol Melanczuk");
		labelTitle.setFont(new Font("Serif", Font.BOLD, 72));
		CommitButton joinButton = new CommitButton("Join game");
		JLabel labelHelper = new JLabel();
		JLabel labelHelper2 = new JLabel();
		//JLabel label4 = new JLabel();
		TextComponent textFields = new TextComponent();
		add(labelTitle, new GBC(1,1).setFill(GBC.NONE).setAnchor(GBC.SOUTH).setWeight(100,100));
		add(labelAuthors, new GBC(1,2).setFill(GBC.NONE).setAnchor(GBC.PAGE_START).setWeight(50,50));
		add(textFields.getPanel(), new GBC(1,3).setAnchor(GBC.CENTER).setWeight(100,100));
		add(joinButton.getButton(), new GBC(1,4).setAnchor(GBC.FIRST_LINE_END).setWeight(300,200));
		add(labelHelper, new GBC(3,1).setFill(GBC.NONE).setWeight(100,100));
		add(labelHelper2, new GBC(0,0).setFill(GBC.NONE).setWeight(100,100));
		//add(label4, new GBC(1,4).setFill(GBC.NONE).setWeight(200,200));

	}
}
