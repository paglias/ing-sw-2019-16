package server;

import controllers.ClientController;
import controllers.GameController;
import messages.AbstractMessage;
import utils.Constants;
import utils.Logger;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private Socket clientSocket;
    private GameController gameController;
    private ClientController clientController;

    // Not used to write or read
    private InputStream inputStream;
    private OutputStream outputStream;

    // To write and read
    private BufferedReader readStream;
    private PrintWriter writeStream;

    ClientHandler (Socket socket, GameController gameController) {
        this.clientSocket = socket;
        this.gameController = gameController;
    }

    void handleConnection() throws IOException {
        try {
            // Communication streams
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            readStream = new BufferedReader(new InputStreamReader(inputStream));
            writeStream = new PrintWriter(outputStream);

            Logger.info("Connected client " + clientSocket.getRemoteSocketAddress());

            clientController = new ClientController(gameController, this);

            // Handle incoming data from client
            String msg;

            do {
                msg = readStream.readLine();
                if (msg != null) handleMessage(msg);
            } while (msg != null);
        } catch (Exception e) {
            Logger.err(e, "Problem with client " + clientSocket.getLocalAddress() + ": " + e.getMessage());
        } finally {
            Logger.info("Closing connection from client " + clientSocket.getLocalAddress());
            close();
        }
    }

    void handleMessage (String msg) {
        if (Constants.DEBUG) Logger.info("From client >>> " + msg);
        // TODO handle errors from deserialization/handling
        AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);

        try {
            parsedMsg.accept(clientController);
        } catch (Exception e) {
            Logger.err(e, "Error handling client message " + parsedMsg.getClass().getName());
        }
    }

    public void sendMessage (String msg) {
        if (Constants.DEBUG) Logger.info("Sending message >>> " + msg);
        writeStream.println(msg);
        writeStream.flush();
    }

    public void close () throws IOException {
        Logger.info("Closing client " + clientSocket.getLocalAddress());

        // First cleanup methods that don't throw exceptions
        // TODO notify game, remove client, ...

        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
        clientSocket.close();
    }
}
