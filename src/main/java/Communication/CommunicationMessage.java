package Communication;

public abstract class CommunicationMessage extends Message {

    private CommunicationMessageType communicationMessageType;

    CommunicationMessage(CommunicationMessageType communicationMessageType) {
        super(MessageType.COMMUNICATION);
        this.communicationMessageType = communicationMessageType;
    }

    public CommunicationMessageType getCommunicationMessageType() {
        return communicationMessageType;
    }

    @Override
    public String toString() {
        return "CommunicationMessage{" +
                "communicationMessageType=" + communicationMessageType +
                '}';
    }
}
