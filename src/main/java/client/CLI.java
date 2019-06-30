package client;

import utils.Logger;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private Connection connection;
    private Scanner keyboard;

    public  CLI (Scanner keyboard) {
        this.keyboard = keyboard;
    }

    void connect (String host, int port) throws IOException {
        try {
            Logger.info("Connecting to server at " + host + ":" + port);

            connection  = new Connection(host, port);
            connection.init();

            Controller controller = new Controller(connection, keyboard);
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
            if (!connection.isClosed()) connection.close();
            Logger.info("To reconnect to the game," +
                    " join the same server with your previous username.");

            System.exit(0);
        }
    }

    public static void startCli (Scanner keyboard) throws IOException {
        Logger.info("Provide host:port please");
        String[] tokens = keyboard.nextLine().split(":");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("Bad formatting: " + tokens[0]);
        }

        String host = tokens[0];
        int port = Integer.parseInt(tokens[1]);

        CLI cli = new CLI(keyboard);
        cli.connect(host, port);
    }
}
