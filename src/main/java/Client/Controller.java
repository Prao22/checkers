package Client;

import Communication.GameInformation;
import Communication.GameMessage;
import Communication.GameMessageType;

public class Controller implements GameService {

    private ClientWindow window;
    private Sender sender;

    public Controller() {

    }

    public Controller(ClientWindow clientWindow, Sender sender) {
        window = clientWindow;
        this.sender = sender;
    }

    public void answer(String adf) {
        sender.send(new GameInformation(adf));
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setWindow(ClientWindow window) {
        this.window = window;
    }

    @Override
    public void serviceMessage(GameMessage message) {
        if (message.getGameMessageType() == GameMessageType.INFORMATION) {
            System.out.println("Halo");
            window.updateLabel(((GameInformation) message).getMessage());
        } else {
            System.out.println("co robic");
        }
    }

    @Override
    public void showError(String err) {
        System.out.println("mamy blad");
    }
}
