package Server.ConsoleUI;

import java.util.Scanner;

public abstract class ConsoleUI extends Thread {
    protected Scanner scanner = SingletonScanner.getInstance();

    protected void printInBorder(String string) {
        final int borderLength = (string.length() * 2) + (string.length() % 2);
        final int indentation = string.length() / 2 + (string.length() % 2);

        System.out.print("\t");

        for (int i = 0; i < borderLength; i++) {
            System.out.print("*");
        }

        System.out.println();
        System.out.print("\t");

        for (int i = 0; i < indentation; i++) {
            System.out.print("-");
        }

        System.out.print(string);

        for (int i = 0; i < indentation; i++) {
            System.out.print("-");
        }

        System.out.println();
        System.out.print("\t");

        for (int i = 0; i < borderLength; i++) {
            System.out.print("*");
        }

        System.out.println();
    }

    protected void printMenu(String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            System.out.println("\t[" + (i + 1) + "] " + elements[i]);
        }
    }

    protected int getInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            return -1;
        }
    }

    protected void clear() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n\n\n\n\n\n\n");
        }
    }
}
