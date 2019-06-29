package controllers;

import messages.*;
import messages.client_data.ClientInput;
import models.Player;
import models.cards.PowerUp;
import server.ClientHandler;
import utils.Constants;
import utils.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController implements MessageVisitor {
    private static ArrayList<GameController> gameControllers = new ArrayList<>();

    private ClientHandler clientHandler;
    private GameController gameController;
    private Player linkedPlayer; // The player associated to this client

    public ClientController (ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.gameController = null;
        this.linkedPlayer = null;
    }

    /**
     * On ended game, unregister the game controller
     *
     * @param endedGame the ended game
     */
    public static void onEndedGame (GameController endedGame) {
        ClientController.gameControllers.remove(endedGame);
    }

    /**
     * Gets linked player.
     *
     * @return the linked player
     */
    public Player getLinkedPlayer () {
        return linkedPlayer;
    }

    /**
     * Gets game controller.
     *
     * @return the game controller for this client
     */
    public GameController getGameController () { return gameController; }

    /**
     * Sets linked player.
     *
     * @param player the player
     */
    public void setLinkedPlayer (Player player) {
        linkedPlayer = player;
    }

    /**
     * Send msg.
     *
     * @param msg the msg
     */
    public void sendMsg (AbstractMessage msg) {
        if (Constants.DEBUG) Logger.info("Sending message >>> " + msg.getClass().getName() + " to " + getLinkedPlayer().getNickname());
        clientHandler.sendMessage(msg.serialize());
    }

    /**
     * Send msg.
     *
     * @param msg the msg
     */
    public void sendMsg (String msg) {
        clientHandler.sendMessage(msg);
    }

    public void visit(ChooseNicknameMessage chooseNicknameMessage) {
        String nickname = chooseNicknameMessage.getNickname();
        if (nickname == null) {
            try {
                clientHandler.close();
            } catch (IOException e) {
                Logger.err(e, "Error disconnecting client with no username");
            }

            return;
        }

        GameController gameControllerWithUser = null;

        for (GameController gC : ClientController.gameControllers) {
            // See if the user disconnected from a previous game
            Player existingPlayer = gC.getGameBoard().getPlayers().stream()
                    .filter(p -> p.getNickname().equals(nickname))
                    .findFirst()
                    .orElse(null);

            if (existingPlayer != null && !existingPlayer.isConnected()) {
                gameControllerWithUser = gC;
                break;
            }
        }

        if (gameControllerWithUser != null) {
            // Can reconnect
            gameController = gameControllerWithUser;
            gameControllerWithUser.addPlayer(nickname, this);
        } else {
            GameController lastGameController = null;

            if (!ClientController.gameControllers.isEmpty()) {
                lastGameController = ClientController.gameControllers.get(ClientController.gameControllers.size() - 1);
            }

            // No active game or last game started, create a new one
            if (lastGameController == null || lastGameController.getGameBoard().hasStarted()) {
                GameController newGameController = new GameController();
                ClientController.gameControllers.add(newGameController);
                gameController = newGameController;
                newGameController.addPlayer(chooseNicknameMessage.getNickname(), this);
            } else { // join the existing game
                gameController = lastGameController;
                lastGameController.addPlayer(nickname, this);
            }


        }
    }
    public void visit(GameSettingsMessage gameSettingsMessage) {
        gameController.setup(gameSettingsMessage);
    }

    public void visit(ActionStartMessage actionStartMessage) {
        ActionController.Action action = actionStartMessage.getAction();

        // Allow out of turn actions only for some powerups
        if (!linkedPlayer.isActive() && action != ActionController.Action.USE_POWER_UP) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
            return;
        }

        if (linkedPlayer.getActionCounter() < 1) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not available actions remained!");
            sendMsg(errorMessage);
            return;
        }

        if (linkedPlayer.getActiveAction() != null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("An action has already started, finish it first!");
            sendMsg(errorMessage);
            return;
        }


        if (!linkedPlayer.getPossibleActions().contains(action)) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Invalid action!");
            sendMsg(errorMessage);
            return;
        }

        if (linkedPlayer.isDead() && action != ActionController.Action.DISCARD_AND_SPAWN) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("You must respawn before doing anything else!");
            sendMsg(errorMessage);
            return;
        }

        gameController.rescheduleTurnTimer(getLinkedPlayer());

        // These actions are not counted towards the actions limit
        if (
                action != ActionController.Action.USE_POWER_UP
                && action != ActionController.Action.DISCARD_AND_SPAWN
                && action != ActionController.Action.DISCARD
        ) {
            linkedPlayer.decreaseActionCounter();
        }

        // If you use the Reload action, it must be at the end of the turn
        // So we set the action count to 0
        if (action == ActionController.Action.RELOAD) {
            linkedPlayer.setActionCounter(0);
        }

        // Spawning can be done only once
        if (action == ActionController.Action.DISCARD_AND_SPAWN) {
            linkedPlayer.getPossibleActions().remove(action);
        }

        linkedPlayer.setActiveAction(action);
        GameStateMessage.updateClients(gameController);
    }

    public void visit(ActionMessage actionMessage) {
        if (linkedPlayer.getActiveAction() == null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("No active action!");
            sendMsg(errorMessage);
            return;
        }

        ActionController.ActionItem actionItem = actionMessage.getActionItem();
        ActionController.Action activeAction = linkedPlayer.getActiveAction();
        List<ActionController.ActionItem> activeActionItems = linkedPlayer.getActiveActionItems();

        if (!activeActionItems.contains(actionItem)) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Invalid action item for the active action!");
            sendMsg(errorMessage);
            return;
        }

        if (activeActionItems.get(0) != actionItem) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Action items must be done in order!");
            sendMsg(errorMessage);
            return;
        }

        ClientInput clientInput = actionMessage.getClientInput();
        ActionController actionController = new ActionController(gameController);

        System.out.println("handling action for " + linkedPlayer.getNickname());

        // Tagback grenade can be used outside of the user's turn
        if (!linkedPlayer.isActive()) {
            PowerUp powerUp = null;

            if (activeAction == ActionController.Action.USE_POWER_UP) {
                powerUp = linkedPlayer.getPowerUps().get(clientInput.powerUpIndex);
            }

            if (powerUp == null || !powerUp.getName().equals("TagbackGrenade")) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setErrorMsg("Not your turn!");
                sendMsg(errorMessage);
                return;
            }
        }

        switch (actionItem) {
            case GRAB:
                actionController.grab(clientInput);
                break;
            case MOVE:
                actionController.move(clientInput);
                break;
            case SHOOT:
                actionController.shoot(clientInput);
                break;
            case RELOAD:
                actionController.reload(clientInput);
                break;
            case USE_POWER_UP:
                actionController.usePowerUp(clientInput);
                break;
            case DISCARD_AND_SPAWN:
                System.out.println("handling discardANDSspawn with n powerups:" + linkedPlayer.getPowerUps().size());
                actionController.discardPowerUpAndSpawn(clientInput);
                break;
            case DISCARD:
                actionController.discard(clientInput);
                break;
        }

        // Do not remove shoot because it can be used once per effect
        if (actionItem != ActionController.ActionItem.SHOOT) {
            activeActionItems.remove(actionItem);
        }
        GameStateMessage.updateClients(gameController);
    }

    public void visit (ActionEndMessage actionEndMessage) {
        if (!linkedPlayer.isActive()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
            return;
        }

        if (linkedPlayer.getActiveAction() == null) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("No active action!");
            sendMsg(errorMessage);
            return;
        }


        // Reset the used effects of weapons
        if (linkedPlayer.getActiveActionItems().contains(ActionController.ActionItem.SHOOT)) {
            linkedPlayer.getWeapons().forEach(w -> {
                ArrayList<Integer> usedEffects = w.getUsedEffects();
                if (usedEffects.size() > 0) {
                    w.getUsedEffects().clear();
                    w.forceReload();
                }
            });

        }

        linkedPlayer.setActiveAction(null);

        GameStateMessage.updateClients(gameController);
    }

    public void visit(EndTurnMessage endTurnMessage) {
        if (!linkedPlayer.isActive()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
            return;
        }
        gameController.endTurn(false);
    }

    public void visit(ErrorMessage errorMessage) {
        Logger.info("Received error message from client: " + errorMessage.getErrorMsg());
    }

    public void visit(GameStateMessage gameStateMessage) {
        // Not implemented, client side only
    }
    public void visit(EndGameMessage endGameMessage) {
        // Not implemented, client side only
    }
}
