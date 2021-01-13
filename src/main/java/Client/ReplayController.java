package Client;

import Client.GUI.ChooseGame.ChoosingDialog;
import Communication.*;
import Game.GameParameters;
import Utility.Log;

import java.util.Map;

public class ReplayController extends MessageController {

    private Map<Integer, GameParameters> availableGames;

    @Override
    public void serviceMessage(Message message) {

        ReplayMessage replayMessage;

        try {
            replayMessage = (ReplayMessage) message;
        }catch (ClassCastException ex) {
            return;
        }

        switch (replayMessage.getReplayMessageType()) {
            case LIST_OF_GAMES:
                listOfGamesHandler((ListOfGames) message);
                break;
            case ANSWER:
                answerHandler((ChooseAnswer) message);
                break;
            case PARAMETERS:
                parametersHandler((ReplayParameters) message);
                break;
            case MOVE:
                moveHandler((ReplayMove) message);
                break;
        }
    }

    @Override
    public void nextMove() {
        Log.log("prosba o ruch");
        sender.send(new NextMove());
    }

    private void listOfGamesHandler(ListOfGames listMessage) {
        availableGames = listMessage.getGames();
        chooseGame();
    }

    private void answerHandler(ChooseAnswer answerMessage) {
        if(!answerMessage.getAnswer()) {
            guiController.showError("Wybrana gra nie poprawna!");
            chooseGame();
        }
    }

    private void parametersHandler(ReplayParameters parametersMessage) {
        GameParameters parameters = parametersMessage.getParameters();
        guiController.prepareForReplay();
        guiController.setBoardParameters(parameters.getNumberFields(), parameters.getNumberPlayers(), parameters.getNumberCounter());
        guiController.showInfo("Zasady gry: \nLiczba graczy: " + parameters.getNumberPlayers() +
                "\nLiczba pionkow: " + parameters.getNumberCounter() +
                "\nRozmiar planszy: " + parameters.getNumberFields() +
                "\nBlokowanie: " + parameters.isBlocks());
    }

    private void moveHandler(ReplayMove moveMessage) {
        if(moveMessage.getMove() != null) {
            Log.log("mam ruch");
            guiController.makeMove(moveMessage.getMove());
        } else {
            guiController.showInfo("Nie ma wiecej ruchow.");
        }
    }

    private void chooseGame() {
        Log.log("" + availableGames.toString());
        ChoosingDialog dialog = new ChoosingDialog(availableGames);
        int gameId = dialog.getId();
        Log.log("Wybralem " + gameId);
        sender.send(new ChosenGame(gameId));
    }
}
