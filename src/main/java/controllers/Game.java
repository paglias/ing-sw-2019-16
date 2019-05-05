package controllers;

import models.GameBoard;
import models.Player;
import models.Square;

public class Game {
    private GameBoard gameBoard;

    public void setup (Integer map) {
        if (gameBoard != null) {
            throw new IllegalArgumentException("Game already exists, cannot create a new one.");
        }

        gameBoard = new GameBoard();
        gameBoard.setMap(map != null ? map : 1);

        // TODO timer for starting and joining
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

    // TODO ...
}
