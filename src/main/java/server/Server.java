package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
// TODO threads import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Closeable {
    private final int port;
    private ServerSocket serverSocket;
    // TODO threads private ExecutorService pool;

    public Server (int port) {
        System.out.println("closed");
        this.port = port;
        // TODO threads pool = Executors.newCachedThreadPool();
    }

    public void init () throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("server.Server listening on port " + port);
    }

    public Socket acceptConnection() throws IOException {
        // blocking call
        Socket accepted = serverSocket.accept();
        System.out.println("Connection accepted: " + accepted.getRemoteSocketAddress());
        return accepted;
    }

    public void lifeCycle() throws IOException {
        init();

        while (true) { //NOSONAR
            final Socket socket = acceptConnection();

            // TODO this is a thread, synchronize methods
            // TODO threads  pool.submit(() -> {
                ClientHandler clientHandler = new ClientHandler(socket);
                try {
                    clientHandler.handleConnection();
                } catch (IOException e) {
                    System.err.println("Problem with " + socket.getLocalAddress() + ": " + e.getMessage());
                }
            // TODO threads  });
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public static void main (String[] args) throws IOException {
        System.out.println("Starting...");

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a port");
        int chosenPort = Integer.parseInt(keyboard.nextLine());
        keyboard.close();

        Server server = new Server(chosenPort);
        try {
            server.lifeCycle();
        } catch (IOException e) {
            System.out.println("IOException in server.lifeCycle!");
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}