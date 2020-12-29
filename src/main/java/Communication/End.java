package Communication;


/**
 * Wiadomość informuje, że wysyłający rozłącza się z odbierającym.
 */
public class End extends CommunicationMessage {

    public End(){
        super(CommunicationMessageType.END);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
