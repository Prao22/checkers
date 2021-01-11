package Communication;

public abstract class ReplayMessage extends Message{

    private final ReplayMessageType replayMessageType;

    public ReplayMessage(ReplayMessageType replayMessageType) {
        super(MessageType.REPLAY);
        this.replayMessageType = replayMessageType;
    }

    public ReplayMessageType getReplayMessageType() {
        return replayMessageType;
    }

    @Override
    public String toString() {
        return "ReplayMessage{" +
                "replayMessageType=" + replayMessageType +
                '}';
    }
}
