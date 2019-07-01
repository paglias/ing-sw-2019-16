package utils;

import java.util.Scanner;

public class Utils {
    private Utils () {} // Cannot be instantiated

    /**
     * Keep a reference to the open keyboard because after closing a Scanner it cannot be reopened easly.
     */
    private static Scanner keyboard = null;

    /**
     * Get the keyboard scanner.
     *
     * @return the scanner
     */
    public static Scanner getKeyboard () {
        if (Utils.keyboard == null) {
            Utils.keyboard = new Scanner(System.in);
        }

        return Utils.keyboard;
    }
}
