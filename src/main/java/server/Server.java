package server;

import controllers.GameController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Closeable {
    private final int port;
    private ServerSocket serverSocket;
    private ExecutorService pool;

    private Server (int port) {
        this.port = port;
        pool = Executors.newCachedThreadPool();
    }

    private Socket acceptConnection() throws IOException {
        // blocking call
        Socket accepted = serverSocket.accept();
        System.out.println("Connection accepted: " + accepted.getRemoteSocketAddress());
        return accepted;
    }

    private void lifeCycle() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        // Create a GameController controller
        // It's created here to make it possible to share it between clients
        // but then it's only handled in a ClientHandler class that runs in a different thread

        // TODO support multiple games
        GameController gameController = new GameController();

        boolean condition = true;
        while (condition) {
            Socket clientSocket = acceptConnection();

            pool.submit(() -> {
                try {
                    ClientHandler clientHandler = new ClientHandler(clientSocket, gameController);
                    clientHandler.handleConnection();
                } catch (IOException e) {
                    System.err.println("Problem closing client " + clientSocket.getLocalAddress() + ": " + e.getMessage());
                }
            });
        }
    }

    public void close() throws IOException {
        // TODO notify clients
        System.out.println("Server is shutting down...");
        serverSocket.close();
    }

    public static void main (String[] args) throws IOException {
        System.out.println("Server is starting...");

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