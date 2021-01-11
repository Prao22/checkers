package Server.Database;

import Game.GameParameters;
import Game.Move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnector implements DatabaseService {

    private static DatabaseConnector instance = null;

    private DatabaseConnector() {

    }

    public static DatabaseConnector getInstance() {
        if(instance == null) {
            synchronized (DatabaseConnector.class) {
                if(instance == null) {
                    instance = new DatabaseConnector();
                }
            }
        }

        return instance;
    }


    @Override
    public int newGame(GameParameters parameters) {
        return 0;
    }

    @Override
    public void saveNextMove(Move move, int gameId) {

    }

    @Override
    public GameParameters getGameParameters(int gameId) {
        return new GameParameters();
    }

    @Override
    public Move getMove(int gameId, int whichMove) {
        return null;
    }

    @Override
    public Map<Integer, GameParameters> listAllGames() {
        Map<Integer, GameParameters> a = new HashMap<>();
        a.put(1, new GameParameters());
        return a;
    }
}
