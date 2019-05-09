package client;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

            // TODO using threads below! synchronize!
            CliView cliView = new CliView(connection, keyboard);

            // Handle incoming data
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    while (true) {
                        String received = connection.receive();
                        cliView.onServerMessage(received);
                    }
                } finally {
                    // Don't do anything, the main thread will take care of closing the connection
                }
            });

            cliView.init();
        } catch (IOException e) {
            System.err.println("IOException " + e.getMessage());
        } finally {
            if (!connection.isClosed()) connection.close();
        }
    }

    public static void main(String[] args)  throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Provide host:port please");
        String[] tokens = keyboard.nextLine().split(":");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("Bad formatting: " + args[0]);
        }

        String host = tokens[0];
        int port = Integer.parseInt(tokens[1]);

        CLI cli = new CLI(keyboard);
        cli.connect(host, port);
    }
}
