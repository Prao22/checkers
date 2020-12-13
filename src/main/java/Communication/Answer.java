package Communication;

public class Answer extends Message {

    private final boolean answer;

    public Answer(boolean answer) {
        super(null);
        this.answer = answer;
    }

    @Override
    public MessageType getType() {
        return super.getType();
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer=" + answer +
                '}';
    }
}
