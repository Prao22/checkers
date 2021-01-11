package Communication;

import Game.GameParameters;

public class ReplayParameters extends ReplayMessage {

    private final GameParameters parameters;

    public ReplayParameters(GameParameters parameters) {
        super(ReplayMessageType.PARAMETERS);
        this.parameters = parameters;
    }

    public GameParameters getParameters() {
        return parameters;
    }
}
