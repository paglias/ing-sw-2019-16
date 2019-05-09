package client;

import java.util.Scanner;

class CliView {
    private Connection connection;
    private Scanner keyboard;

    CliView(Connection connection, Scanner keyboard) {
        this.connection = connection;
        this.keyboard = keyboard;
    }

    void onServerMessage (String msg) {
        System.out.println("From server >>> " + msg);
    }

    private String waitForUserInput () {
        return keyboard.nextLine();
    }

    void init () {
        System.out.println("Welcome to the CLI version of Adrenaline!");

        boolean condition = true;
        while (condition) {
            System.out.println("Send some command:");
            String command = waitForUserInput();
            System.out.println("You asked for >>> " + command);
            connection.send(command);
        }
    }
}
