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

    /**
     * Obsługa wiadomości dotyczącej gry.
     *
     * @param message  otrzymana wiadomość.
     * @param playerId gracz który ją wysłał
     */
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

    /**
     * Dodaje gracza do rozgrywki.
     *
     * @param playerId numer gracza
     */
    @Override
    public void addPlayer(int playerId) {
        game.addPlayer(playerId);
    }

    /**
     * Usuwa gracza z rozgrywki.
     *
     * @param playerId numer gracza
     */
    @Override
    public void removePlayer(int playerId) {
        if (game.removePlayer(playerId)) {
            sender.send(new YourTurn(), game.whoseTurn());
        }

    }

    /**
     * Inicjalizuje gre.
     */
    @Override
    public void start() {
        game.init(gameParameters);
        int whoseTurn = game.whoseTurn();
        sender.send(new YourTurn(), whoseTurn);
    }

    /**
     * Obsługa informacji dotyczących gry.
     * Używane podczas testowania.
     *
     * @param information przesłana informacja
     * @param playerId    nadawca informacja
     */
    private void informationHandler(GameInformation information, int playerId) {
        Log.log("Otrzymalem wiadomosc: \"" + information.getMessage() + "\" od gracza nr " + playerId);
        sender.sendToAll(new GameInformation("Mam nowa wiadomosc od kogos z was"));
        sender.send(new GameInformation("Odebralem od ciebie wiadomosc " + information.getMessage()), playerId);
    }


    /**
     * Obsługa odbieranych ruchów
     *
     * @param move     ruch w grze
     * @param playerId gracz wykonujący ruch
     */
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
