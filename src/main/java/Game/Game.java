package Game;

import Game.Judge.*;
import Utility.BoardCreator;
import Utility.Log;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<Integer, Player> players;
    private Board board;
    private GameParameters parameters;
    private Turn turns;
    private Judge judge;
    private LastMove lastMove;

    public Game() {
        players = new HashMap<>(6);
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

    public boolean makeMove(Move move, int who) {

        if (move == null) {
            lastMove = null;
            turns.nextTurn();
            return true;
        }

        Field from = board.getField(move.getFrom()[Move.ROW], move.getFrom()[Move.COLUMN]);
        Field to = board.getField(move.getTo()[Move.ROW], move.getTo()[Move.COLUMN]);

        if (judge.checkIfMoveIsValid(from, to, who, lastMove)) {

            board.moveCounter(move);

            boolean jump = judge.isWasJump();
            lastMove = new LastMove(from, to, who, jump);

            if (!jump) {
                lastMove = null;
                turns.nextTurn();
            }

            return true;
        } else {
            return false;
        }
    }

    public int isWinner() {
        return judge.getWinner();
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
