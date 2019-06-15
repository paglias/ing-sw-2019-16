package client;

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
            System.out.println("Connecting to server at " + host + ":" + port);

            connection  = new Connection(host, port);
            connection.init();

            Controller controller = new Controller(connection, keyboard);
            controller.init();

            // Handle messages from the server
            String msg;

            // TODO thread?
            do {
                msg = connection.receive();
                if (msg != null) controller.onServerMessage(msg);
            } while (msg != null);

        } catch (IOException e) {
            System.err.println("IOException " + e.getMessage());
        } finally {
            if (!connection.isClosed()) connection.close();
        }
    }

    public static void startCli (Scanner keyboard) throws IOException {
        System.out.println("Provide host:port please");
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
