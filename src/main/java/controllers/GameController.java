package controllers;

import messages.AbstractMessage;
import messages.GameSettingsMessage;
import messages.GameStateMessage;
import models.GameBoard;
import models.Player;
import models.cards.PowerUp;

import java.util.*;

public class GameController {
    private static int gameStartTimeout = 30;
    private static int turnTimeout = 30;

    private GameBoard gameBoard;
    private ArrayList<ClientController> clients = new ArrayList<>();

    public GameBoard getGameBoard () { return gameBoard; }

    public GameController () {
        gameBoard = new GameBoard();
    }

    public void addClient (ClientController clientController) {
        clients.add(clientController);
    }

    public ClientController getClientForPlayer (Player player) {
        return clients.stream()
                .filter(c -> c.getLinkedPlayer() == player)
                .findFirst()
                .orElse(null);
    }

    public ClientController getClientForPlayer (String nickname) {
        Player player = gameBoard.getPlayerByNickname(nickname);
        return getClientForPlayer(player);
    }

    public void dispatchToClients (AbstractMessage msg) {
        String serialized = msg.serialize();
        clients.stream().forEach(c -> c.sendMsg(serialized));
    }

    public synchronized void addPlayer(String nickname, ClientController clientController) {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("GameController already started, cannot join.");
        }

        if (nickname == null) throw new IllegalArgumentException("Nickname must exist");

        Player player = new Player();
        player.setNickname(nickname);

        // Give the each player 2 powerups (one will be discarded)
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());

        player.setDead(true); // Useful to make sure they spawn as first thing

        gameBoard.addPlayer(player);
        clientController.setLinkedPlayer(player);
        addClient(clientController);

        // Start the game when 5 players have joined
        if (gameBoard.getPlayers().size() == 5) {
            start();
        } else {
            // Start a timer when 3 players have started
            if (gameBoard.getPlayers().size() == 3) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!gameBoard.hasStarted()) start();
                    }
                }, gameStartTimeout * (long)1000);
            }

            GameStateMessage.updateClients(this);
        }

    }

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

    public synchronized void start() {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("GameController already started, cannot create a new one.");
        }

        gameBoard.startGame();
        gameBoard.getPlayers().get(0).setActive(true);

        GameStateMessage.updateClients(this);
    }

    public synchronized void startTurn() {
        Timer turnTimer = new Timer();
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                endTurn();
            }
        }, turnTimeout * (long)1000);
        Player player = gameBoard.getActivePlayer();

        ActionController actionController = new ActionController(this);
        player.setPossibleActions(actionController.getPossibleActions());

        if(!gameBoard.isFinalFrenzy()) {
            player.setActionCounter(2);
        }
        if(gameBoard.getSkulls().getNRemaining()==0){
            gameBoard.finalFrenzy();
        }
        player.setStartTurnDate(new Date());
        GameStateMessage.updateClients(this);
    }

    public synchronized void endTurn() {
        gameBoard.nextPlayer(gameBoard.getActivePlayer());
        startTurn();
    }
}


