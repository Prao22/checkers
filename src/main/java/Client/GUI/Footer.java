package Client.GUI;

import Client.ButtonObserver;
import Game.CounterColor;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa stopki z informacjami o grze.
 * 
 *
 */
public class Footer extends JPanel {

    
    /**
     *  
     */
    private final JLabel info;
    
    /**
     * Etykieta z informacjami o graczu.
     */
    private final JLabel playerInfo;
    
    /**
     * Etykieta z informacją o tym czy w aktualnej turze nastąpił ruch gracza.
     */
    private final JLabel turnInfo;
    
    /**
     * Przycisk za pośrednictwem którego gracz możne zpasować ruch w turze.
     */
    private final JButton button;
    
    /**
     * Obserwator akcji przycisku.
     */
    private ButtonObserver observer;

    public Footer(ButtonObserver observer) {
        this.observer = observer;
        info = new JLabel();
        playerInfo = new JLabel();
        turnInfo = new JLabel();
        button = new JButton();

        //magiczne liczby wyprowadzone doświadczalnie (potrzebne aby aplikacja wyglądała jako tako na mniejszych ekranach)
        int fontSize = Math.max(12, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 192));
        int boldFontSize = Math.max(15, (int) (5 * Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 768));

        Font normalFont = new Font("Arial", Font.PLAIN, fontSize);
        Font italicFont = new Font("Arial", Font.ITALIC, fontSize);
        Font boldFont = new Font("Monospaced", Font.BOLD, boldFontSize);

        info.setFont(italicFont);
        playerInfo.setFont(italicFont);
        turnInfo.setFont(boldFont);
        button.setFont(normalFont);
        button.setText("Oddaj ruch");

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(info);
        add(playerInfo);
        add(turnInfo);
        add(button);

        button.addActionListener(actionEvent -> observer.buttonClicked());
        updateTurnInfo(false);
        playerInfo.setText(" Twój id: " + "-" + " | Twój kolor: " + " - " + " | ");

        setPreferredSize(new Dimension(info.getPreferredSize().width + playerInfo.getPreferredSize().width +
                turnInfo.getPreferredSize().width + button.getPreferredSize().width, info.getPreferredSize().height + playerInfo.getPreferredSize().height +
                turnInfo.getPreferredSize().height + button.getPreferredSize().height));
    }


    /**
     * Aktualizuje tekst etykiety "info".
     * @param text nowy tekst który chcemy wyświtlić.
     */
    public void update(String text) {
        info.setText(text);
    }

    /**
     * Aktualizuje etykiete wyświetlajaca informacje o ilości graczy.
     * @param playerId ilość graczy ktora ma zostać wyświetlona.
     */
    public void updatePlayerInfo(int playerId) {
        playerInfo.setText(" Twój id: " + playerId + " | Twój kolor: " + CounterColor.getFromNumber(playerId) + " | ");
    }

    /**
     * Aktualizuje etykiete wyświetlającą informacje czy jest tura gracza czy nie.
     * @param myTurn czy jest moja tura.
     */
    public void updateTurnInfo(boolean myTurn) {
        if (myTurn) {
            turnInfo.setText(" TWOJA TURA!  ");
            button.setEnabled(true);
        } else {
            turnInfo.setText("              ");
            button.setEnabled(false);
        }
    }

    public void setReplayMode() {
        info.setVisible(false);
        playerInfo.setVisible(false);
        turnInfo.setVisible(false);
        button.setText("Następny ruch");
        button.setEnabled(true);
    }

    public void setObserver(ButtonObserver observer) {
        this.observer = observer;
    }
}
