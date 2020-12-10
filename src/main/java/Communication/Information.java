package Communication;

public class Information extends Message {

    private final String information;

    public Information(String message) {
        super(MessageType.INFORMATION);
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
