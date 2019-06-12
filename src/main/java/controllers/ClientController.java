package controllers;

import messages.*;
import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import server.ClientHandler;

public class ClientController implements MessageVisitor {
    private ClientHandler clientHandler;
    private GameController gameController;
    private GameBoard gameBoard;
    private Player linkedPlayer; // The player associated to this client

    public ClientController (GameController gameController, ClientHandler clientHandler) {
        this.gameController = gameController;
        this.clientHandler = clientHandler;
        this.gameBoard = gameController.getGameBoard();
    }

    // TODO make sure any method from model called here is unsing synchronized
    // Already done for GameController

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
        GameStateMessage.updateClients(gameController);
    }

    public void visit(DisconnectMessage disconnectMessage) {
        System.out.println("handling disconnection msg" + disconnectMessage.serialize());
    }

    public void visit(ChooseNicknameMessage chooseNicknameMessage) {
        System.out.println("handling choose username msg" + chooseNicknameMessage.serialize());
        gameController.addPlayer(chooseNicknameMessage.getNickname(), this);

        GameStateMessage.updateClients(gameController);
    }
    public void visit(GameSettingsMessage gameSettingsMessage) {
        System.out.println("handling game settings msg" + gameSettingsMessage.serialize());
        gameController.setup(gameSettingsMessage);
    }

    public void visit(ActionStartMessage actionStartMessage) {
        // TODO refuse if no active player
        // TODO check action is possible for user
        // set action as active
        // decrease action counter
    }

    public void visit(ActionMessage actionMessage) {
        // TODO check action is active
        ActionController actionController = new ActionController(gameController);

        ActionController.ActionItem actionItem = actionMessage.getAction();
        ClientInput clientInput = actionMessage.getClientInput();

        switch (actionItem) {
            case GRAB:
                actionController.grab(clientInput);
            case MOVE:
                actionController.move(clientInput);
            case SHOOT:
                actionController.shoot(clientInput);
            case RELOAD:
                actionController.reload(clientInput);
            case USE_POWER_UP:
                actionController.usePowerUp(clientInput);
        }
    }

    public void visit (ActionEndMessage actionEndMessage) {
        // TODO
    }

    public void visit(EndTurnMessage endTurnMessage) {
        gameController.endTurn();
    }

    public void visit(ErrorMessage errorMessage) {
        // TODO which client?
        System.out.println("Received error message from client" + errorMessage.getErrorMsg());
    }

    public void visit(GameStateMessage gameStateMessage) {
        // Not implemented, client side only
    }
    public void visit(EndGameMessage endGameMessage) {
        // Not implemented, client side only
    }
}
