package client;

import utils.Logger;

import java.io.*;
import java.net.Socket;


public class Connection implements Closeable {
    private final String host;
    private final int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Instances game connection.
     *
     * @throws IOException the io exception
     */
    public void init () throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // auto-flushing, yeah B)
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Receive string messages.
     *
     * @return the string
     * @throws IOException the io exception
     */
    public String receive () throws IOException {
        return in.readLine();
    }

    /**
     * Send messages.
     *
     * @param message the message
     */
    public void send (String message) {
        out.println(message);
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     */
    public boolean isClosed () { return socket.isClosed(); }

    /**
     * Close connection to the server.
     *
     * @throws IOException in closing the connection.
     */
    public void close() throws IOException {
        Logger.info("Closing connection to server at " + host + ":" + port);
        in.close();
        out.close();
        socket.close();
    }
}