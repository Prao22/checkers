package Client;

import Communication.GameInformation;
import Communication.GameMessage;
import Communication.Parameters;
import Game.GameParameters;
import Utility.Log;

public class Controller implements GameService {

    private ClientWindow window;
    private GameParameters parameters;
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

        switch (message.getGameMessageType()) {
            case INFORMATION: {
                Log.log("Otrzymałem wiadomość: " + message.toString());
                window.updateLabel(((GameInformation) message).getMessage());
                break;
            }

            case MOVE: {
                System.out.println(message.toString());
                break;
            }

            case PARAMETERS: {
                Log.log("Otrzymałem parametry: " + ((Parameters) message).getParameters().toString());
                parameters = ((Parameters) message).getParameters();
                break;
            }

            case YOUR_TURN: {
                Log.log("Teraz jest moja tura i powinienem odesłać ruch");
                break;
            }

        }
    }

    @Override
    public void showError(String err) {
        System.out.println("mamy blad");
    }
}
