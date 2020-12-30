package Server.ConsoleUI;

import Server.Server;

public class ServerConsoleUI extends ConsoleUI implements Runnable {

    private final Server watchedServer;
    private final ServerSettingsUI settingsUI;
    private final Object serverLock;

    public ServerConsoleUI(Server server, Object lock) {
        watchedServer = server;
        settingsUI = new ServerSettingsUI(server, watchedServer.getGameParameters());
        serverLock = lock;
    }

    @Override
    public void run() {
        while (true) {
            clear();
            printInBorder("SERWER");
            printMenu(new String[]{"Ustawienia", "Stan: " + (watchedServer.isRunning() ? "ON" : "OFF"),
                    watchedServer.isRunning() ? "-" : "Start",
                    "Połączonych graczy: " + watchedServer.getOnlineClients() + "/" + watchedServer.getMaxClients(), "Zakoncz"});

            System.out.println("Aby odświeżyć informacje o serwerze kliknij cokolwiek i zatwierdz enterem.");

            int choose = getInt();

            switch (choose) {
                case 1:
                    settingsUI.loop();
                    break;
                case 3:
                    if (watchedServer.isRunning() || !watchedServer.changeState()) {
                        System.out.println("Nie udało się zmienić stanu!");
                    } else {
                        synchronized (serverLock) {
                            serverLock.notify();
                        }
                    }
                    break;
                case 5:
                    if (watchedServer.isRunning()) {
                        watchedServer.changeState();
                    }
                    watchedServer.setEnd(true);
                    return;
            }
        }
    }
}
