package Communication;

public class Information extends CommunicationMessage {

    private final String information;

    public Information(String message) {
        super(CommunicationMessageType.INFORMATION);
        information = message;
    }

    public String getMessage() {
        return information;
    }

    @Override
    public String toString() {
        return "Information{" +
                "information='" + information + '\'' +
                '}';
    }
}
