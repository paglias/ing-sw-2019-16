package client;

import java.io.IOException;
import java.util.Scanner;

public class CliView {
    Connection connection;
    Scanner keyboard;

    public CliView(Connection connection, Scanner keyboard) {
        this.connection = connection;
        this.keyboard = keyboard;
    }

    public void onServerMessage (String msg) {
        System.out.println("From server >>> " + msg);
    }

    String waitForUserInput () {
        return keyboard.nextLine();
    }

    public void init () {
        System.out.println("Welcome to the CLI version of Adrenaline!");

        boolean condition = true;
        while (condition) {
            System.out.println("Send some command:");
            String command = waitForUserInput();
            System.out.println("You asked for >>> " + command);
        }
    }
}
