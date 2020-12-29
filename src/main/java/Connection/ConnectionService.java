package Connection;

import java.io.*;
import java.net.Socket;

/**
 * Obsługuje połączenie z klientem/serwerem połączonym
 * za pomocą socketa.
 */
public class ConnectionService implements IConnectionService {

    /**
     * Socket dzięki któremu jesteśmy połączeni z drugą stroną.
     */
    protected Socket socket;

    /**
     * Strumienie potrzebne do odbierania i wysyłania obiektów.
     */
    protected InputStream in;
    protected OutputStream out;
    protected ObjectOutputStream outObjects;
    protected ObjectInputStream inObjects;

    public ConnectionService(Socket socket) throws IOException {
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
        outObjects = new ObjectOutputStream(out);
        outObjects.flush();
        inObjects = new ObjectInputStream(in);
    }

    /**
     * Wysłanie obiektu do połączonego z nami socketu.
     *
     * @param object obiekt który chcemy wysłać.
     * @return czy wysyłka się powiodła
     */
    @Override
    public boolean sendObject(Object object) {
        try {
            outObjects.writeObject(object);
            outObjects.reset();
            outObjects.flush();
        } catch (IOException exception) {
            return false;
        }

        return true;
    }

    /**
     * Odbiór obiektu wysłanego przez połączonego
     * z nami socketa. Gdy nie ma żadnego obieku w buforze
     * funkcja blokuje wątek dopóki żaden się nie pojawi.
     *
     * @return obiekt który został wysłany
     */
    @Override
    public Object receiveObject() {
        try {
            return inObjects.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            return null;
        }
    }

    /**
     * Zamyka połączenie z połączonym z nami socketem.
     */
    @Override
    public void closeConnection() {
        try {
            inObjects.close();
            in.close();
            outObjects.close();
            out.close();
            socket.close();
        } catch (IOException ignored) {
        }
    }
}
