package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private Socket clientConnection;

    public ClientHandler (Socket socket) {
        this.clientConnection = socket;
    }

    public void handleConnection() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // Communication streams
            inputStream = clientConnection.getInputStream();
            outputStream = clientConnection.getOutputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter out = new PrintWriter(outputStream);

            String msg;

            do {
                msg = in.readLine();

                if (msg != null && !msg.startsWith("quit")) {
                    System.out.println("<<< " + clientConnection.getRemoteSocketAddress() + ": " + msg);
                    out.println(">>> " + msg);
                    // when you call flush you really send what
                    // you added to the buffer with println.
                    out.flush();
                }
            } while (msg != null && !msg.startsWith("quit"));

        } finally {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            clientConnection.close();
        }
    }
}
