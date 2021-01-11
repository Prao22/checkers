package Communication;

/**
 * Informacja dla klienta, że serwer jest w trybie wysyłania powtórek.
 */
public class ReplayMode extends CommunicationMessage {

    public ReplayMode() {
        super(CommunicationMessageType.REPLAY_MODE);
    }
}
