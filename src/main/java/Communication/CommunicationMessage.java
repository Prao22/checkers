package Communication;

/**
 * Wiadomość dotycząca połączenia między klientem a serwerem.
 */
public abstract class CommunicationMessage extends Message {

    /**
     * Szczegółowa informacja czego dotyczy wiadomość.
     */
    private final CommunicationMessageType communicationMessageType;

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
