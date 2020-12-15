package Communication;

import Game.GameParameters;

public class Parameters extends GameMessage {
    private GameParameters parameters;

    public Parameters(GameParameters parameters) {
        super(GameMessageType.PARAMETERS);
        this.parameters = parameters;
    }

    public GameParameters getParameters() {
        return parameters;
    }
}
