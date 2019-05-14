package server;

import controllers.ClientController;
import controllers.GameController;
import messages.AbstractMessage;

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

            System.out.println("Connected client " + clientSocket.getRemoteSocketAddress());

            clientController = new ClientController(gameController, this);
            clientController.init();

            // Handle incoming data from client
            String msg;

            do {
                msg = readStream.readLine();
                if (msg != null) handleMessage(msg);
            } while (msg != null);
        } catch (IOException e) {
            System.err.println("Problem with client " + clientSocket.getLocalAddress() + ": " + e.getMessage());
        } finally {
            close();
        }
    }

    void handleMessage (String msg) {
        System.out.println("From client >>> " + msg);
        AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
        parsedMsg.accept(clientController);
    }

    public void sendMessage (String msg) {
        writeStream.println(msg);
        writeStream.flush();
    }

    public void close () throws IOException {
        System.out.println("Closing client " + clientSocket.getLocalAddress());

        // First cleanup methods that don't throw exceptions
        // TODO notify game, remove client, ...

        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
        clientSocket.close();
    }
}
