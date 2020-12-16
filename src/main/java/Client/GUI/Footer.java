package Client.GUI;

import Game.CounterColor;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    private final JLabel connectedPlayersInfo;
    private final JLabel info;
    private final JLabel playerInfo;
    private final JLabel turnInfo;

    public Footer() {
        connectedPlayersInfo = new JLabel();
        info = new JLabel();
        playerInfo = new JLabel();
        turnInfo = new JLabel();

        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        Font italicFont = new Font("Arial", Font.ITALIC, 20);
        Font boldFont = new Font("Arial", Font.BOLD, 25);

        connectedPlayersInfo.setFont(normalFont);
        info.setFont(italicFont);
        playerInfo.setFont(italicFont);
        turnInfo.setFont(boldFont);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(info);
        add(connectedPlayersInfo);
        add(playerInfo);
        add(turnInfo);
    }

    public void update(int onlinePlayers, int maxPlayers, String text) {
        connectedPlayersInfo.setText(onlinePlayers + "/" + maxPlayers);
        if (text != null) {
            info.setText(text);
        }
    }

    public void update(String text) {
        info.setText(text);
    }

    public void updatePlayerInfo(int playerId) {
        playerInfo.setText(" Twój id: " + playerId + " | Twój kolor: " + CounterColor.getFromNumber(playerId) + " | ");
    }

    public void updateTurnInfo(boolean myTurn) {
        turnInfo.setText(myTurn ? " TWOJA TURA!" : "");
    }
}
