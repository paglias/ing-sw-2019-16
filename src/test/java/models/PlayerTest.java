package models;

import controllers.ActionController;
import controllers.GameController;
import models.cards.*;
import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    GameBoard gameBoard;


    @BeforeEach
    void setup() {
        player = new Player();
        player.setNickname("First");
        gameBoard = new GameBoard();
        gameBoard.addPlayer(player);
    }

    @Test
    void givenPoints(){
        ArrayList<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(2);
        points.add(3);
        points.add(4);
        points.add(8);
        points.add(10);
        player.setGivenPoints(points);
        assertEquals(player.getGivenPoints(), points);

    }

    @Test
    void totalPoints() {
        assertEquals(player.getTotalPoints(), 0);

        player.addToTotalPoints(5);
        assertEquals(player.getTotalPoints(), 5);

        player.addToTotalPoints(5);
        assertEquals(player.getTotalPoints(), 10);
    }

    @Test
    void actionCounter() {
        player.setActionCounter(5);
        assertEquals(player.getActionCounter(), 5);

        player.decreaseActionCounter();
        assertEquals(player.getActionCounter(), 4);
    }

    @Test
    void setActive(){
        Player player1 = new Player();
        assertFalse(player1.isActive());
        player1.setActive(true);
        assertTrue(player1.isActive());
    }
    @Test
    void adrenaline() {
        player.setAdrenaline(1);
        assertEquals(player.getAdrenaline(), 1);

        player.increaseAdrenaline();
        assertEquals(player.getAdrenaline(), 2);

        assertThrows(IllegalArgumentException.class, () -> {
            player.increaseAdrenaline();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            player.setAdrenaline(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            player.setAdrenaline(3);
        });
    }

    @Test
    void cubes() {
        // Remove default cubes for testing
        player.removeCube(Card.Color.BLUE);
        player.removeCube(Card.Color.RED);
        player.removeCube(Card.Color.YELLOW);

        assertEquals(player.getCubes().size(), 0);

        player.addCube(Card.Color.BLUE);
        assertEquals(player.getCubes().size(), 1);
        assertEquals(player.getCubes().get(0), Card.Color.BLUE);

        player.addCube(Card.Color.RED);
        assertEquals(player.getCubes().size(), 2);
        assertEquals(player.getCubes().get(1), Card.Color.RED);

        player.removeCube(Card.Color.BLUE);
        assertThrows(IllegalArgumentException.class, () -> {
            player.removeCube(Card.Color.BLUE);
        });

        assertEquals(player.getCubes().size(), 1);
        assertEquals(player.getCubes().get(0), Card.Color.RED);

        player.addCube(Card.Color.BLUE);
        player.addCube(Card.Color.BLUE);
        player.addCube(Card.Color.BLUE);
        assertThrows(IllegalArgumentException.class, ()->{
            player.addCube(Card.Color.BLUE);
        });
    }

    @Test
    void nickname() {
        player.setNickname("test");

        assertEquals(player.getNickname(), "test");
    }

    @Test
    void marks() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setNickname("John");
        player2.setNickname("Mark");

        player.addMark(player1);
        player.addMark(player2);
        player.addMark(player2);


        assertEquals(player.getMarks().size(), 3);
        assertEquals(player.getMarks().get(0), player1);
        assertEquals(player.getMarks().get(1), player2);
        assertEquals(player.getMarks().get(2), player2);
    }

    @Test
    void marksThrowsIllegalArgument(){
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setNickname("John");
        player2.setNickname("Mark");

        player.addMark(player1);
        player.addMark(player2);
        player.addMark(player2);
        assertEquals(player.getMarks().size(), 3);
        player.addMark(player2);
        assertThrows(IllegalArgumentException.class, () -> {
            player.addMark(player2);
        });
    }

    @Test
    void powerUps() {
        player.addPowerUp((PowerUp)gameBoard.getPowerUpsDeck().pick());
        PowerUp powerUp=player.getPowerUps().get(player.getPowerUps().size()-1);
        int size=player.getPowerUps().size();
        player.addPowerUp(powerUp);
        assertEquals(player.getPowerUps().size(), size+1);

    }

    @Test
    void position() {
        Square position = new Square(Square.Color.YELLOW, false);

        assertNull(player.getPosition());
        player.setPosition(position);
        assertEquals(player.getPosition(), position);
    }

    @Test
    void nDeaths() {
        assertEquals(player.getNDeaths(), 0);

        player.increaseNDeaths();
        player.increaseNDeaths();
        assertEquals(player.getNDeaths(), 2);
    }

    @Test
    void firstPlayer() {
        player.setFirstPlayer(true);
        assertTrue(player.isFirstPlayer());
    }

    @Test
    void weapons() {
        assertEquals(player.getWeapons().size(), 0);

        Weapon weapon1 = new Weapon();
        Weapon weapon2 = new Weapon();

        player.addWeapon(weapon1);
        player.addWeapon(weapon2);
        player.addWeapon(new Weapon());

        assertThrows(IllegalArgumentException.class, () -> {
            player.addWeapon(new Weapon());
        });

        assertEquals(player.getWeapons().size(), 3);
        assertEquals(player.getWeapons().get(0), weapon1);

        GameBoard gameBoard = new GameBoard();
        gameBoard.setMap(1);

        player.removeWeapon(weapon2);
        assertEquals(player.getWeapons().size(), 2);
        assertEquals(player.getWeapons().get(0), weapon1);

        player.getWeapons().clear();
        assertThrows(IllegalArgumentException.class, ()->{
            player.removeWeapon(weapon1);
        });
    }
    @Test
    void reloadWeapon(){
        player.getCubes().clear();
        player.addWeapon((Weapon)gameBoard.getWeaponsDeck().pick());
        Weapon weapon1=player.getWeapons().get(0);
        assertThrows(IllegalArgumentException.class, ()-> {
            player.reload(weapon1);
        });


    }

    @Test
    void damage() {
        Player player1 = new Player();

        assertEquals(player.getDamage().size(), 0);

        player.addDamage(player1);
        assertEquals(player.getDamage().size(), 1);
        assertEquals(player.getDamage().get(0), player1);

        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);

        assertEquals(player.getDamage().size(), 12);
        assertThrows(IllegalArgumentException.class, () -> {
            player.addDamage(player1);
        });
    }

    @Test
    void dead() {
        assertFalse(player.isDead());

        player.setDead(true);
        assertTrue(player.isDead());
    }

    @Test
    void movePlayerThrowsNoAccessSquare() {
        Square current = new Square(Square.Color.BLUE, false);
        player.setPosition(current);
        Square target = new Square(Square.Color.BLUE, false);

        assertThrows(IllegalArgumentException.class, () -> {
            player.move(target);
        });
    }

    @Test
    void movePlayer() {
        Square current = new Square(Square.Color.BLUE, false);
        player.setPosition(current);

        Square target = new Square(Square.Color.BLUE, false);
        current.addCanAccessSquare(target);

        player.move(target);
        assertEquals(player.getPosition(), target);

        Square notAccessable= new Square(Square.Color.RED, false);
        assertThrows(IllegalArgumentException.class, ()->{
            player.move(notAccessable);
        });
    }

    @Test
    void calculateDeathPoints() {
        gameBoard.setMap(1);
        ArrayList<Integer> newPlayerPoints = new ArrayList<>();

        Player playerA = new Player();
        playerA.setNickname("Alfa");
        gameBoard.addPlayer(playerA);

        Player playerB = new Player();
        playerB.setNickname("Beta");
        gameBoard.addPlayer(playerB);

        Player playerC = new Player();
        playerC.setNickname("Delta");
        gameBoard.addPlayer(playerC);

        Player playerD = new Player();
        playerD.setNickname("Gamma");
        gameBoard.addPlayer(playerD);

        Player playerE=new Player();
        playerE.setNickname("Echo");
        gameBoard.addPlayer(playerE);

        newPlayerPoints.add(1);
        newPlayerPoints.add(1);
        newPlayerPoints.add(2);
        newPlayerPoints.add(4);
        newPlayerPoints.add(5);
        newPlayerPoints.add(7);

        player.setGivenPoints(newPlayerPoints);
        player.addDamage(playerA);
        player.addDamage(playerA);
        player.addDamage(playerD);
        player.addDamage(playerC);
        player.addDamage(playerA);
        player.addDamage(playerB);
        player.addDamage(playerC);
        player.addDamage(playerD);
        player.addDamage(playerA);
        player.addDamage(playerD);
        player.addDamage(playerD);

        player.calculateDeathPoints();

        assertEquals(playerA.getTotalPoints(), 8);
        assertEquals(playerD.getTotalPoints(), 5);
        assertEquals(playerC.getTotalPoints(), 4);
        assertEquals(playerB.getTotalPoints(), 2);
        int size= playerE.getGivenPoints().size();
        size=0;
        int points=playerE.getTotalPoints();
        player.calculateDeathPoints();
        assertEquals(playerE.getTotalPoints(), points);
    }

    @Test
    void discardItem(){
        player.addPowerUp((PowerUp)gameBoard.getPowerUpsDeck().pick());
        PowerUp powerUp=player.getPowerUps().get(player.getPowerUps().size()-1);
        int size=player.getPowerUps().size();
        player.discardItem(powerUp);
        assertEquals(player.getPowerUps().size(), size-1);
    }
    @Test
    void discardWeapon(){
        player.addWeapon((Weapon)gameBoard.getWeaponsDeck().pick());
        Weapon weapon=player.getWeapons().get(player.getWeapons().size()-1);
        int size=player.getWeapons().size();
        player.removeWeapon(weapon);
        assertEquals(player.getWeapons().size(), size-1);
    }

    @Test
    void setPossibleActions(){
        List<ActionController.Action>pActions=new ArrayList<>();
        player.setActionCounter(2);
        player.setPossibleActions(pActions);
        assertFalse(pActions.size()>0);
    }
    @Test
    void setStartTurnDate(){
        Date date=new Date();
        player.setStartTurnDate(date);
        assertNotNull(date);;

    }
    @Test
    void getActiveActionsItem(){
        List<ActionController.Action>pActions=new ArrayList<>();
        player.getActiveActionItems();
        assertFalse(pActions.size()>0);
    }

    @Test
    void setActiveAction(){
        List<ActionController.Action>pActions=new ArrayList<>();
        pActions.add(ActionController.Action.MOVE);
        player.setActiveAction(pActions.get(0));
        assertTrue(pActions.size()>0);
    }
    @Test
    void afterShoot(){
        Player player1=new Player();
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        assertEquals(player.getDamage().size(), 3);
        player.setAdrenaline(0);
        player1.afterShoot(player);
        assertEquals(player.getAdrenaline(), 1);

    }
    @Test
    void afterShoot2(){
        Player player1=new Player();
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        assertEquals(player.getDamage().size(), 6);
        player.setAdrenaline(1);
        player1.afterShoot(player);
        assertEquals(player.getAdrenaline(), 2);

    }
    @Test
    void afterShoot3(){
        gameBoard.getSkulls().setNRemaining(5);
        Player player1=new Player();
        player1.setNickname("test");

        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);
        player.addDamage(player1);

        gameBoard.addPlayer(player1);

        assertEquals(player.getDamage().size(), 11);
        int size=player.getNDeaths();
        int killers=gameBoard.getSkulls().getKillers().size();
        player.addPowerUp((PowerUp)gameBoard.getPowerUpsDeck().pick());

        player1.afterShoot(player);

        assertTrue(player.isDead());
        assertEquals(player.getNDeaths(), size+1);
        gameBoard.getSkulls().setNRemaining(0);
        assertFalse(gameBoard.isFinalFrenzy());
        gameBoard.getSkulls().setNRemaining(2);
        assertEquals(gameBoard.getSkulls().getKillers().size(), killers+1);


    }

    @Test
    void grabItemWeapon(){
        gameBoard.setMap(1);
        player.setPosition(gameBoard.getSquares().get(4));

        Weapon weapon = player.getPosition().getWeaponsSlot().getWeapons().get(0);
        player.grabItem(weapon.getName());

        assertDoesNotThrow(() -> {
            player.getWeaponByName(weapon.getName());
        });
    }

    @Test
    void grabItemAmmo(){
        gameBoard.setMap(1);
        player.setPosition(gameBoard.getSquares().get(0));

        int cubesN = player.getCubes().size();
        int powerupN = player.getPowerUps().size();


        player.grabItem(null);

        assertTrue(player.getPowerUps().size() > powerupN || player.getCubes().size() > cubesN);
    }

    @Test
    void FinalFrenzyDone(){
        player.finalFrenzyDone();
        assertFalse(player.finalFrenzyDone());
    }
    @Test
    void setFinalFrenzyDone(){
        player.setFinalFrenzyDone();
        assertTrue(player.finalFrenzyDone());
    }


}

