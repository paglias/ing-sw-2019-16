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

    public void init () {
        if (gameController.getGameBoard().hasStarted()) {
            clientHandler.sendMessage("Game already started!");
        }
    }
}
