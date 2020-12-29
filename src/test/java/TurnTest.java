import Game.*;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.HashMap;
import java.util.Map;

public class TurnTest {

    @Test
    public void nextTurnTest() {
        Map<Integer, Player> players = new HashMap<>();
        players.put(1, new Player(1));
        players.put(2, new Player(2));

        Turn turn = new Turn(players);
        int pl = turn.whoseTurn();

        turn.nextTurn();

        assert turn.whoseTurn() == pl % 2 + 1;
    }

    @Test
    public void nextTurnWithDisconnect() {
        Map<Integer, Player> players = new HashMap<>();
        players.put(1, new Player(1));
        players.put(2, new Player(2));
        players.put(3, new Player(3));

        Turn turn = new Turn(players);
        int pl = turn.whoseTurn();

        turn.nextTurn();

        assert turn.whoseTurn() == pl % 3 + 1;

        players.remove(1);

        for(int i = 0 ; i < 10; i++) {
            turn.nextTurn();
            assert turn.whoseTurn() != 1 && turn.whoseTurn() != -1;
        }

        players.remove(2);


        turn.nextTurn();
        assert turn.whoseTurn() == 3;

        players.clear();
        turn.nextTurn();
        assert turn.whoseTurn() == -1;

    }

    @Test
    public void maxPlayers() {
        Map<Integer, Player> players = new HashMap<>();
        players.put(1, new Player(1));
        players.put(2, new Player(2));
        players.put(3, new Player(3));
        players.put(4, new Player(4));
        players.put(5, new Player(5));
        players.put(6, new Player(6));


        Turn turn = new Turn(players);

        for(int i = 0; i < 10; i++) {
            turn.nextTurn();
            assert turn.whoseTurn() > 0 && turn.whoseTurn() <= 6;
        }
    }
}
