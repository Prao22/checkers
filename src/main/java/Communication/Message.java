package Communication;

import java.io.Serializable;

/**
 * Klasa która jest przesyłana między serwerem a klientami.
 */
public abstract class Message implements Serializable {

    /**
     * Typ wiadomości, czego dotyczy.
     * @see MessageType
     */
    private final MessageType type;

    public Message(final MessageType type) {
        this.type = type;
    }

    /**
     * Zwraca informacje czego dotyczy wiadomość.
     *
     * @return jakiego typu jest wiadomość.
     */
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
