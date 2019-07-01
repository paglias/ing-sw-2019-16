package server;

import controllers.ClientController;
import messages.AbstractMessage;
import messages.ErrorMessage;
import utils.Constants;
import utils.Logger;

import java.io.*;
import java.net.Socket;

/**
 * The type Client handler.
 */
public class ClientHandler {
    private Socket clientSocket;
    private ClientController clientController;

    // Not used to write or read
    private InputStream inputStream;
    private OutputStream outputStream;

    // To write and read
    private BufferedReader readStream;
    private PrintWriter writeStream;

    /**
     * Instantiates a new Client handler.
     *
     * @param socket the socket
     */
    ClientHandler (Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Create the input and output streams.
     * Create a controller for the client.
     * Read all data and messages from the client.
     * Manage disconnection.
     *
     * @throws IOException the io exception
     */
    void handleConnection() throws IOException {
        try {
            // Communication streams
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            readStream = new BufferedReader(new InputStreamReader(inputStream));
            writeStream = new PrintWriter(outputStream);

            Logger.info("Connected client " + clientSocket.getRemoteSocketAddress());

            clientController = new ClientController(this);

            // Handle incoming data from client
            String msg;

            do {
                msg = readStream.readLine();
                if (msg != null) handleMessage(msg);
            } while (msg != null);
        } catch (Throwable e) {
            Logger.err(e, "Problem with client " + clientSocket.getLocalAddress() + ": " + e.getMessage());
        } finally {
            Logger.info("Closing connection from client " + clientSocket.getLocalAddress());
            close();
            if (clientController != null && clientController.getGameController() != null) {
                clientController.getGameController().disconnectPlayer(clientController);
            }
        }
    }

    /**
     * Handle message from client to server.
     * Dispatch message to the right handler.
     *
     * @param msg the msg
     */
    void handleMessage (String msg) {
        if (Constants.DEBUG) Logger.info("From client >>> " + msg);

        try {
            AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
            parsedMsg.accept(clientController);
        } catch (Throwable e) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Error processing the action on the server, make sure it was possible");
            clientController.sendMsg(errorMessage);
            Logger.err(e, "Error handling client message " + clientSocket.getLocalAddress());
        }
    }

    /**
     * Send message to the client.
     *
     * @param msg the msg
     */
    public void sendMessage (String msg) {
        writeStream.println(msg);
        writeStream.flush();
    }

    /**
     * Close client connection.
     *
     * @throws IOException the io exception
     */
    public void close () throws IOException {
        Logger.info("Closing client " + clientSocket.getLocalAddress());

        if (clientController != null && clientController.getGameController() != null) {
            clientController.getGameController().disconnectPlayer(clientController);
        }

        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
        clientSocket.close();
    }
}
