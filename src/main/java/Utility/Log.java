package Utility;

/**
 * Prosta klasa do wysyłania odpowiednio sformatowanych
 * logów lub błedów na ekran konsoli.
 */
public class Log {

    /**
     * Czy logi mają być wyświetlane.
     */
    public static boolean logFlag = true;

    public static void log(String log) {
        if (logFlag) System.out.println(log);
    }

    public static void err(String err) {
        System.out.println("[error] " + err);
    }
}
