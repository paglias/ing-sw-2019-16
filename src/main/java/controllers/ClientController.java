package controllers;

import messages.*;
import server.ClientHandler;

public class ClientController implements MessageVisitor {
    private ClientHandler clientHandler;
    private GameController gameController;

    public ClientController (GameController gameController, ClientHandler clientHandler) {
        this.gameController = gameController;
        this.clientHandler = clientHandler;
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

    public void init () {
        if (gameController.getGameBoard().hasStarted()) {
            clientHandler.sendMessage("Game already started!");
        }
    }
}
