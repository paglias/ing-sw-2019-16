import client.Client;
import models.GameBoard;
import models.cards.Weapon;
import server.Server;
import utils.Constants;
import utils.Logger;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {

    /**
     * Main class of the app that allows to launch both the client and server.
     * For the client and sever specific main classes see the client and server packages.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main (String[] args) throws IOException {
        Constants.load(args);

        Scanner keyboard = Utils.getKeyboard();

        Logger.info("Welcome to Adrenaline!");
        Logger.info("Choose 1 for the server, 2 for the client:");
        int choice = Integer.parseInt(keyboard.next());

        if (choice == 1) {
            Server.main(null);
        } else if (choice == 2) {
            Client.main(null);
        }

        keyboard.close(); // close keyboard scanner at the end
    }
}
