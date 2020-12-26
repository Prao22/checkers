package Game;

import Utility.Log;

public class Counter {

    private final int playerId;
    private Field currentField;

    public Counter(int playerId) {
        this.playerId = playerId;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public boolean onRightPlace() {
        Log.log("player " + playerId + "   dest " + currentField.getDestination());
        return playerId == currentField.getDestination();
    }

    public int getPlayerId() {
        return playerId;
    }
}
