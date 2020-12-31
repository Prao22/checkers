package Game;

import Game.Judge.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa odpowiadająca za logike gry.
 */
public class Game {
    /**
     * Gracze którzy biorą udział w grze.
     */
    private Map<Integer, Player> players;

    /**
     * Plansza gry.
     */
    private Board board;

    /**
     * Parametry z jakimi gra jest prowadzona.
     */
    private GameParameters parameters;

    /**
     * Obsługa kolejności.
     */
    private Turn turns;

    /**
     * Sędzia do sprawdzania poprawności ruchów.
     */
    private Judge judge;

    /**
     * Ostatni wykonany ruch.
     */
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

    /**
     * Robi ruch jeśli jest poprawny.
     *
     * @param move ruch który ma być wykonany
     * @param who  wykonawca ruchu
     * @return czy ruch był poprawny
     */
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

    /**
     * Zwraca zwyciężce gry.
     *
     * @return id wygranego lub -1 jeśli takiego nie ma.
     */
    public int isWinner() {
        return judge.getWinner();
    }

    /**
     * Sprawdza kogo jest teraz tura.
     *
     * @return id gracza który teraz powinnien wykonać ruch.
     * -1 jeśli nie ma graczy.
     */
    public int whoseTurn() {
        return turns.whoseTurn();
    }

    /**
     * Dodaje gracza do rozgrywki.
     *
     * @param playerId id gracza którego dodać
     */
    public void addPlayer(int playerId) {
        players.put(playerId, new Player(playerId));
    }

    /**
     * Usuwa gracza z rozgrywki.
     *
     * @param playerId id gracza którego usunąć
     */
    public boolean removePlayer(int playerId) {

        players.remove(playerId);

        if (playerId == whoseTurn()) {
            turns.nextTurn();
            return true;
        }

        return false;
    }

}
