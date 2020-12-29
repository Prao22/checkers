package Communication;

/**
 * Odpowiedź od serwera czy wysłany wcześniej
 * ruch jest poprawny.
 */
public class Answer extends GameMessage {

    private final boolean answer;

    public Answer(boolean answer) {
        super(GameMessageType.ANSWER);
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
        return "Answer{" +
                "answer=" + answer +
                '}';
    }
}
