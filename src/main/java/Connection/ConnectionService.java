package Connection;

import java.io.*;
import java.net.Socket;

public class ConnectionService implements IConnectionService {

    protected Socket socket;

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

    @Override
    public Object receiveObject() {
        try {
            return inObjects.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            return null;
        }
    }

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
