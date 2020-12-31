package Connection;

/**
 * Interfejs do łączenia się po ip i porcie.
 */
public interface Connectable {
    /**
     * @param ip   IP serwera
     * @param port port serwera
     * @return czy połączenie się udało
     */
    boolean connect(String ip, int port);
}
