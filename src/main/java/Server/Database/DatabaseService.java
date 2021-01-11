package Server.Database;

import Game.GameParameters;

import java.util.Map;

public interface DatabaseService {
    /**
     * Tworzy gre w bazie.
     * @param parameters parametry tej gry
     * @return id gry w bazie
     */
    int newGame(GameParameters parameters);

    /**
     * Zapisuje ruch do danej gry.
     * @param move ruch który ma być zapisany.
     * @param gameId id gry której ruch dotyczy.
     */
    void saveNextMove(Game.Move move, int gameId);

    /**
     * Pobiera parametry danej gry.
     * @param gameId id gry której parametry chcemy pobrać.
     * @return parametry gry o podanym id. Null jeśli gra nie istnieje.
     */
    GameParameters getGameParameters(int gameId);

    /**
     * Pobiera ruch z bazy.
     * @param gameId id gry z której ruch checemy pobrać.
     * @param whichMove nr ruchu który checmy pobrać.
     * @return ruch z danej gry lub null jeśli ruch o danych parametrach nie istnieje.
     */
    Game.Move getMove(int gameId, int whichMove);

    /**
     * Zwraca wszystkie gry jakie przechowuje baza.
     * @return parametry gry jako wartość, kluczem jest id gry.
     */
    Map<Integer, GameParameters> listAllGames();
}
