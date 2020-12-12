package Server.ConsoleUI;

import java.util.Scanner;

public class SingletonScanner {

    private static Scanner scanner = new Scanner(System.in);

    private SingletonScanner() {
    }

    public static Scanner getInstance() {
        return scanner;
    }
}
