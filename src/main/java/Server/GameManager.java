package Server;


import Communication.*;
import Game.Game;
import Game.Player;
import Game.GameParameters;
import Utility.Log;

import java.util.*;

public class GameManager implements GameService {

    private Sender sender;
    private GameParameters gameParameters;
    private Game game;


    public GameManager(Sender sender, GameParameters gameParameters) {
        this.sender = sender;
        this.gameParameters = gameParameters;
        game = new Game();
    }

    @Override
    public int serviceMessage(GameMessage message, int playerId) {

        switch (message.getGameMessageType()) {

            case INFORMATION: {
                informationHandler((GameInformation) message, playerId);
                break;
            }

            case MOVE: {
                Log.log("Gracz " + playerId + " chce zrobić ruch " + ((Move) message).toString());
                moveHandler((Move) message, playerId);
                break;
            }
        }

        return 0;
    }

    @Override
    public void addPlayer(int playerId) {
        game.addPlayer(playerId);
    }

    @Override
    public void removePlayer(int playerId) {
        game.removePlayer(playerId);
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
        if (game.checkIfMoveIsValid(move.getMove())) {
            game.makeMove(move.getMove());
            sender.send(new Answer(true), playerId);
            sender.sendToAll(move);
            sender.send(new YourTurn(), game.whoseTurn());
        } else {
            sender.send(new Answer(false), playerId);
        }
    }


    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setGameParameters(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
    }
}
