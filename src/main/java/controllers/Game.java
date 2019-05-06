package controllers;

import models.GameBoard;
import models.Player;
import models.Square;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private GameBoard gameBoard;

    public void setup (Integer map) {
        if (gameBoard != null) {
            throw new IllegalArgumentException("Game already exists, cannot create a new one.");
        }

        gameBoard = new GameBoard();
        gameBoard.setMap(map != null ? map : 1);
    }

    public void addPlayer (String nickname, String colorString) {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("Game already started, cannot join.");
        }

        if (nickname == null) throw new IllegalArgumentException("Nickname must exist");

        Square.Color color = Square.Color.valueOf(colorString); // TODO catch IllegalArgumentException?

        Player player = new Player();
        player.setNickname(nickname);
        player.setColor(color);
        gameBoard.addPlayer(player);

        // Start the game when 5 players have joined
        if (gameBoard.getPlayers().size() == 5) start();

        // Start a timer when 3 players have started
        if (gameBoard.getPlayers().size() == 3) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!gameBoard.hasStarted()) start();
                }
            }, 3 * 1000); // TODO make timeout duration configurable
        }
    }

    public void start () {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("Game already started, cannot create a new one.");
        }

        if (gameBoard.getPlayers().size() < 2) {
            throw new IllegalArgumentException("Need at least two players");
        }

        gameBoard.startGame();
        gameBoard.getPlayers().get(0).setActive(true);
        gameBoard.nextTurn();
    }

    // TODO choose powerup at beginning of first turn
    // TODO Execute actions

    public void nextTurn () {
        if (!gameBoard.hasStarted()) {
            throw new IllegalArgumentException("Game not started...");
        }

        gameBoard.nextTurn();
    }

    public void turn () {
        if (gameBoard.getTurn() == 1) {
            // TODO first turn, pick two powerups, keep 1
        }
    }

    // TODO ...
}
