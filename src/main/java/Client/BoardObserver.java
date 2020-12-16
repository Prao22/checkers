package Client;

import Client.GUI.Board;

public interface BoardObserver {
    void clickNotify(int row, int col);
    void attach(Board board);
}
