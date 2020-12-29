package Communication;

/**
 * Jeśli wiadomość jest wysyłana od klienta do serwera,
 * oznacza, że klient chce wykonać taki ruch.
 * Jeśli serwer wysyła tą wiadomość do klienta oznacza to,
 * że ruch zawierający się w wiadmości został wykonany i
 * gracze powinni zaakualiziować swoje plansze.
 */
public class Move extends GameMessage {

    private final Game.Move move;

    public Move(Game.Move move) {
        super(GameMessageType.MOVE);
        this.move = move;
    }

    public Game.Move getMove() {
        return move;
    }
}
