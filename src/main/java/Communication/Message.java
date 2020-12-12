package Communication;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private MessageType type;

    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type.name() + '\'' +
                '}';
    }
}
