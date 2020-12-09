package Server;

import Communication.Information;
import Communication.Message;
import Connection.IConnectionService;

import java.util.Scanner;

public class ClientHandler extends Handler {


    public ClientHandler(IConnectionService service) {
        super(service);
    }


    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);

        while (isEnd) {
            Information a = (Information) connectionService.receiveObject();
            System.out.println("odebralem: " + a);
        }
    }

    public void disconnect() {
        isEnd = true;
        closeConnection();
    }
}
