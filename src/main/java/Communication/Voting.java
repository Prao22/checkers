package Communication;

public class Voting extends GameMessage {

    private final String question;

    public Voting(String question) {
        super(GameMessageType.VOTING);
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
