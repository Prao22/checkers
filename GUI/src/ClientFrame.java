/*
*@author Karol Melanczuk
*/

import java.awt.*;
import javax.swing.*;



class ClientFrame extends JFrame
{
	static int DEFAULT_X;
	static int DEFAULT_Y;
	static TextComponent textField;
	static ServerInteraction serverInteraction;
	public ClientFrame()
	{
		
		serverInteraction = new ServerInteraction();
		
		Toolkit kit  = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		DEFAULT_X = screenSize.width;
		DEFAULT_Y=screenSize.height;
		
		setSize(DEFAULT_X/2,DEFAULT_Y/2);
		setTitle("Chinese Checkers");
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		createLabels();
		
		createTextComponents();
		
		createButtons();

	}
	void createLabels()
	{
		JLabel labelTitle = new JLabel("Chinese Checkers");
		JLabel labelAuthors = new JLabel("by Sebastian i Karol Melanczuk");
		labelTitle.setFont(new Font("Serif", Font.BOLD, 72));
		JLabel labelHelper = new JLabel();
		JLabel labelHelper2 = new JLabel();
		add(labelTitle, new GBC(1,1).setFill(GBC.NONE).setAnchor(GBC.SOUTH).setWeight(100,100));
		add(labelAuthors, new GBC(1,2).setFill(GBC.NONE).setAnchor(GBC.PAGE_START).setWeight(50,50));
		add(labelHelper, new GBC(3,1).setFill(GBC.NONE).setWeight(100,100));
		add(labelHelper2, new GBC(0,0).setFill(GBC.NONE).setWeight(100,100));
	}
	
	void createTextComponents()
	{
		ClientFrame.textField = new TextComponent();
		add(textField.getPanel(), new GBC(1,3).setAnchor(GBC.CENTER).setWeight(100,100));
	}
	
	void createButtons()
	{
		CommitButton joinButton = new CommitButton("Join game");
		add(joinButton.getButton(), new GBC(1,4).setAnchor(GBC.FIRST_LINE_END).setWeight(300,200));
	}
	static TextComponent getTextField()
	{
		return ClientFrame.textField;
	}
}
