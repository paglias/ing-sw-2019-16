package client;

import utils.Constants;
import utils.Logger;
import utils.Utils;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private CLI () { throw new IllegalStateException("CLI class cannot be instantiated."); }

    public static void quit () {
        Logger.info("To reconnect to the game," +
                " join the same server with your previous username.");

        System.exit(0);
    }

    private static void connect (String host, int port) throws IOException {
        Connection connection = null;

        try {
            Logger.info("Connecting to server at " + host + ":" + port);

            connection  = new Connection(host, port);
            connection.init();

            Controller controller = new Controller(connection);
            controller.init();

            // Handle messages from the server
            String msg;
            do {
                msg = connection.receive();
                if (msg != null) controller.onServerMessage(msg);
            } while (msg != null);
        } catch (Throwable e) {
            Logger.err(e, null);
        } finally {
            if (connection != null) connection.close();
            CLI.quit();
        }
    }

    /**
     * Main class for the client of the game.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main (String[] args) throws IOException {
        Constants.load();

        Logger.info("Welcome to the Adrenaline Client!");

        Logger.info("Provide host:port of the server you want to connect to:");
        Scanner keyboard = Utils.getKeyboard();
        String[] tokens = keyboard.next().split(":");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("Bad formatting: " + tokens[0]);
        }

        String host = tokens[0];
        int port = Integer.parseInt(tokens[1]);

        CLI.connect(host, port);
    }
}
