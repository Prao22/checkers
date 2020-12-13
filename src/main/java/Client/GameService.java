package Client;

import Communication.GameMessage;

public interface GameService {
    void serviceMessage(GameMessage message);
    void showError(String err);
}
