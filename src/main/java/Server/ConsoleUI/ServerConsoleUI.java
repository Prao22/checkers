package Server.ConsoleUI;

import Server.Server;

public class ServerConsoleUI extends ConsoleUI {

    private final Server watchedServer;
    private final ServerSettingsUI settingsUI;

    public ServerConsoleUI(Server server) {
        watchedServer = server;
        settingsUI = new ServerSettingsUI(server, watchedServer.getGameParameters());
    }

    public void loop() {

        while (true) {
            clear();
            printInBorder("SERWER");
            printMenu(new String[]{"Ustawienia", "Stan: " + (watchedServer.isRunning() ? "ON" : "OFF"),
                    watchedServer.isRunning() ? "-" : "Start",
                    "Połączonych graczy: " + watchedServer.getOnlineClients() + "/" + watchedServer.getMaxClients(), "Podgląd", "Zakoncz"});

            int choose = getInt();

            switch (choose) {
                case 1:
                    settingsUI.loop();
                    break;
                case 3:
                    if(!watchedServer.changeState() || watchedServer.isRunning()) {
                        System.out.println("Nie udało się zmienić stanu!");
                    }
                    break;
                case 5:
                    serverLog();
                    break;
                case 6:
                    return;
            }
        }
    }

    private void serverLog() {
        System.out.print("ABY WYJŚĆ WPROWADŹ COKOLWIEK I KLIKNIJ ENTER!");
        watchedServer.setLogFlag(true);
        scanner.nextLine();
    }


}
