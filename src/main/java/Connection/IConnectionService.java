package Connection;

public interface IConnectionService {

    boolean sendObject(Object object);
    Object receiveObject();
    void closeConnection();

}
