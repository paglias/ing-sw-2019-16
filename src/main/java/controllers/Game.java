package controllers;

import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private GameBoard gameBoard;
    private static int gameStartTimeout = 5;
    private static int turnTimeout = 5;

    public void setup(Integer map) {
        if (gameBoard != null) {
            throw new IllegalArgumentException("Game already exists, cannot create a new one.");
        }

        gameBoard = new GameBoard();
        gameBoard.setMap(map != null ? map : 1);
    }

    public void addPlayer(String nickname, String colorString) {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("Game already started, cannot join.");
        }

        if (nickname == null) throw new IllegalArgumentException("Nickname must exist");

        Square.Color color = Square.Color.valueOf(colorString);

        Player player = new Player();
        player.setNickname(nickname);
        player.setColor(color);

        // Give the each player 2 powerups (one will be discarded)
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());

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
            }, gameStartTimeout * 1000);
        }
    }

    public void start() {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("Game already started, cannot create a new one.");
        }

        gameBoard.startGame();
        gameBoard.getPlayers().get(0).setActive(true);
    }

    public void discardPowerUpAndSpawn(int powerUpToDiscardPosition) {
        Player player = gameBoard.getActivePlayer();
        PowerUp powerUp = player.getPowerUps().remove(powerUpToDiscardPosition);
        gameBoard.getPowerUpsDeck().discard(powerUp);

        Square spawnPosition = gameBoard.getSquares().stream()
                .filter(s -> s.getColor().toString() == powerUp.getColor().toString() && s.isSpawnPoint())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        player.setPosition(spawnPosition);
    }

    public void startTurn() {
        Timer turnTimer = new Timer();
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                endTurn();
            }
        }, turnTimeout * 1000);
        Player player = gameBoard.getActivePlayer();
        player.setActionCounter(2);
    }

    public void endTurn() {
        Player currentPlayer=gameBoard.getActivePlayer();
        for(Player player: gameBoard.getPlayers()){
            player.playerIsDead(currentPlayer, gameBoard);
       }// controlla quali tra i giocatori sono morti

        gameBoard.nextPlayer(gameBoard.getActivePlayer());
        startTurn();


    }

    public void action(Square position, Weapon weapon) {
        Player player = gameBoard.getActivePlayer();
        if(!gameBoard.isFinalFrenzy()){
            if (player.getActionCounter()==2) {
            move(position);
            moveGrab(position, weapon);
            // shoot();
            }
        }
    }

    public void move (Square position){
        Player player = gameBoard.getActivePlayer();
        player.move(position);
    }

    public void moveGrab (Square position, Weapon weapon){
        Player player = gameBoard.getActivePlayer();
        player.move(position);
        player.grabItem(gameBoard, weapon);

    }
    public void shoot(String weaponName, List<Weapon.Effect>PrimaryEffect, List<Weapon.Effect>SecondaryEffect, List<Weapon.Effect>TertiaryEffect) {
        Player player = gameBoard.getActivePlayer();
        Weapon weapon = player.getWeaponByName(weaponName);
        List<Weapon.Effect> effects = weapon.getPrimaryEffect().get(0);
        for (Weapon.Effect effect : effects) {
            switch (effect) {
                case MOVE:
                    // weapon.move();
            }

        }
    }

    public void usePowerup(PowerUp powerUp, Player playerTarget, Card.Color cubeColor, Square newSquare, Square position, Square.Direction direction
    , List<Square>squares){
        Player player=gameBoard.getActivePlayer();
        switch(powerUp.getName()){
            case NEWTON:
                powerUp.effect(player,playerTarget,position, newSquare,direction,squares);
            case TELEPORTER:
                powerUp.effect(player, position);
            case TAGBACK_GRENADE:
                powerUp.effect(player,playerTarget);
            case TARGETING_SCOPE:
                powerUp.effect(player,cubeColor,playerTarget);
        }

    }

    public void finalFrenzyMode(){
        Player player=gameBoard.getActivePlayer();
        if(player.getActionCounter()==2){
            // player.
        }

    }
}