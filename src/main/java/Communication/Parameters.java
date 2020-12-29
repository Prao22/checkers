package Communication;

import Game.GameParameters;

/**
 * Wiadomość zawiera parametry gry.
 * Serwer wysyła tę wiadomość do każdego gracza
 * przed rozpoczenciem gry.
 */
public class Parameters extends GameMessage {

    /**
     * Parametry gry która będzie hostowana na serwerze.
     */
    private final GameParameters parameters;

    public Parameters(GameParameters parameters) {
        super(GameMessageType.PARAMETERS);
        this.parameters = parameters;
    }

    public GameParameters getParameters() {
        return parameters;
    }
}
