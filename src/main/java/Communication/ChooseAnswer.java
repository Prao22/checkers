package Communication;

public class ChooseAnswer extends ReplayMessage {
    private final boolean answer;

    public ChooseAnswer(boolean answer) {
        super(ReplayMessageType.ANSWER);
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }

    @Override
    public MessageType getType() {
        return super.getType();
    }

    @Override
    public String toString() {
        return "ChooseAnswer{" +
                "answer=" + answer +
                '}';
    }
}
