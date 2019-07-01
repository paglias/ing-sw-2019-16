package utils;

public class Logger {
    private Logger () {} // Cannot instantiate this class

    /**
     * Log message to system.out.
     *
     * @param msg the message to log
     */
    public static void info (String msg) {
        System.out.println(msg);
    }

    /**
     * Log error to system.err.
     *
     * @param e the error object.
     * @param s the optional message.
     */
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
