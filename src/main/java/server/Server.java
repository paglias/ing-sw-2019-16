package server;

import utils.Constants;
import utils.Logger;
import utils.Utils;

import java.io.Closeable;
import java.io.IOException;
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
     * Main class for the server of the game.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main (String[] args) throws IOException {
        Constants.load();

        Logger.info("Welcome to the Adrenaline Server!");
        Logger.info("Server is starting...");

        Logger.info("Choose a port:");
        Scanner keyboard = Utils.getKeyboard();

        int chosenPort = Integer.parseInt(keyboard.next());

        Server server = new Server(chosenPort);
        try {
            server.lifeCycle();
        } catch (Throwable e) {
            Logger.err(e, "Exception in server.lifeCycle!");
        } finally {
            Logger.info("Server is closing.");
            server.close();
        }
    }
}