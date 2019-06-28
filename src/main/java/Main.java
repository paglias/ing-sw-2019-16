import client.CLI;
import server.Server;
import utils.Constants;
import utils.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {
        Constants.load();

        Scanner keyboard = new Scanner(System.in);
        Logger.info("Welcome to Adrenaline!");
        Logger.info("Choose 1 for the server, 2 for the client:");
        int choice = Integer.parseInt(keyboard.nextLine());

        if (choice == 1) {
            Server.start(keyboard);
        } else if (choice == 2) {
            CLI.startCli(keyboard);
            keyboard.close();
        }
    }
}
