package Connection;

import Communication.Message;
import Connection.IConnectionService;
import Utility.Log;

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

    public boolean isEnd() {
        return isEnd;
    }

    public void closeConnection() {
        isEnd = true;
        //Log.log("Zamykam połączenie...");
        connectionService.closeConnection();
        //Log.log("Zamknięto!");
    }
}
