package server;

import controllers.GameController;
import utils.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
        Logger.info("Connection accepted: " + accepted.getRemoteSocketAddress());
        return accepted;
    }

    /**
     * List for new connections to the server as long as it's open.
     * When a client connect spawn a new thread and create a client handler.
     */
    private void lifeCycle() throws IOException {
        serverSocket = new ServerSocket(port);
        Logger.info("Server listening on port " + port);

        boolean condition = true;
        while (condition) {
            Socket clientSocket = acceptConnection();

            pool.submit(() -> {
                try {
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clientHandler.handleConnection();
                } catch (Throwable e) {
                    Logger.err(e, "Problem! closing client " + clientSocket.getLocalAddress());
                }
            });

            if (serverSocket.isClosed()) condition = false;
        }
    }

    /**
     * Close the game server.
     * Destroy the socket.
     */
    public void close() throws IOException {
        Logger.info("Server is shutting down...");
        serverSocket.close();
    }

    /**
     * Start the game server.
     * Ask for connection string.
     *
     *
     * @param keyboard a keyboard scanner
     * @throws IOException an io exception
     */
    public static void start (Scanner keyboard) throws IOException {
        Logger.info("Server is starting...");
        Logger.info("Choose a port:");
        int chosenPort = Integer.parseInt(keyboard.nextLine());
        keyboard.close();

        Server server = new Server(chosenPort);
        try {
            server.lifeCycle();
        } catch (Throwable e) {
            Logger.err(e, "Exception in server.lifeCycle!");
        } finally {
            server.close();
        }
    }
}