package client;

import client.views.Game;
import messages.*;

import java.util.Scanner;

public class Controller implements MessageVisitor  {
    private Connection connection;
    private Scanner keyboard;

    Controller(Connection connection, Scanner keyboard) {
        this.connection = connection;
        this.keyboard = keyboard;
    }

    void init () {
        System.out.println("Starting the GUI...");

        // TODO ConnectMessage msg = new ConnectMessage();
        // connection.send(msg.serialize());

        Game.startGame(this);
    }

    void onServerMessage (String msg) {
        System.out.println("From server >>> " + msg);
        // TODO handle errors from deserialization/handling
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

    public void visit(ChooseNicknameMessage chooseNicknameMessage) {
        // Not implemented, server side only
    }
    public void visit(GameSettingsMessage gameSettingsMessage) {
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
        System.out.println("Received error message from server" + errorMessage.getErrorMsg());
    }
}
