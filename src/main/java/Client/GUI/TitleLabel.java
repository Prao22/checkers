package Client.GUI;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {

    private final int MARGIN = 50;

    public TitleLabel(String gameTitle) {
        super(gameTitle);
        setFont(new Font("Arial", Font.BOLD, 60));
        setHorizontalAlignment(JLabel.CENTER);

        setPreferredSize(new Dimension(getPreferredSize().width + MARGIN, getPreferredSize().height));
    }
}
