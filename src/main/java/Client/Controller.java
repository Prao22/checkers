package Client;

import Communication.*;
import Game.CounterColor;
import Game.GameParameters;
import Utility.Log;

public class Controller implements GameService, GameController {

    private GameParameters parameters;
    private Sender sender;
    private GUIController guiController;
    private int myPlayerId;
    private boolean myTurn = false;
    private boolean end = false;

    public Controller() {

    }

    /**
     * Obsługa odebranych wiadomości od serwera dotyczących gry.
     * Wiadomość jest przekazywana odpowiedniej funkcji przeznaczonej
     * do obsługi konkretnych typów wiadomości.
     * @param message otrzymana wiadomość.
     */
    @Override
    public void serviceMessage(GameMessage message) {

        switch (message.getGameMessageType()) {
            case INFORMATION: {
                informationHandler((GameInformation) message);
                break;
            }

            case ANSWER: {
                answerHandler((Answer) message);
                break;
            }

            case MOVE: {
                moveHandler((Communication.Move) message);
                break;
            }

            case PARAMETERS: {
                parametersHandler((Parameters) message);
                break;
            }

            case YOUR_TURN: {
                yourTurnHandler();
                break;
            }

            case START: {
                startHandler((Start) message);
                break;
            }

            case VOTING: {
                break;
            }

            case WINNER: {
                winnerHandler((Winner) message);
                break;
            }

        }
    }

    private void moveHandler(Communication.Move move) {
        Log.log("Jest ruch " + move.toString());

        if(move.getMove() != null) {
            guiController.makeMove(move.getMove());
        }
    }
    private void answerHandler(Answer answer) {
        Log.log("Odpowiedz serwera: " + answer.getAnswer());

        if(!answer.getAnswer()) {
            guiController.showError("Ruch niepoprawny");
        }
    }
    private void informationHandler(GameInformation information) {
        Log.log("Otrzymałem wiadomość: " + information.toString());
        guiController.showInfo(information.getMessage());
        guiController.updateFooter(information.getMessage());
    }
    private void parametersHandler(Parameters mParameters) {
        parameters = mParameters.getParameters();
        Log.log("Otrzymałem parametry: " + parameters.toString());
        guiController.setBoardParameters(parameters.getNumberFields(), parameters.getNumberPlayers(), parameters.getNumberCounter());
        guiController.showInfo("Zasady gry: \nLiczba graczy: " + parameters.getNumberPlayers() +
                "\nLiczba pionkow: " + parameters.getNumberCounter() +
                "\nRozmiar planszy: " + parameters.getNumberFields() +
                "\nBlokowanie: " + parameters.isBlocks());
    }
    private void yourTurnHandler() {
        Log.log("Teraz jest moja tura i powinienem odesłać ruch");
        guiController.updateTurnInfo(true);
        guiController.resetClicks();
        myTurn = true;
    }
    private void startHandler(Start start) {
        Log.log("START GRY!");
        myPlayerId = start.getPlayerId();
        guiController.showInfo("Otrzymałeś id = " + myPlayerId + " i kolor " + CounterColor.getFromNumber(myPlayerId));
        guiController.updatePlayerInfo(myPlayerId);
        guiController.updateTurnInfo(false);
        guiController.repack();
    }
    private void winnerHandler(Winner winner) {
        if(winner.getWinner() == myPlayerId) {
            guiController.showInfo("Gratulacje!\nZająłeś " + winner.getPlace() + " miejsce!");
            end = true;
        } else {
            guiController.showInfo("Gracz z kolorem " + CounterColor.getFromNumber(winner.getWinner()) + " zajął " + winner.getPlace() + " miejsce!");
        }
    }

    @Override
    public void showError(String err) {
        System.out.println("mamy blad ---> " + err);
        guiController.showError(err);
    }

    @Override
    public CounterColor getColor() {
        return CounterColor.getFromNumber(myPlayerId);
    }

    @Override
    public void setMove(Game.Move move) {
        if(end) {
            return;
        }

        if(myTurn) {
            myTurn = false;
            sender.send(new Communication.Move(move));
            guiController.updateTurnInfo(false);
        } else {
            guiController.showError("Nie twoja tura!");
        }
    }

    @Override
    public void close() {
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
    public void setGuiController(GUIController guiController) {
        this.guiController = guiController;
    }
}
