package controllers;

import messages.GameSettingsMessage;
import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.Action;
import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import org.graalvm.compiler.nodes.calc.PointerEqualsNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class ActionTests {
    GameBoard gameBoard;
    List<Action> ActionsList= new ArrayList<>();
    ActionController actionController;
    GameController gameController;
    Square square1;
    Square square2;
    Square square3;
    Square square4;
    ClientInput clientInput;
    Player player;


    @BeforeEach
    void setup(){
        gameBoard= new GameBoard();
        gameController= new GameController();
        actionController= new ActionController(gameController);
        clientInput= new ClientInput();
        Player player = new Player();





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
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.setPosition(square1);
        square1.addCanAccessSquare(square2);
        square1.setNumber(0);
        square2.setNumber(1);
        gameController.setup(msg);
        clientInput.position=1;
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
        clientInput.position=1
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
    }






}
