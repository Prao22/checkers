package Client;

import Communication.Message;
import Game.CounterColor;

public abstract class MessageController implements IController {
    protected Sender sender;
    protected GUIController guiController;

    public abstract void serviceMessage(Message message);

    public void showError(String err) {
        guiController.showError(err);
    }

    public void showInfo(String info) {
        guiController.showInfo(info);
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
    public void setGuiController(GUIController guiController) {
        this.guiController = guiController;
    }

    public GUIController getGuiController() {
        return guiController;
    }

    @Override
    public CounterColor getColor() {
        return null;
    }

    @Override
    public void setMove(Game.Move move) {

    }

    @Override
    public void close() {

    }

    public void nextMove() {
    }
}
