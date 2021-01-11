package Client;

import Game.CounterColor;
import Game.Move;

public interface IController {
    CounterColor getColor();
    void setMove(Game.Move move);
    void nextMove();
    void close();
}
