package Client.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa obrabiająca tytuł gry.
 * 
 *
 */
public class TitleLabel extends JLabel {

    //magiczne liczby wyprowadzone doświadczalnie (potrzebne aby aplikacja wyglądała jako tako na mniejszych ekranach)
    private final int MARGIN = (int) (5 * Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 384;

    public TitleLabel(String gameTitle) {
        super(gameTitle);
        //magiczne liczby wyprowadzone doświadczalnie (potrzebne aby aplikacja wyglądała jako tako na mniejszych ekranach)
        setFont(new Font("Arial", Font.BOLD, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 64)));
        setHorizontalAlignment(JLabel.CENTER);

        setPreferredSize(new Dimension(getPreferredSize().width + MARGIN, getPreferredSize().height));
    }
}
