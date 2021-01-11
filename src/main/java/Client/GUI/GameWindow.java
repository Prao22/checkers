package Client.GUI;

import Client.GUIObserver;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

/**
 * Klasa ramki w której wyświetlona zostanie plansza z grą.
 * 
 *
 */
public class GameWindow extends JFrame implements WindowListener {

	/**
	 * Tytuł gry.
	 */
    private static final String GAME_NAME = "Chinese Checkers";
    
    /**
     * Etykieta zawierajaca tytuł gry.
     */
    private final TitleLabel title;
    
    /**
     * Plansza gry.
     */
    private Board board;
    
    /**
     * Stopka na której wyświetlone zostaną informacje o grze.
     */
    private final Footer footer;
    
    /**
     * Obserwator akcji gracza.
     */
    private GUIObserver observer;

    public GameWindow(GUIObserver observer) {
        setTitle(GAME_NAME);
        title = new TitleLabel(GAME_NAME);
        footer = new Footer(observer);
        this.observer = observer;

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(title, BorderLayout.NORTH);
        this.getContentPane().add(footer, BorderLayout.SOUTH);

        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
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
        this.board = new Board(sizeOfBoard, players, counters, observer);
        getContentPane().add(this.board, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }

    public void setReplayMode() {
        this.toFront();
        footer.setReplayMode();
    }

    public void setObserver(GUIObserver observer) {
        this.observer = observer;
        footer.setObserver(observer);
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
