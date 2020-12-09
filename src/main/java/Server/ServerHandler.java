package Server;

import Communication.Information;
import Communication.Message;
import Connection.IConnectionService;

import java.util.Scanner;

public class ServerHandler extends Handler {


    public ServerHandler(IConnectionService service) {
        super(service);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            Message a = (Message) connectionService.receiveObject();

            if(a.getType().equals("INFORMATION") && a instanceof Information)
            {
                Information b = (Information) a;
                System.out.println("odebralem: " + b.getMessage());
            }
        }
    }

    @Override
    public void disconnect() {
        isEnd = true;
        //moze jakies info wyslac???
        closeConnection();
    }
}
