package Communication;

/**
 * Gdy klient odbierze tę wiadomość oznacza to, że
 * teraz jest jego kolej na wykonanie ruchu.
 * Serwer tego nie obsługuje.
 */
public class YourTurn extends GameMessage {
    public YourTurn() {
        super(GameMessageType.YOUR_TURN);
    }
}
