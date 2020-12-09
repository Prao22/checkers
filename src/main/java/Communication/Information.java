package Communication;

public class Information extends Message {

    private String information;

    public Information(String message) {
        super("INFORMATION");
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
