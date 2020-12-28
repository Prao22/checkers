package Server;

import Communication.*;
import Game.Game;
import Game.GameParameters;
import Utility.Log;

/**
 * Klasa obsługująca odebrane wiadomości które dotyczą gry.
 */
public class GameManager implements GameService {

    /**
     * Umożliwia wysyłanie wiadomości do graczy.
     */
    private final Sender sender;

    /**
     * Parametry aktualnej rozgrywki.
     */
    private final GameParameters gameParameters;

    /**
     * Obsługa logicznej części gry.
     */
    private final Game game;


    public GameManager(Sender sender, GameParameters gameParameters, Game game) {
        this.sender = sender;
        this.gameParameters = gameParameters;
        this.game = game;
    }

    @Override
    public void serviceMessage(GameMessage message, int playerId) {

        switch (message.getGameMessageType()) {

            case INFORMATION: {
                informationHandler((GameInformation) message, playerId);
                break;
            }

            case MOVE: {
                Log.log("Gracz " + playerId + " chce zrobić ruch " + message.toString());
                moveHandler((Move) message, playerId);
                break;
            }
        }

    }

    @Override
    public void addPlayer(int playerId) {
        game.addPlayer(playerId);
    }

    @Override
    public void removePlayer(int playerId) {
        if (game.removePlayer(playerId)) {
            sender.send(new YourTurn(), game.whoseTurn());
        }

    }

    @Override
    public void start() {
        game.init(gameParameters);
        int whoseTurn = game.whoseTurn();
        sender.send(new YourTurn(), whoseTurn);
    }

    private void informationHandler(GameInformation information, int playerId) {
        Log.log("Otrzymalem wiadomosc: \"" + information.getMessage() + "\" od gracza nr " + playerId);
        sender.sendToAll(new GameInformation("Mam nowa wiadomosc od kogos z was"));
        sender.send(new GameInformation("Odebralem od ciebie wiadomosc " + information.getMessage()), playerId);
    }

    private void moveHandler(Communication.Move move, int playerId) {
        if (playerId != game.whoseTurn()) {
            return;
        }

        if (game.makeMove(move.getMove(), playerId)) {
            sender.send(new Answer(true), playerId);
            sender.sendToAll(move);
        } else {
            sender.send(new Answer(false), playerId);
        }

        int winner = game.isWinner();

        if (winner != -1) {
            sender.sendToAll(new Winner(winner));
        } else {
            sender.send(new YourTurn(), game.whoseTurn());
        }
    }
}
