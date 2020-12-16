package Game;

import Utility.Log;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<Integer, Player> players;
    private Board board;
    private GameParameters parameters;
    private Turn turns;

    public Game() {
        players = new HashMap<Integer, Player>(6);
    }

    public void init(GameParameters parameters) {
        turns = new Turn(players);
        this.parameters = parameters;
        board = new Board(parameters.getNumberFields());
    }

    public boolean checkIfMoveIsValid(Move move) {
        return true;
    }

    public void makeMove(Move move) {
        board.moveCounter(move);
        //sprawdzenie czy byl skok
        //przesuniecie tury albo i nie
        turns.nextTurn();
    }

    public int whoseTurn() {
        return turns.whoseTurn();
    }

    public void addPlayer(int playerId) {
        players.put(playerId, new Player(playerId));
    }

    public void removePlayer(int playerId) {
        players.remove(playerId);
        turns.removePlayer(playerId);
    }
}
