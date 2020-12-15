package Utility;

public class Log {

    public static boolean logFlag = true;

    public static void log(String log) {
        if (logFlag) System.out.println(log);
    }

    public static void err(String err) {
        System.out.println("[error] " + err);
    }
}
