package Client;

public interface GUIService {
    void makeMove(Game.Move move);
    void showError(String errorText);
    boolean showQuestion(String question);
    void updateFooter(int onlinePlayers, int maxPlayers, String text);
    void updateFooter(String text);
    void updatePlayerInfo(int playerId);
    void updateTurnInfo(boolean turn);
    void showInfo(String info);
    void setBoardParameters(int size, int numberOfPlayers, int numberOfCounters);
    void resetClicks();
    void repack();
}
