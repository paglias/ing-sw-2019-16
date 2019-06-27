package controllers;

import messages.GameSettingsMessage;
import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.PowerUp;
import models.cards.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTests {
    ActionController actionController;
    GameController gameController;
    GameBoard gameBoard;
    ClientInput clientInput;
    Player player;
    Weapon weapon;


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

        gameBoard.finalFrenzy();
        pActions = actionController.getPossibleActions();
        assertTrue(pActions.contains(ActionController.Action.MOVE_MOVE_RELOAD_SHOOT));
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
        int nCubes= player.getCubes().size();
        clientInput.weaponName="Electroscythe";
        int cost=player.getWeaponByName(clientInput.weaponName).getRechargeCost().size();
        actionController.reload(clientInput);
        assertEquals(player.getCubes().size(), nCubes-cost);
    }

    @Test
    void grab() {
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
    void usePowerUp() {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
        //player.setPosition(square1);
        clientInput.position = 1;
        clientInput.powerUpIndex=0;
        int nPowerups = player.getPowerUps().size();
        actionController.usePowerUp(clientInput);
        assertEquals(player.getPowerUps().size(), nPowerups - 1);
        assertEquals(player.getPosition().getNumber(), 1);
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
    void discard() {
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        clientInput.position = 1;
        clientInput.powerUpIndex=0;
        player.addPowerUp((PowerUp) gameBoard.getPowerUpsDeck().pick());
        int nPowerups = player.getPowerUps().size();
        actionController.usePowerUp(clientInput);
        Weapon weapon = player.getWeaponByName(clientInput.weaponName);
        int nWeapons = player.getWeapons().size();
        assertEquals(player.getPowerUps().size(), nPowerups - 1);
        assertEquals(player.getWeapons().size(), nWeapons - 1);
    }


}




