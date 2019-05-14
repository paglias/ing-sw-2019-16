package client;

import messages.*;

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

    public void visit(ConnectMessage connectMessage) {
        System.out.println("handling connection msg" + connectMessage.serialize());
    }
    public void visit(DisconnectMessage disconnectMessage) {
        System.out.println("handling disconnection msg" + disconnectMessage.serialize());
    }

    public void visit(ChooseUsernameMessage chooseUsernameMessage) {
        System.out.println("handling choose username msg" + chooseUsernameMessage.serialize());
    }
    public void visit(ChooseMapMessage chooseMapMessage) {
        System.out.println("handling choose map msg" + chooseMapMessage.serialize());
    }
    public void visit(ActionMessage actionMessage) {
        System.out.println("handling choose action msg" + actionMessage.serialize());
    }
    public void visit(EndTurnMessage endTurnMessage) {
        System.out.println("handling choose endturn msg" + endTurnMessage.serialize());
    }

    public void visit(GameStateMessage gameStateMessage) {
        // Not implemented, client side only
    }
    public void visit(EndGameMessage endGameMessage) {
        // Not implemented, client side only
    }

    public void visit(ErrorMessage errorMessage) {
        System.out.println("handling error msg" + errorMessage.serialize());
    }
}
