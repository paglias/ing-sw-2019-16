package utils;

public class Logger {
    private Logger () {} // Cannot instantiate this class

    public static void info (String s) {
        System.out.println(s);
    }

    public static void err (Throwable e, String s) {
        e.printStackTrace();

        String eMsg = e.getMessage();
        if (eMsg != null) {
            System.err.println(e.getMessage());
        }
        if (s != null) {
            System.err.println(s);
        }
    }
}
