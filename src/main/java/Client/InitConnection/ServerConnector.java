package Client.InitConnection;

import Client.Connectable;

/**
 * Klasa przy pomocy której użytkownik łączy się z serwerem
 *
 *
 */
public class ServerConnector {
	/**
	 * Klient który połączy się z serwerem.
	 */
    private final Connectable client;

    public ServerConnector(Connectable toConnect) {
        client = toConnect;
    }

    /**
     * 
     * @param ip Ip serwera z którym chcemy się połączyć.
     * @param port Port serwera z którym chcemy się połączyć
     * @return Zwraca wartość true jeżeli udało nawiąza się połączenie, false w wypadku przeciwnym.
     */
    		
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
