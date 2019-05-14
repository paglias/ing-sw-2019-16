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
        // Not implemented, server side only
    }
    public void visit(DisconnectMessage disconnectMessage) {
        // Not implemented, server side only
    }

    public void visit(ChooseUsernameMessage chooseUsernameMessage) {
        // Not implemented, server side only
    }
    public void visit(ChooseMapMessage chooseMapMessage) {
        // Not implemented, server side only
    }
    public void visit(ActionMessage actionMessage) {
        // Not implemented, server side only
    }
    public void visit(EndTurnMessage endTurnMessage) {
        // Not implemented, server side only
    }

    public void visit(GameStateMessage gameStateMessage) {
        System.out.println("handling game state msg" + gameStateMessage.serialize());
    }
    public void visit(EndGameMessage endGameMessage) {
        System.out.println("handling end game msg" + endGameMessage.serialize());
    }

    public void visit(ErrorMessage errorMessage) {
        System.out.println("handling error msg" + errorMessage.serialize());
    }
}
