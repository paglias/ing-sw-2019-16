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

    public void sendMsg (AbstractMessage msg) {
        clientHandler.sendMessage(msg.serialize());
    }

    // Message already serialized
    public void sendMsg (String msg) {
        clientHandler.sendMessage(msg);
    }

    public void visit(DisconnectMessage disconnectMessage) {
        System.out.println("handling disconnection msg" + disconnectMessage.serialize());
    }

    public void visit(ChooseNicknameMessage chooseNicknameMessage) {
        gameController.addPlayer(chooseNicknameMessage.getNickname(), this);
    }
    public void visit(GameSettingsMessage gameSettingsMessage) {
        gameController.setup(gameSettingsMessage);
    }

    public void visit(ActionStartMessage actionStartMessage) {
        if (!linkedPlayer.isActive()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
        }

        if (linkedPlayer.getActionCounter() < 1) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not available actions remained!");
            sendMsg(errorMessage);
        }

        if (linkedPlayer.getActiveAction() != null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("An action has already started, finish it first!");
            sendMsg(errorMessage);
        }

        ActionController.Action action = actionStartMessage.getAction();

        if (!linkedPlayer.getPossibleActions().contains(action)) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Invalid action!");
            sendMsg(errorMessage);
        }

        if (linkedPlayer.isDead() && action != ActionController.Action.DISCARD_AND_SPAWN) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("You must respawn before doing anything else!");
            sendMsg(errorMessage);
        }

        if (action != ActionController.Action.USE_POWER_UP && action != ActionController.Action.DISCARD_AND_SPAWN) {
            linkedPlayer.decreaseActionCounter();
        }

        if (action != ActionController.Action.USE_POWER_UP) {
            linkedPlayer.getPossibleActions().remove(action);
        }

        linkedPlayer.setActiveAction(action);
        GameStateMessage.updateClients(gameController);
    }

    public void visit(ActionMessage actionMessage) {
        if (!linkedPlayer.isActive()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
        }

        if (linkedPlayer.getActiveAction() == null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("No active action!");
            sendMsg(errorMessage);
        }


        ActionController.ActionItem actionItem = actionMessage.getActionItem();
        ActionController.Action activeAction = linkedPlayer.getActiveAction();

        if (!activeAction.getActionItems().contains(actionItem)) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Invalid action item for the active action!");
            sendMsg(errorMessage);
        }

        ClientInput clientInput = actionMessage.getClientInput();
        ActionController actionController = new ActionController(gameController);

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
            case DISCARD_AND_SPAWN:
                actionController.discardPowerUpAndSpawn(clientInput);
        }

        activeAction.getActionItems().remove(actionItem);

        GameStateMessage.updateClients(gameController);
    }

    public void visit (ActionEndMessage actionEndMessage) {
        if (!linkedPlayer.isActive()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
        }

        if (linkedPlayer.getActiveAction() == null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("No active action!");
            sendMsg(errorMessage);
        }

        linkedPlayer.setActiveAction(null);
        GameStateMessage.updateClients(gameController);
    }

    public void visit(EndTurnMessage endTurnMessage) {
        gameController.endTurn();
    }

    public void visit(ErrorMessage errorMessage) {
        System.out.println("Received error message from client" + errorMessage.getErrorMsg());
    }

    public void visit(GameStateMessage gameStateMessage) {
        // Not implemented, client side only
    }
    public void visit(EndGameMessage endGameMessage) {
        // Not implemented, client side only
    }
}
