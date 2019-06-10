package controllers;

import messages.AbstractMessage;
import messages.GameStateMessage;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.PowerUp;
import models.cards.Weapon;

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

        gameBoard.addPlayer(player);
        clientController.setLinkedPlayer(player);
        addClient(clientController);

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

    public synchronized void discardPowerUpAndSpawn(int powerUpToDiscardPosition) {
        Player player = gameBoard.getActivePlayer();
        PowerUp powerUp = player.getPowerUps().remove(powerUpToDiscardPosition);
        gameBoard.getPowerUpsDeck().discard(powerUp);

        Square spawnPosition = gameBoard.getSquares().stream()
                .filter(s -> s.getColor().toString() == powerUp.getColor().toString() && s.isSpawnPoint())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        player.setPosition(spawnPosition);
    }

    public synchronized void startTurn() {
        Timer turnTimer = new Timer();
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                endTurn();
            }
        }, turnTimeout * 1000);
        Player player = gameBoard.getActivePlayer();
        if(!gameBoard.isFinalFrenzy()) {
            player.setActionCounter(2);
        }
        if(gameBoard.getSkulls().getNRemaining()==0){
            gameBoard.finalFrenzy();
        }
        player.setStartTurnDate(new Date());
    }

    public synchronized void endTurn() {
        Player currentPlayer=gameBoard.getActivePlayer();
        for(Player player: gameBoard.getPlayers()){
            player.playerIsDead(currentPlayer, gameBoard);
       }// controlla quali tra i giocatori sono morti

        gameBoard.nextPlayer(gameBoard.getActivePlayer());
        startTurn();


    }

    /*public synchronized void action(Square position, Weapon weapon, Square secondPosition, Square thirdPosition, String action,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect) {
        Player player = gameBoard.getActivePlayer();
        if(player.getAdrenaline()==0) {
            switch (action) {
                case "move":
                    move(position, secondPosition, thirdPosition);
                case "moveGrab":
                    moveGrab(position, weapon);
                case "shoot":
                    shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
            }
        }
        if(player.getAdrenaline()==1){
            switch (action){
                case"move":
                    move(position,secondPosition,thirdPosition);
                case"moveGrab":
                    moveGrab(position,weapon);
                case"shoot":
                    shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
                case "moveMoveGrab":
                    moveMoveGrab(position,weapon,secondPosition);

            }
            if(player.getAdrenaline()==2){
                switch (action){
                    case"move":
                        move(position,secondPosition,thirdPosition);
                    case"moveGrab":
                        moveGrab(position,weapon);
                    case"shoot":
                        shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
                    case"moveMoveGrab":
                        moveMoveGrab(position,weapon,secondPosition);
                    case"moveShoot":
                        moveShoot(position,weaponName,useSecondaryEffect,useTertiaryEffect);
                }
            }
        }
    }

    public synchronized void actionBeforePlayer(String action,Square position,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect, Square secondPosition, Square thirdPosition, Square fourthPosition){
        Player player= gameBoard.getActivePlayer();
        if(player.isBeforeFirstPlayer()){
            switch(action){
                case "moveReloadShoot":
                    moveReloadShoot(position,weaponName,useSecondaryEffect,useTertiaryEffect);
                case "moveMove":
                    moveMove(position,secondPosition,thirdPosition,fourthPosition);
                case "moveMoveGrab":
                    moveMoveGrab(position,player.getWeaponByName(weaponName),secondPosition);

            }
        }
    }
    public synchronized void actionAfterPlayer(String action, Square position, String weaponName, Square secondPosition,Square thirdPosition,Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player=gameBoard.getActivePlayer();
        if(!player.isBeforeFirstPlayer()){
            switch(action){
                case "moveMoveReloadShoot":
                    moveMoveReloadShoot(position,secondPosition,weaponName,useSecondaryEffect,useTertiaryEffect);
                case"threeMovesGrab":
                    threeMovesGrab(position,secondPosition,thirdPosition,player.getWeaponByName(weaponName));

            }
        }

    }

    public synchronized void move (Square position, Square secondPosition, Square thirdPosition){
        Player player = gameBoard.getActivePlayer();
        player.moveAction(position, secondPosition, thirdPosition);
    }


    public synchronized void moveGrab (Square position, Weapon weapon){
        Player player = gameBoard.getActivePlayer();
        player.move(position);
        player.grabItem(gameBoard, weapon);
    }
    /*public synchronized void shoot(String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect) {
        Player player = gameBoard.getActivePlayer();
        Weapon weapon = player.getWeaponByName(weaponName);

        // TODO ability to use other effects if more than one
        List<Weapon.Effect> effects = weapon.getPrimaryEffect().get(0);
        List<Weapon.Effect> secondaryEffects= weapon.getSecondaryEffect().get(0);
        List<Weapon.Effect> tertiaryEffects= weapon.getTertiaryEffect().get(0);

        weapon.payPrimaryCost();
        for (Weapon.Effect effect : effects) {
            weapon.effect(effect);
        }

        if(useSecondaryEffect){
            weapon.paySecondaryCost();
            for (Weapon.Effect effect : secondaryEffects) {
                weapon.effect(effect);
            }
        }

        if(useTertiaryEffect){
            weapon.payTertiaryCost();
            for (Weapon.Effect effect : tertiaryEffects) {
                weapon.effect(effect);
            }
        }
    }
    public synchronized void moveReloadShoot(Square position, String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player= gameBoard.getActivePlayer();
        player.move(position);
        player.reload(player.getWeaponByName(weaponName));
        shoot(weaponName, useSecondaryEffect, useTertiaryEffect);
    }

    public synchronized void moveMove(Square position, Square secondPosition, Square thirdPosition, Square fourthPosition){
        Player player=gameBoard.getActivePlayer();
        player.moveAction(position,secondPosition,thirdPosition);
        player.move(fourthPosition);
    }
    public synchronized void moveMoveGrab(Square positon, Weapon weapon, Square secondPosition){
        Player player=gameBoard.getActivePlayer();
        player.move(positon);
        player.move(secondPosition);
        player.grabItem(gameBoard, weapon);
    }
    public synchronized void moveMoveReloadShoot(Square position, Square secondPosition,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player=gameBoard.getActivePlayer();
        player.move(position);
        player.move(secondPosition);
        player.reload(player.getWeaponByName(weaponName));
        shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
    }
    public synchronized void threeMovesGrab(Square position, Square secondPosition, Square thirdPosition, Weapon weapon){
        Player player=gameBoard.getActivePlayer();
        player.moveAction(position,secondPosition,thirdPosition);
        player.grabItem(gameBoard,weapon);
    }
    public synchronized void moveShoot(Square position,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player=gameBoard.getActivePlayer();
        player.move(position);
        shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
    }
    public synchronized void usePowerup(PowerUp powerUp){
        Player player = gameBoard.getActivePlayer();
        powerUp.effect(powerUp.getName());
    }*/
}


