package Client.GUI;

import Client.ButtonObserver;
import Game.CounterColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Footer extends JPanel {

    private final JLabel connectedPlayersInfo;
    private final JLabel info;
    private final JLabel playerInfo;
    private final JLabel turnInfo;
    private final JButton noMove;
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
        if (myTurn) {
            turnInfo.setText(" TWOJA TURA!  ");
            noMove.setEnabled(true);
        } else {
            turnInfo.setText("              ");
            noMove.setEnabled(false);
        }
    }
}
