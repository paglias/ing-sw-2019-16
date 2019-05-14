package controllers;

import messages.AbstractMessage;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.PowerUp;
import models.cards.Weapon;

import java.util.*;

public class GameController {
    private static int gameStartTimeout = 5;
    private static int turnTimeout = 5;

    private GameBoard gameBoard;
    private ArrayList<ClientController> clients = new ArrayList<>();

    public GameBoard getGameBoard () { return gameBoard; }

    public GameController () {
        gameBoard = new GameBoard();
    }

    public void addClient (ClientController clientController) {
        clients.add(clientController);
    }

    public void dispatchToClients (AbstractMessage msg) {
        String serialized = msg.serialize();
        clients.stream().forEach(c -> c.sendMsg(serialized));
    }

    public void setMap (int mapNumper) {
        if (!gameBoard.getSquares().isEmpty()) {
            throw new IllegalArgumentException("Map already loaded.");
        }
        gameBoard.setMap(mapNumper);
    }

    public void addPlayer(String nickname, ClientController clientController) {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("GameController already started, cannot join.");
        }

        if (nickname == null) throw new IllegalArgumentException("Nickname must exist");

        Random random = new Random();
        Square.Color[] colors = Square.Color.values();
        Square.Color color = colors[random.nextInt(colors.length)];

        Player player = new Player();
        player.setNickname(nickname);
        player.setColor(color);

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
    }

    public void start() {
        if (gameBoard.hasStarted()) {
            throw new IllegalArgumentException("GameController already started, cannot create a new one.");
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
        if(!gameBoard.isFinalFrenzy()) {
            player.setActionCounter(2);
        }
        if(gameBoard.getSkulls().getNRemaining()==0){
            gameBoard.finalFrenzy();
        }
    }

    public void endTurn() {
        Player currentPlayer=gameBoard.getActivePlayer();
        for(Player player: gameBoard.getPlayers()){
            player.playerIsDead(currentPlayer, gameBoard);
       }// controlla quali tra i giocatori sono morti

        gameBoard.nextPlayer(gameBoard.getActivePlayer());
        startTurn();


    }

    public void action(Square position, Weapon weapon, Square secondPosition, Square thirdPosition, String action,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect) {
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
                    //shoot
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
                        //shoot
                    case"moveMoveGrab":
                        moveMoveGrab(position,weapon,secondPosition);
                    case"moveShoot":
                        moveShoot(position,weaponName,useSecondaryEffect,useTertiaryEffect);
                }
            }
        }
        }
    public void actionBeforePlayer(String action,Square position,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect, Square secondPosition, Square thirdPosition, Square fourthPosition){
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
    public void actionAfterPlayer(String action, Square position, String weaponName, Square secondPosition,Square thirdPosition,Boolean useSecondaryEffect, Boolean useTertiaryEffect){
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

    public void move (Square position, Square secondPosition, Square thirdPosition){
        Player player = gameBoard.getActivePlayer();
        player.moveAction(position, secondPosition, thirdPosition);
    }

    public void moveGrab (Square position, Weapon weapon){
        Player player = gameBoard.getActivePlayer();
        player.move(position);
        player.grabItem(gameBoard, weapon);

    }
    public void shoot(String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect) {
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
    public void moveReloadShoot(Square position, String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player= gameBoard.getActivePlayer();
        player.move(position);
        player.reload(player.getWeaponByName(weaponName));
        shoot(weaponName, useSecondaryEffect, useTertiaryEffect);
    }

    public void moveMove(Square position, Square secondPosition, Square thirdPosition, Square fourthPosition){
        Player player=gameBoard.getActivePlayer();
        player.moveAction(position,secondPosition,thirdPosition);
        player.move(fourthPosition);
    }
    public void moveMoveGrab(Square positon, Weapon weapon, Square secondPosition){
        Player player=gameBoard.getActivePlayer();
        player.move(positon);
        player.move(secondPosition);
        player.grabItem(gameBoard, weapon);
    }
    public void moveMoveReloadShoot(Square position, Square secondPosition,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player=gameBoard.getActivePlayer();
        player.move(position);
        player.move(secondPosition);
        player.reload(player.getWeaponByName(weaponName));
        shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
    }
    public void threeMovesGrab(Square position, Square secondPosition, Square thirdPosition, Weapon weapon){
        Player player=gameBoard.getActivePlayer();
        player.moveAction(position,secondPosition,thirdPosition);
        player.grabItem(gameBoard,weapon);
    }
    public void moveShoot(Square position,String weaponName, Boolean useSecondaryEffect, Boolean useTertiaryEffect){
        Player player=gameBoard.getActivePlayer();
        player.move(position);
        shoot(weaponName,useSecondaryEffect,useTertiaryEffect);
    }

    public void usePowerup(PowerUp powerUp){
        Player player=gameBoard.getActivePlayer();
        powerUp.effect(powerUp.getName());
    }



}

