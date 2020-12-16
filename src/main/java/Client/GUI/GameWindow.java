package Client.GUI;

import Client.GUIObserver;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class GameWindow extends JFrame implements WindowListener {

    private static final String GAME_NAME = "Chinese Checkers";
    private final TitleLabel title;
    private Board board;
    private final Footer footer;
    private final GUIObserver observer;

    public GameWindow(GUIObserver observer) {
        setTitle(GAME_NAME);
        title = new TitleLabel(GAME_NAME);
        footer = new Footer();
        //board = new Board(4, 4, 10, observer);
        this.observer = observer;

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(title, BorderLayout.NORTH);
        //this.getContentPane().add(board, BorderLayout.CENTER);
        this.getContentPane().add(footer, BorderLayout.SOUTH);

        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateFooter(int onlinePlayers, int maxPlayers, String text) {
        footer.update(onlinePlayers, maxPlayers, text);
    }

    public void updateFooter(String text) {
        footer.update(text);
    }

    public void updatePlayerInfo(int playerId) {
        footer.updatePlayerInfo(playerId);
    }

    public void updateTurnInfo(boolean turn) {
        footer.updateTurnInfo(turn);
    }


    public void setBoardParameters(int sizeOfBoard, int players, int counters) {
        //this.getContentPane().remove(board);
        this.board = new Board(sizeOfBoard, players, counters, observer);
        this.getContentPane().add(board, BorderLayout.CENTER);
        pack();
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int confirm = JOptionPane.showOptionDialog(
                this, "Czy napewno chcesz wyjść z gry?",
                "Potwierdzenie wyjścia", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(confirm == 0) {
            dispose();
            observer.close();
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
