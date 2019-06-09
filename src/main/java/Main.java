import client.CLI;
import client.views.Game;
import server.Server;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to Adrenaline!");
        System.out.println("Choose 1 for the server, 2 for the client:");
        int choice = Integer.parseInt(keyboard.nextLine());

        if (choice == 1) {
            Server.start(keyboard);
        } else if (choice == 2) {
            CLI.startCli(keyboard);
            keyboard.close();
        }
    }
}
