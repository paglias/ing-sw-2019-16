package controllers;

import messages.GameSettingsMessage;
import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ActionTests {
    ActionController actionController;
    GameController gameController;
    GameBoard gameBoard;
    ClientInput clientInput;
    Player player;


    @BeforeEach
    void setup(){
        gameController= new GameController();
        player = new Player();
        player.setActive(true);
        player.setNickname("test");
        gameBoard = gameController.getGameBoard();
        gameBoard.addPlayer(player);
        actionController= new ActionController(gameController);
        clientInput= new ClientInput();

        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.setup(msg);
    }

   /* @Test
   void getPossibleActions(){
        int adrenaline=player.getAdrenaline();
        adrenaline=0;
        boolean finalFrenzy=gameBoard.isFinalFrenzy();
        finalFrenzy= false;


    }*/

    @Test
    void move(){
        player.setPosition(gameBoard.getSquares().get(0));

        clientInput.position=1;
        actionController.move(clientInput);
        assertEquals(player.getPosition().getNumber(), 1);
    }

    /*@Test
    void reload(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        Weapon weapon= player.getWeaponByName(clientInput.weaponName);
        ArrayList<Card.Color> cost= weapon.getRechargeCost();
        actionController.reload(clientInput);
        assertEquals(weapon.isLoaded(), true);
    }

    @Test
    void grab(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        Weapon weapon=player.getWeaponByName(clientInput.weaponName);
        int nWeapons= player.getWeapons().size();
        actionController.reload(clientInput);
        assertEquals(player.getWeapons().size(),nWeapons+1);

    }

    @Test
    void usePowerUp(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        System.out.println(gameController.getGameBoard().getPlayers().size());
        player.setActive(true);
        PowerUp powerUp= player.getPowerUps().get(clientInput.powerUpIndex);
        int nPowerups= player.getPowerUps().size();
        actionController.usePowerUp(clientInput);
        assertEquals(player.getPowerUps().size(), nPowerups-1);
    }

    @Test
    void discardPowerupAndSpawn(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.setPosition(square1);
        square1.isSpawnPoint();
        square1.setNumber(1);
        clientInput.position=1;
        PowerUp powerUp= player.getPowerUps().get(clientInput.powerUpIndex);
        int nPowerups= player.getPowerUps().size();
        actionController.usePowerUp(clientInput);
        assertEquals(player.getPowerUps().size(), nPowerups-1);
        assertEquals(player.getPosition().getNumber(), 1);
    }

    @Test
    void discard(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.setPosition(square1);
        square1.isSpawnPoint();
        square1.setNumber(1);
        clientInput.position=1;
        PowerUp powerUp= player.getPowerUps().get(clientInput.powerUpIndex);
        int nPowerups= player.getPowerUps().size();
        actionController.usePowerUp(clientInput);
        Card.Color color= powerUp.getColor();
        Weapon weapon=player.getWeaponByName(clientInput.weaponName);
        int nWeapons= player.getWeapons().size();
        assertEquals(player.getPowerUps().size(), nPowerups-1);
        assertEquals(player.getWeapons().size(), nWeapons-1);
    }*/






}
