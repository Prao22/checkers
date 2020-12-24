package Client;

import Client.GUI.Board;
import Client.GUI.GameWindow;

import javax.swing.*;

public class GUIController implements GUIService, GUIObserver {

    private GameWindow mainWindow;
    private Board observedBoard;
    private GameController gameController;

    private int[] firstClick;
    private boolean secondClick = false;

    public GUIController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setWindow(GameWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void makeMove(Game.Move move) {
        observedBoard.move(move.getFrom(), move.getTo());
    }

    @Override
    public void showError(String errorText) {
        JOptionPane.showMessageDialog(mainWindow, errorText, "Błąd",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public boolean showQuestion(String question) {
        return JOptionPane.showConfirmDialog(mainWindow, question, "Pytanie", JOptionPane.YES_NO_OPTION) != 0;
    }

    @Override
    public void updateFooter(int onlinePlayers, int maxPlayers, String text) {
        mainWindow.updateFooter(onlinePlayers, maxPlayers, text);
    }

    @Override
    public void updateFooter(String text) {
        mainWindow.updateFooter(text);
    }

    @Override
    public void updatePlayerInfo(int playerId) {
        mainWindow.updatePlayerInfo(playerId);
    }

    @Override
    public void updateTurnInfo(boolean turn) {
        mainWindow.updateTurnInfo(turn);
    }

    @Override
    public void showInfo(String info) {
        JOptionPane.showMessageDialog(mainWindow, info);
    }

    @Override
    public void setBoardParameters(int size, int numberOfPlayers, int numberOfCounters) {
        mainWindow.setBoardParameters(size, numberOfPlayers, numberOfCounters);
    }

    @Override
    public void resetClicks() {
        firstClick = null;
        secondClick = false;
    }

    @Override
    public void clickNotify(int row, int col) {

        if(!observedBoard.getColorOfField(row, col).equals(gameController.getColor().getJavaColor()) && !secondClick)
        {
            return;
        }


        if (secondClick) {
            gameController.setMove(new Game.Move(firstClick, new int[]{row, col}));
            secondClick = false;
            observedBoard.normalField(firstClick[0], firstClick[1]);
            firstClick = null;
        } else {
            firstClick = new int[]{row, col};
            secondClick = true;
            observedBoard.highlightField(row, col);
        }
    }

    @Override
    public void attach(Board board) {
        observedBoard = board;
    }

    @Override
    public void close() {

    }
}
