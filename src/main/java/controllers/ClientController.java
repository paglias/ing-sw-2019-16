package controllers;

import messages.*;
import models.Player;
import models.Square;
import server.ClientHandler;

public class ClientController implements MessageVisitor {
    private ClientHandler clientHandler;
    private GameController gameController;
    private Player linkedPlayer; // The player associated to this client

    public ClientController (GameController gameController, ClientHandler clientHandler) {
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }


    public Player getLinkedPlayer () {
        return linkedPlayer;
    }

    public void setLinkedPlayer (Player player) {
        linkedPlayer = player;
    }

    public void init () {
        // TODO
    }

    public void sendMsg (AbstractMessage msg) {
        clientHandler.sendMessage(msg.serialize());
    }

    // Message already serialized
    public void sendMsg (String msg) {
        clientHandler.sendMessage(msg);
    }

    public void visit(ConnectMessage connectMessage) {
        GameStateMessage gameState = new GameStateMessage(gameController);
        clientHandler.sendMessage(gameState.serialize());
    }
    public void visit(DisconnectMessage disconnectMessage) {
        System.out.println("handling disconnection msg" + disconnectMessage.serialize());
    }

    public void visit(ChooseNicknameMessage chooseNicknameMessage) {
        System.out.println("handling choose username msg" + chooseNicknameMessage.serialize());
        gameController.addPlayer(chooseNicknameMessage.getNickname(), this);

        GameStateMessage gameState = new GameStateMessage(gameController);
        clientHandler.sendMessage(gameState.serialize());
    }
    public void visit(ChooseMapMessage chooseMapMessage) {
        System.out.println("handling choose map msg" + chooseMapMessage.serialize());
        gameController.setMap(chooseMapMessage.getMapNumber());

        GameStateMessage gameState = new GameStateMessage(gameController);
        clientHandler.sendMessage(gameState.serialize());
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
        // TODO which client?
        System.out.println("Received error message from client" + errorMessage.getErrorMsg());
    }
}
