package Client.GUI;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    public TitleLabel(String gameTitle) {
        super(gameTitle);
        setFont(new Font("Arial", Font.BOLD, 60));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
