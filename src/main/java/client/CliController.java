package client;

import messages.AbstractMessage;
import messages.ConnectionMessage;
import messages.GameStartedMessage;
import messages.MessageVisitor;

import java.util.Scanner;

class CliController implements MessageVisitor  {
    private Connection connection;
    private Scanner keyboard;

    CliController(Connection connection, Scanner keyboard) {
        this.connection = connection;
        this.keyboard = keyboard;
    }


    void init () {
        System.out.println("Welcome to the CLI version of Adrenaline!");

        boolean condition = true;
        while (condition) {
            System.out.println("Send some command:");
            String command = waitForUserInput();
            System.out.println("You asked for >>> " + command);
            connection.send(command);
        }
    }

    void onServerMessage (String msg) {
        System.out.println("From server >>> " + msg);
        AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
        parsedMsg.accept(this);
    }

    private String waitForUserInput () {
        return keyboard.nextLine();
    }

    public void visit(ConnectionMessage gameStartedMessage) {
        System.out.println("The server has sent a game started message!" + gameStartedMessage.serialize());
    }
    public void visit(GameStartedMessage gameStartedMessage) {
        System.out.println("The server has sent a game started message!" + gameStartedMessage.serialize());
    }
}
