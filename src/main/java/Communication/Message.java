package Communication;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private String type;

    public Message(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                '}';
    }
}
