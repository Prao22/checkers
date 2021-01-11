package Server;

import Communication.*;
import Game.GameParameters;
import Server.Database.DatabaseService;

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
        GameParameters parameters = databaseService.getGameParameters(gameId);

        if(parameters != null) {
            sender.send(new ChooseAnswer(true), clientId);
            sender.send(new ReplayParameters(parameters), clientId);
        } else {
            sender.send(new ChooseAnswer(false), clientId);
        }

    }

    void nextMoveHandler(NextMove request) {
        Game.Move nextMove = databaseService.getMove(gameId, moveCounter);
        moveCounter++;

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
