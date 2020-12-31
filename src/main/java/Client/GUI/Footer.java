package Client.GUI;

import Client.ButtonObserver;
import Game.CounterColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa stopki z informacjami o grze.
 * 
 *
 */
public class Footer extends JPanel {

	/**
	 * Etykieta z informacjami o ilości połączonych graczy.
	 */
    private final JLabel connectedPlayersInfo;
    
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
    private final JButton noMove;
    
    /**
     * Obserwator akcji przycisku.
     */
    private final ButtonObserver observer;

    public Footer(ButtonObserver observer) {
        this.observer = observer;
        connectedPlayersInfo = new JLabel();
        info = new JLabel();
        playerInfo = new JLabel();
        turnInfo = new JLabel();
        noMove = new JButton();

        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        Font italicFont = new Font("Arial", Font.ITALIC, 20);
        Font boldFont = new Font("Monospaced", Font.BOLD, 25);

        connectedPlayersInfo.setFont(normalFont);
        info.setFont(italicFont);
        playerInfo.setFont(italicFont);
        turnInfo.setFont(boldFont);
        noMove.setFont(normalFont);
        noMove.setText("Oddaj ruch");

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(info);
        add(connectedPlayersInfo);
        add(playerInfo);
        add(turnInfo);
        add(noMove);

        noMove.addActionListener(actionEvent -> observer.buttonClicked());
        noMove.setEnabled(false);
    }

    /**
     * 
     * @param onlinePlayers Gracze którzy już dołączyli do gry.
     * @param maxPlayers Ilość wszystkich graczy którzy mogą do gry dołączyć.
     * @param text
     */
    public void update(int onlinePlayers, int maxPlayers, String text) {
        connectedPlayersInfo.setText(onlinePlayers + "/" + maxPlayers);
        if (text != null) {
            info.setText(text);
        }
    }

    /**
     * Aktualizuje tekst etykiety "info".
     * @param text Nowy tekst który chcemy wyświtlić.
     */
    public void update(String text) {
        info.setText(text);
    }

    /**
     * Aktualizuje etykiete wyświetlajaca informacje o ilości graczy.
     * @param playerId Ilość graczy ktora ma zostać wyświetlona.
     */
    public void updatePlayerInfo(int playerId) {
        playerInfo.setText(" Twój id: " + playerId + " | Twój kolor: " + CounterColor.getFromNumber(playerId) + " | ");
    }

    /**
     * Aktualizuje etykiete wyświetlającą informacje o ruchu gracza.
     * @param myTurn
     */
    public void updateTurnInfo(boolean myTurn) {
        if (myTurn) {
            turnInfo.setText(" TWOJA TURA!  ");
            noMove.setEnabled(true);
        } else {
            turnInfo.setText("              ");
            noMove.setEnabled(false);
        }
    }
}
