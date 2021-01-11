package Client;

import Client.GUI.Board;
import Client.GUI.GameWindow;

import javax.swing.*;

public class GUIController implements GUIService, GUIObserver {

    private GameWindow mainWindow;
    private Board observedBoard;
    private IController controller;

    private int[] firstClick;
    private boolean secondClick = false;
    private boolean replayMode = false;

    public GUIController(IController controller) {
        this.controller = controller;
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
        if(firstClick != null) {
            observedBoard.normalField(firstClick[0], firstClick[1]);
            firstClick = null;
        }

        secondClick = false;
    }

    @Override
    public void repack() {
        mainWindow.pack();
    }

    @Override
    public void prepareForReplay() {

        replayMode = true;
        mainWindow.setReplayMode();
    }

    @Override
    public void clickNotify(int row, int col) {

        if (replayMode || observedBoard.getColorOfField(row, col) == null || controller.getColor() == null ||
                (!observedBoard.getColorOfField(row, col).equals(controller.getColor().getJavaColor()) && !secondClick)) {
            return;
        }


        if (secondClick) {
            controller.setMove(new Game.Move(firstClick, new int[]{row, col}));
            resetClicks();
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
        controller.close();
    }

    @Override
    public void buttonClicked() {
        if(!replayMode) {
            controller.setMove(null);
        } else {
            controller.nextMove();
        }
    }

    public void setController(IController controller) {
        this.controller = controller;
    }
}
