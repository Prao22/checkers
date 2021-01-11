package Communication;

import Game.GameParameters;

import java.util.List;
import java.util.Map;

public class ListOfGames extends ReplayMessage {

    private final Map<Integer, GameParameters> games;

    public ListOfGames(Map<Integer, GameParameters> games) {
        super(ReplayMessageType.LIST_OF_GAMES);
        this.games = games;
    }

    public Map<Integer, GameParameters> getGames() {
        return games;
    }
}
