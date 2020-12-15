package Client.InitConnection;

import Client.Connectable;

public class ServerConnector {
    private final Connectable client;

    public ServerConnector(Connectable toConnect) {
        client = toConnect;
    }

    public boolean connectionRequest(String ip, String port) {
        int parsedPort = -1;

        try {
            parsedPort = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            return false;
        }

        return client.connect(ip, parsedPort);
    }

}
