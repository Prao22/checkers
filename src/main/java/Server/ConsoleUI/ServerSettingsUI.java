package Server.ConsoleUI;

import Game.GameParameters;
import Server.*;

public class ServerSettingsUI extends ConsoleUI {

    Server watchedServer;
    GameParameters gameParameters;

    public ServerSettingsUI(Server server, GameParameters gameParameters) {
        watchedServer = server;
        this.gameParameters = gameParameters;
    }

    void loop() {

        while (true) {
            clear();
            printInBorder("USTAWIENIA");

            if(watchedServer.isRunning()) {
                System.out.println("Nie mozna zmienac ustawien gdy serwer chodzi!");
                scanner.nextLine();
                return;
            }

            printMenu(new String[]{"Port: " + watchedServer.getPort(),
                                   "Liczba graczy: " + watchedServer.getMaxClients(),
                                   "Liczba pól: " + gameParameters.getNumberFields(),
                                   "Liczba pionków: " + gameParameters.getNumberCounter(), "" +
                                   "Blokady: " + gameParameters.isBlocks(),
                                   "Powrót"});

            int choose = getInt();


            switch (choose) {
                case 1:
                    changePort();
                    break;
                case 2:
                    changeMaxPlayers();
                    break;
                case 3:
                    changeFields();
                    break;
                case 4:
                    changeCounters();
                    break;
                case 5:
                    changeBlock();
                    break;
                case 6:
                    return;
            }
        }
    }

    private void changeBlock() {
        System.out.print("Podaj czy mozna blokowac [1/0]: ");

        int block = getInt();

        if (block == 0 || block == 1) {
            gameParameters.setBlocks(block == 1);
        }
    }

    private void changeCounters() {
        System.out.print("Podaj ile ma być pionków: ");

        int counters = getInt();

        if (counters > 0) {
            gameParameters.setNumberCounter(counters);
        }
    }

    private void changeFields() {
        System.out.print("Podaj ile ma być pól na boku gwiazdy: ");

        int field = getInt();

        if (field > 0) {
            gameParameters.setNumberFields(field);
        }
    }

    private void changeMaxPlayers() {
        System.out.print("Podaj ile ma być graczy: ");

        int players = getInt();

        if (players > 1 && players < 7) {
            gameParameters.setNumberPlayers(players);
        }
    }

    private void changePort() {
        System.out.print("Podaj nowy port: ");
        int port = getInt();

        if (port > 0) {
            watchedServer.setPort(port);
        }
    }
}
