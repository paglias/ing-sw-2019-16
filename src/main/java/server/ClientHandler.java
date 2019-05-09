package server;

// import common.Message;
import controllers.ClientsController;
import controllers.GameController;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private Socket clientSocket;
    private GameController gameController;
    private ClientsController clientsController;

    // Not used to write or read
    private InputStream inputStream;
    private OutputStream outputStream;

    // To write and read
    private BufferedReader readStream;
    private PrintWriter writeStream;

    ClientHandler (Socket socket, GameController gameController, ClientsController clientsController) {
        this.clientSocket = socket;
        this.gameController = gameController;
        this.clientsController = clientsController;
    }

    void handleConnection() throws IOException {
        try {
            // Communication streams
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            readStream = new BufferedReader(new InputStreamReader(inputStream));
            writeStream = new PrintWriter(outputStream);

            clientsController.addClient(this);
            System.out.println("Connected " + clientSocket.getRemoteSocketAddress());

            boolean condition = true;
            String msg;

            // TODO gameController.onConnection()

            do {
                msg = readStream.readLine();
                if (msg != null) {
                    System.out.println("Client sent >>> " + msg);
                    // TODO clientController.handleMessage(msg)
                }
            } while (condition && msg != null);
            // TODO set condition to false to disconnect the client
        } catch (IOException e) {
            System.err.println("Problem with client " + clientSocket.getLocalAddress() + ": " + e.getMessage());
        } finally {
            close();
        }
    }

    public void sendMessage (String msg) {
        writeStream.println(msg);
        writeStream.flush();
    }

    public void close () throws IOException {
        System.out.println("Closing client " + clientSocket.getLocalAddress());

        // First cleanup methods that don't throw exceptions
        clientsController.removeClient(this);
        // TODO notify game

        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
        clientSocket.close();
    }
}
