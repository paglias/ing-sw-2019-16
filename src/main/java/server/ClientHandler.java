package server;

import controllers.ClientController;
import controllers.GameController;
import javafx.application.Platform;
import messages.AbstractMessage;
import models.Player;
import utils.Constants;
import utils.Logger;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private Socket clientSocket;
    private ClientController clientController;

    // Not used to write or read
    private InputStream inputStream;
    private OutputStream outputStream;

    // To write and read
    private BufferedReader readStream;
    private PrintWriter writeStream;

    ClientHandler (Socket socket) {
        this.clientSocket = socket;
    }

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
            clientController.getGameController().disconnectPlayer(clientController);
        }
    }

    void handleMessage (String msg) {
        if (Constants.DEBUG) Logger.info("From client >>> " + msg);

        try {
            AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
            parsedMsg.accept(clientController);
        } catch (Throwable e) {
            Logger.err(e, "Error handling client message " + clientSocket.getLocalAddress());
        }
    }

    public void sendMessage (String msg) {
        writeStream.println(msg);
        writeStream.flush();
    }

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
