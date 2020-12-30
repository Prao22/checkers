package Client;

import Game.CounterColor;
import Game.Move;

public interface GameController {
    CounterColor getColor();
    void setMove(Game.Move move);
    void close();
}
