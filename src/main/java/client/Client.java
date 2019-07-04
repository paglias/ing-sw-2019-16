package client;

import utils.Constants;
import utils.Logger;

import java.io.IOException;


public class Client {
    private static Controller controller;

    private Client() { throw new IllegalStateException("Client class cannot be instantiated."); }

    /**
     * Quit client from the game.
     */
    public static void quit () {
        Logger.info("To reconnect to the game," +
                " join the same server with your previous username.");

        System.exit(0);
    }

    /**
     * Connect the host to the game.
     *
     * @param host the host
     * @param port the port
     */
    public static void connect (String host, int port) {
        controller.connect(host, port);
    }

    /**
     * Main class for the client of the game.
     *
     * @param args the input arguments
     *
     */
    public static void main (String[] args) {
        Constants.load(args);

        Logger.info("Welcome to the Adrenaline Client!");

        Controller controller = new Controller();
        Client.controller = controller;
        controller.init();
    }
}
