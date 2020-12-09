package Server;

import Communication.Information;
import Connection.ConnectionService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public ServerHandler handler;

    public Client(String ip, int port) throws IOException {
        handler = new ServerHandler(new ConnectionService(new Socket(ip, port)));
        handler.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Client c = new Client("127.0.0.1", 59001);

        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.println("co wyslac: ");
            c.handler.sendMessage(new Information(scanner.nextLine()));
        }

    }
}
