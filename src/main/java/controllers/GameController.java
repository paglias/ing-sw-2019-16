package controllers;

import messages.AbstractMessage;
import messages.EndGameMessage;
import messages.GameSettingsMessage;
import messages.GameStateMessage;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.PowerUp;
import utils.Constants;
import utils.Logger;

import java.util.*;

public class GameController {
    private GameBoard gameBoard;
    private ArrayList<ClientController> clients = new ArrayList<>();
    private Timer gameStartTimer;
    private Timer turnTimer;

    /**
     * Gets game board.
     *
     * @return the game board
     */
    public GameBoard getGameBoard () { return gameBoard; }

    /**
     * Instantiates a new Game controller.
     */
    public GameController () {
        gameBoard = new GameBoard();
    }

    /**
     * Add client.
     *
     * @param clientController the client controller
     */
    public void addClient (ClientController clientController) {
        clients.add(clientController);
    }

    public void rescheduleTurnTimer (Player player) {
        if (turnTimer != null) turnTimer.cancel();
        turnTimer = new Timer();
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (gameBoard.hasStarted() && !gameBoard.hasEnded() && player.isActive()) {
                    endTurn(true);
                }
            }
        }, Constants.TURN_TIMEOUT * 1000);

    }

    /**
     * Gets client for player.
     *
     * @param player the player
     * @return the client for player
     */
    public ClientController getClientForPlayer (Player player) {
        return clients.stream()
                .filter(c -> c.getLinkedPlayer() == player)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get player by nickname.
     *
     * @param nickname the nickname
     * @return the client for player
     */
    public ClientController getClientForPlayer (String nickname) {
        Player player = gameBoard.getPlayerByNickname(nickname);
        return getClientForPlayer(player);
    }

    /**
     * Dispatch to clients.
     *
     * @param msg the msg
     */
    public void dispatchToClients (AbstractMessage msg) {
        String serialized = msg.serialize();
        clients.stream().forEach(c -> c.sendMsg(serialized));
    }

    /**
     * Add player.
     * If there's already an existing player connect the new player
     * If there's not an existing player this add the first player of the game
     * If there are 5 players start the game
     * If there are 3 players start the timer
     *
     *
     * @param nickname         the nickname
     * @param clientController the client controller
     */
    public synchronized void addPlayer(String nickname, ClientController clientController) {
        Player existingPlayer = gameBoard.getPlayers().stream()
                .filter(p -> p.getNickname().equals(nickname))
                .findFirst()
                .orElse(null);

        // Player reconnected
        if (existingPlayer != null) {
            ClientController oldClient = getClientForPlayer(existingPlayer);
            clients.remove(oldClient);
            clientController.setLinkedPlayer(existingPlayer);
            addClient(clientController);
            existingPlayer.setConnected(true);
        } else { // new player
            if (gameBoard.hasStarted()) {
                throw new IllegalArgumentException("GameController already started, cannot join.");
            }

            Logger.info("Player " + nickname + " joined!");

            Player player = new Player();
            player.setNickname(nickname);

            // Give the each player 2 powerups (one will be discarded)
            player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
            player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());

            player.setDead(true); // Useful to make sure they spawn as first thing

            gameBoard.addPlayer(player);
            clientController.setLinkedPlayer(player);
            addClient(clientController);
        }

        // Start the game when 5 players have joined
        if (!gameBoard.hasStarted() && gameBoard.getPlayers().size() == 5) {
            start();
        } else {
            // Start a timer when 3 players are connected
            if (gameBoard.getNConnectedPlayers() == 3) {
                if (gameStartTimer != null) gameStartTimer.cancel();
                gameStartTimer = new Timer();
                gameStartTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!gameBoard.hasStarted() && gameBoard.getNConnectedPlayers() >= 3) {
                            start();
                        }
                    }
                }, Constants.TIMEOUT * 1000);
            }

            GameStateMessage.updateClients(this);
        }
    }

    /**
     * Disconnect a player.
     * If there are less than 3 players end the game
     *
     * @param clientController the client controller
     */
    public synchronized void disconnectPlayer (ClientController clientController) {
        Player player = clientController.getLinkedPlayer();
        player.setConnected(false);
        Logger.info("Player " + player.getNickname() + " disconnected.");
        GameStateMessage.actionsHistoryTemp.add(player.getNickname() + " disconnected!");

        long remainingPlayers = gameBoard.getNConnectedPlayers();

        if (remainingPlayers < 3) {
            if (gameBoard.hasStarted()) {
                if (!gameBoard.hasEnded()) endGame();
            } else if (gameStartTimer != null){
                gameStartTimer.cancel();
            }
        } else {
            GameStateMessage.updateClients(this);
        }
    }

    /**
     * Setup the game.
     * Set the skulls.
     * Choose the map.
     *
     * @param gameSettingsMessage the game settings message
     */
    public synchronized void setup (GameSettingsMessage gameSettingsMessage) {
        if (gameBoard.isGameSetup()) {
            throw new IllegalArgumentException("Game already setup.");
        }

        int nSkulls = gameSettingsMessage.getSkullsNumber();
        int mapN = gameSettingsMessage.getMapNumber();

        if (mapN < 1 || mapN > 4) {
            throw new IllegalArgumentException("Invalid map number.");
        }
        if (nSkulls < 5 || nSkulls > 8) {
            throw new IllegalArgumentException("Invalid skulls number.");
        }

        gameBoard.getSkulls().setNRemaining(nSkulls);
        gameBoard.setMap(mapN);
        gameBoard.setGameSetup(true);

        GameStateMessage.updateClients(this);
    }

    /**
     * Start.
     */
    public synchronized void start() {
        if (gameBoard.hasStarted()) {
            // Not throwing an error because multiple timers could have started at once
            Logger.info("Game already started!");
            return;
        }

        // Automatically setup the game if not done manually
        if (!gameBoard.isGameSetup()) {
            gameBoard.setMap(1);
            gameBoard.getSkulls().setNRemaining(5);
            gameBoard.setGameSetup(true);
        }

        gameBoard.startGame();
        gameBoard.getPlayers().get(0).setActive(true);

        Logger.info("Staring game!");
        GameStateMessage.actionsHistoryTemp.add("Game started!");

        startTurn();
    }

    /**
     * Start new turn.
     */
    public synchronized void startTurn() {
        Player player = gameBoard.getActivePlayer();

        rescheduleTurnTimer(player);

        ActionController actionController = new ActionController(this);
        player.setPossibleActions(actionController.getPossibleActions());

        if(!gameBoard.isFinalFrenzy()) {
            player.setActionCounter(2);
        }
        if(gameBoard.getSkulls().getNRemaining()==0){
            gameBoard.finalFrenzy();
        }
        player.setStartTurnDate(new Date());

        Logger.info("Starting turn, active player " + player.getNickname());
        GameStateMessage.actionsHistoryTemp.add("It's " + player.getNickname() + "'s turn!");
        GameStateMessage.updateClients(this);
    }

    /**
     * End turn.
     */
    public synchronized void endTurn(boolean fromTimeout) {
        Player player = gameBoard.getActivePlayer();

        // Disconnect players if the timeout activated
        if (fromTimeout) {
            player.setConnected(false);
            Logger.info("Turn timeout ended for " + player.getNickname());
        }

        Logger.info("Ending turn for " + player.getNickname());
        GameStateMessage.actionsHistoryTemp.add(player.getNickname() + "'s turn ended!");

        player.setActiveAction(null);

        Player newActivePlayer = gameBoard.nextPlayer(player);

        // Everyone has done their final frenzy turn, the game ends here
        if (gameBoard.isFinalFrenzy() && newActivePlayer.isFirstPlayer() && newActivePlayer.finalFrenzyDone()) {
            endGame();
            return;
        }

        // Make sure the next player is connected
        while (!newActivePlayer.isConnected()) {
            newActivePlayer = gameBoard.nextPlayer(newActivePlayer);
        }

        // Refill weapon slots and ammos
        gameBoard.getSquares().stream()
                .forEach(square -> {
                    if (square.isSpawnPoint()) {
                        square.getWeaponsSlot().refill(gameBoard.getWeaponsDeck());
                    } else if (!square.getColor().equals(Square.Color.EMPTY)){
                        square.setAmmo(gameBoard.getAmmoDeck());
                    }
                });

        startTurn();
    }

    /**
     * End game.
     */
    public synchronized void endGame () {
        Logger.info("Ending the game!");

        gameBoard.calculateGamePoints();
        Player winner =  Collections.max(gameBoard.getPlayers(), Comparator.comparing(s -> s.getTotalPoints()));

        EndGameMessage endGameMessage = new EndGameMessage();
        endGameMessage.setWinner(winner.getNickname());

        dispatchToClients(endGameMessage);
        ClientController.onEndedGame(this);

        gameBoard.endGame();
    }
}


