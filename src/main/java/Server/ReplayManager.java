package Server;

import Communication.*;
import Game.GameParameters;
import Server.Database.DatabaseConnector;
import Server.Database.DatabaseService;
import Utility.Log;

public class ReplayManager implements ReplayService {

    private final Sender sender;
    private final DatabaseService databaseService;
    private int gameId;
    private int clientId;
    private int moveCounter = 1;

    public ReplayManager(Sender sender, DatabaseService databaseService) {
        this.sender = sender;
        this.databaseService = databaseService;
    }

    void gameChooseHandler(ChosenGame chosenGame) {

        gameId = chosenGame.getGameId();
        Log.log("Chce ogldac " + gameId);
        GameParameters parameters = databaseService.getGameParameters(gameId);

        if(parameters != null) {
            sender.send(new ChooseAnswer(true), clientId);
            sender.send(new ReplayParameters(parameters), clientId);
        } else {
            sender.send(new ChooseAnswer(false), clientId);
        }

    }

    void nextMoveHandler(NextMove request) {

        Log.log("Prosze o ruch z " + gameId + " " + moveCounter);
        Game.Move nextMove = databaseService.getMove(gameId, moveCounter);
        moveCounter++;

        Log.log("Mam ruch + " + nextMove);

        sender.send(new ReplayMove(nextMove), clientId);
    }

    @Override
    public void serviceMessage(ReplayMessage message, int clientId) {
        switch (message.getReplayMessageType()) {
            case GAME_CHOOSE:
                gameChooseHandler((ChosenGame) message);
                break;
            case NEXT:
                nextMoveHandler((NextMove) message);
                break;
        }
    }

    @Override
    public void start() {
        sender.send(new ListOfGames(databaseService.listAllGames()), clientId);
    }

    @Override
    public void setClient(int id) {
        clientId = id;
    }

}
