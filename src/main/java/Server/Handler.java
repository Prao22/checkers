package Server;

import Communication.Message;
import Connection.IConnectionService;

public abstract class Handler extends Thread {

    protected IConnectionService connectionService;
    protected boolean isEnd = false;

    public Handler(IConnectionService service) {
        this.connectionService = service;
    }

    public boolean sendMessage(Message message) {
        return connectionService.sendObject(message);
    }

    public abstract void disconnect();

    public void closeConnection() {
        connectionService.closeConnection();
    }
}
