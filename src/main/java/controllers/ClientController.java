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
     * Disconnect player.
     */
    public void disconnect () {
        try {
            clientHandler.close();
        } catch (IOException e) {
            Logger.err(e, "Error closing connection.");
        }
    }

    private void cleanUpAction () {
        // Reset the used effects of weapons
        if (linkedPlayer.getActiveActionItems().contains(ActionController.ActionItem.SHOOT)) {
            linkedPlayer.getWeapons().forEach(w -> {
                if (!w.getUsedEffects().isEmpty()) {
                    w.getUsedEffects().clear();
                    w.forceReload();
                }
            });
        }

        linkedPlayer.setActiveAction(null);
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

    /**
     * Gets the nickname.
     * If there's no player with nickname, cannot disconnect it.
     *
     *
     * @param chooseNicknameMessage for choosing nickname.
     */
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
    /**
     * Setup the starting game state messages.
     *
     * @param  gameSettingsMessage the message for initial game settings
     */
    public void visit(GameSettingsMessage gameSettingsMessage) {
        gameController.setup(gameSettingsMessage);
    }

    /**
     * Controls the action starting.
     * Use power up and reload are not counted as possible actions.
     * Every player has 2 possible actions.
     * Discard and spawn when the first turn of every player starts and when the player dies.
     *
     *
     * @param actionStartMessage for starting the action
     */
    public void visit(ActionStartMessage actionStartMessage) {
        ActionController.Action action = actionStartMessage.getAction();

        // Allow out of turn actions only for some powerups and discard and spawn
        if (!linkedPlayer.isActive() &&
            action != ActionController.Action.USE_POWER_UP &&
            action != ActionController.Action.DISCARD_AND_SPAWN
        ) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
            return;
        }

        if (
                linkedPlayer.getActionCounter() < 1 &&
                action != ActionController.Action.RELOAD &&
                action != ActionController.Action.USE_POWER_UP &&
                action != ActionController.Action.DISCARD &&
                action != ActionController.Action.DISCARD_AND_SPAWN
        ) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not available actions remained!");
            sendMsg(errorMessage);
            return;
        }

        if (linkedPlayer.getActiveAction() != null) {
            cleanUpAction();
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

        if (!linkedPlayer.isDead() && action == ActionController.Action.DISCARD_AND_SPAWN) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("You can only respawn when dead!");
            sendMsg(errorMessage);
            return;
        }

        gameController.rescheduleTurnTimer(getLinkedPlayer());

        // These actions are not counted towards the actions limit
        if (
                action != ActionController.Action.USE_POWER_UP
                && action != ActionController.Action.DISCARD_AND_SPAWN
                && action != ActionController.Action.DISCARD
                && action != ActionController.Action.RELOAD
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

    /**
     * Properly for the action that the player want to choose.
     * Every player has two possible actions.
     * Action items must be executed in order in every action, unless there will be an error message.
     *
     * @param actionMessage for making the action
     */
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

    /**
     * Controls the end of an action, if a player, whose turn finishes, try to do another move,
     * error message.
     *
     * @param actionEndMessage to end the action
     */
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


        cleanUpAction();

        GameStateMessage.updateClients(gameController);
    }

    /**
     * Control end turn for all players.
     *
     * @param endTurnMessage to end the turn.
     */
    public void visit(EndTurnMessage endTurnMessage) {
        if (!linkedPlayer.isActive()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMsg("Not your turn!");
            sendMsg(errorMessage);
            return;
        }
        gameController.endTurn(false);
    }

    /**
     * Errors management.
     *
     * @param errorMessage the error message
     */
    public void visit(ErrorMessage errorMessage) {
        Logger.info("Received error message from client: " + errorMessage.getErrorMsg());
    }
    /**
     * Implemented only in client side, manages all game state messages .
     *
     * @param gameStateMessage the state of the game process.
     */
    public void visit(GameStateMessage gameStateMessage) {

    }
    /**
     *  Implemented only in client side, manages the end of the game.
     *
     * @param endGameMessage the end game message.
     */
    public void visit(EndGameMessage endGameMessage) {

    }
}
