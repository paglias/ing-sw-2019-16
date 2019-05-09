package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Closeable {
    private final String host;
    private final int port;
    private Socket connection;
    private BufferedReader in;
    private PrintWriter out;

    Client (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void init () throws IOException {
        connection = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // auto-flushing, yeah B)
        out = new PrintWriter(connection.getOutputStream(), true);
    }

    public String receive () throws IOException {
        return in.readLine();
    }

    public void send (String message) {
        out.println(message);
    }

    public void close() throws IOException {
        in.close();
        out.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Provide host:port please");
        String[] tokens = keyboard.nextLine().split(":");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("Bad formatting: " + args[0]);
        }

        String host = tokens[0];
        int port = Integer.parseInt(tokens[1]);

        Client client = new Client(host, port);

        try {
            client.init();

            String received = null;

            do {
                System.out.println(">>> Provide line:");
                String toSend = keyboard.nextLine();
                client.send(toSend);
                received = client.receive();
                System.out.println(received);
            } while (true);
        } finally {
            client.close();
        }
    }
}