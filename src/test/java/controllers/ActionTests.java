package controllers;

import messages.GameSettingsMessage;
import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import models.Square;
import models.WeaponsSlot;
import models.cards.*;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTests {
    ActionController actionController;
    GameController gameController;
    GameBoard gameBoard;
    ClientInput clientInput;
    Player player;

    @BeforeEach
    void setup() {
        gameController = new GameController();
        player = new Player();
        player.setActive(true);
        player.setNickname("test");
        gameBoard = gameController.getGameBoard();
        gameBoard.addPlayer(player);
        actionController = new ActionController(gameController);
        clientInput = new ClientInput();

        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.setup(msg);
    }

   @Test
   void getPossibleActions(){
       player.setActionCounter(2);

       List<ActionController.Action> pActions = actionController.getPossibleActions();
        assertTrue(pActions.contains(ActionController.Action.SHOOT));
        assertTrue(pActions.contains(ActionController.Action.MOVE));
        assertTrue(pActions.contains(ActionController.Action.USE_POWER_UP));
        assertTrue(pActions.contains(ActionController.Action.RELOAD));
        assertFalse(pActions.contains(ActionController.Action.DISCARD_AND_SPAWN));
        assertFalse(pActions.contains(ActionController.Action.MOVE_MOVE_GRAB));

        player.setDead(true);
        pActions = actionController.getPossibleActions();
        assertTrue(pActions.contains(ActionController.Action.DISCARD_AND_SPAWN));

       player.setAdrenaline(1);
       pActions = actionController.getPossibleActions();
       assertTrue(pActions.contains(ActionController.Action.MOVE));
       assertTrue(pActions.contains(ActionController.Action.MOVE_GRAB));
       assertTrue(pActions.contains(ActionController.Action.SHOOT));
       assertTrue(pActions.contains(ActionController.Action.MOVE_MOVE_GRAB));

       player.setAdrenaline(2);
       pActions = actionController.getPossibleActions();
       assertTrue(pActions.contains(ActionController.Action.MOVE));
       assertTrue(pActions.contains(ActionController.Action.MOVE_GRAB));
       assertTrue(pActions.contains(ActionController.Action.SHOOT));
       assertTrue(pActions.contains(ActionController.Action.MOVE_MOVE_GRAB));
       assertTrue(pActions.contains(ActionController.Action.MOVE_SHOOT));

        gameBoard.finalFrenzy();
        player.setIsBeforeFirstPlayer(true);
        pActions = actionController.getPossibleActions();
        assertTrue(pActions.contains(ActionController.Action.MOVE_RELOAD_SHOOT));
       assertTrue(pActions.contains(ActionController.Action.FOUR_MOVE));
       assertTrue(pActions.contains(ActionController.Action.MOVE_MOVE_GRAB));

       player.setIsBeforeFirstPlayer(false);
       pActions = actionController.getPossibleActions();
       assertTrue(pActions.contains(ActionController.Action.MOVE_MOVE_RELOAD_SHOOT));
       assertTrue(pActions.contains(ActionController.Action.THREE_MOVE_GRAB));
    }

    @Test
    void move() {
        player.setPosition(gameBoard.getSquares().get(0));

        clientInput.position = 1;
        actionController.move(clientInput);
        assertEquals(player.getPosition().getNumber(), 1);
    }

   @Test
    void reload(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);

        player.addWeapon((Weapon) gameBoard.getWeaponsDeck().pick());
        Weapon weapon = player.getWeapons().get(0);
        weapon.forceReload();

        assertFalse(weapon.isLoaded());

        clientInput.weaponName = weapon.getName();

       ArrayList<Card.Color> cost = player.getWeaponByName(clientInput.weaponName).getRechargeCost();
       player.getCubes().addAll(cost);
       int nCubes= player.getCubes().size();
        actionController.reload(clientInput);
        assertEquals(player.getCubes().size(), nCubes-cost.size());
       assertTrue(weapon.isLoaded());
    }

    @Test
    void grabWeapon() {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        Square spawnPoint = gameBoard.getSquares().get(2);
        player.setPosition(spawnPoint);

        clientInput.weaponName = spawnPoint.getWeaponsSlot().getWeapons().get(0).getName();
        int nWeapons = player.getWeapons().size();

        actionController.grab(clientInput);

        assertEquals(player.getWeapons().size(), nWeapons + 1);
    }

    @Test
    void grabAmmo() {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.setPosition(gameBoard.getSquares().get(1));

        int nPowerUps = player.getPowerUps().size();
        int cubes = player.getCubes().size();

        actionController.grab(clientInput);

        assertTrue(player.getPowerUps().size() > nPowerUps || player.getCubes().size() > cubes);
    }

    @Test
    void discardPowerupAndSpawn() {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
        clientInput.powerUpIndex=0;
        int nPowerups = player.getPowerUps().size();
        actionController.discardPowerUpAndSpawn(clientInput);
        assertEquals(player.getPowerUps().size(), nPowerups - 1);
    }

    @Test
    void discardPowerUp() {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        clientInput.position = 1;
        clientInput.powerUpIndex=0;
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
        int nPowerups = player.getPowerUps().size();
        actionController.discard(clientInput);
        assertEquals(player.getPowerUps().size(), nPowerups - 1);
    }

    @Test
    void discardWeapon () {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.addWeapon((Weapon) gameBoard.getWeaponsDeck().pick());
        int nWeaponse = player.getWeapons().size();

        clientInput.weaponName = player.getWeapons().get(0).getName();
        actionController.discard(clientInput);
        assertEquals(player.getWeapons().size(), nWeaponse - 1);
    }
    @Test
    void getParameters(){
        WeaponsDeck weaponsDeck= new WeaponsDeck();
        Weapon weapon= (Weapon)weaponsDeck.pick();
        Effect effect= weapon.getEffects(1).get(0);
        Action action= effect.getActions().get(0);
        HashMap<Effect.Input, Integer> map= action.getParameters();
        assertTrue(map.size()>0);
        String firstInput= map.keySet().toArray()[0].toString();
        assertDoesNotThrow(()->{
            Effect.Input.valueOf(firstInput);
        });
        assertNotNull(map.get( Effect.Input.valueOf(firstInput)));
    }

    @Test
    void userPowerup() {
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.setPosition(gameBoard.getSquares().get(0));
        clientInput.positions.add(1);
        clientInput.powerUpIndex=0;

        PowerUp powerUp = (PowerUp) gameBoard.getPowerUpsDeck().pick();

        while (!powerUp.getName().equals("Teleporter")) {
            powerUp = (PowerUp) gameBoard.getPowerUpsDeck().pick();
        }

        player.addPowerUp(powerUp);

        actionController.usePowerUp(clientInput);
        assertEquals(player.getPowerUps().size(), 0);
        assertEquals(player.getPosition(), gameBoard.getSquares().get(1));
    }

    @Test
    void shoot() {
        gameController.getGameBoard().addPlayer(player);

        Player player1 = new Player();
        player1.setNickname("target");
        gameBoard.addPlayer(player1);

        player.setPosition(gameBoard.getSquares().get(0));
        player1.setPosition(gameBoard.getSquares().get(0));

        player.setActive(true);

        clientInput.players.add("target");
        clientInput.effectType = 1;
        clientInput.useSecondPrimary = false;
        clientInput.weaponName = "Sledgehammer";

        // Bad way of searching for a weapon but the only one

        Weapon weapon = (Weapon) gameBoard.getWeaponsDeck().pick();
        boolean notFoundInDeck = false;

        while (!notFoundInDeck && !weapon.getName().equals("Sledgehammer")) {
            weapon = (Weapon) gameBoard.getWeaponsDeck().pick();
            if (weapon == null) notFoundInDeck = true;
        }

        WeaponsSlot weaponsSlot1 = gameBoard.getSquares().get(4).getWeaponsSlot();
        WeaponsSlot weaponsSlot2 = gameBoard.getSquares().get(11).getWeaponsSlot();
        WeaponsSlot weaponsSlot3 = gameBoard.getSquares().get(2).getWeaponsSlot();

        // If not found in deck, see weapons slots
        if (weapon == null || !weapon.getName().equals("Sledgehammer")) {
            for (Weapon w : weaponsSlot1.getWeapons()) {
                if (w.getName().equals("Sledgehammer")) {
                    weapon = w;
                    break;
                }
            }
        }

        if (weapon == null || !weapon.getName().equals("Sledgehammer")) {
            for (Weapon w : weaponsSlot2.getWeapons()) {
                if (w.getName().equals("Sledgehammer")) {
                    weapon = w;
                    break;
                }
            }
        }

        if (weapon == null || !weapon.getName().equals("Sledgehammer")) {
            for (Weapon w : weaponsSlot3.getWeapons()) {
                if (w.getName().equals("Sledgehammer")) {
                    weapon = w;
                    break;
                }
            }
        }


        player.addWeapon(weapon);

        actionController.shoot(clientInput);
        assertTrue(player.getWeapons().get(0).isLoaded());
        assertEquals(player1.getDamage().size(), 2);
    }

}




