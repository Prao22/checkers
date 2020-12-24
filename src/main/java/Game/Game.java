package Game;

import Game.Judge.*;
import Utility.Log;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<Integer, Player> players;
    private Board board;
    private GameParameters parameters;
    private Turn turns;
    private Judge judge;

    public Game() {
        players = new HashMap<Integer, Player>(6);
    }

    public void init(GameParameters parameters) {
        turns = new Turn(players);
        this.parameters = parameters;
        board = new Board(parameters.getNumberFields(), parameters.getNumberPlayers(), parameters.getNumberCounter());

        judge = new DefaultJudge(board);
        judge = new CheckOverlappingCounters(judge);
        judge = new CheckDefaultJump(judge);
        judge = new CheckDefaultMove(judge);
    }

    public boolean checkIfMoveIsValid(Move move) {
        Field from = board.getField(move.getFrom()[Move.ROW], move.getFrom()[Move.COLUMN]);
        Field to = board.getField(move.getTo()[Move.ROW], move.getTo()[Move.COLUMN]);

        return judge.checkIfMoveIsValid(from, to);
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

    public boolean removePlayer(int playerId) {

        players.remove(playerId);

        if (playerId == whoseTurn()) {
            turns.nextTurn();
            return true;
        }

        return false;
    }

}
