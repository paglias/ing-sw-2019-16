package models;

import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;

    @BeforeEach
    void setup() {
        player = new Player();
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
    }

    @Test
    void moveCounter() {
        player.setMoveCounter(3);
        assertEquals(player.getMoveCounter(), 3);

        player.decreaseMoveCounter();
        assertEquals(player.getMoveCounter(), 2);
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
    void powerUps() {
        PowerUp powerUp1 = new PowerUp(PowerUp.Name.NEWTON, Card.Color.BLUE);
        PowerUp powerUp2 = new PowerUp(PowerUp.Name.TELEPORTER, Card.Color.BLUE);

        player.addPowerUp(powerUp1);
        player.addPowerUp(powerUp2);

        assertEquals(player.getPowerUps().size(), 2);
        player.removePowerUp(powerUp1);

        assertEquals(player.getPowerUps().size(), 1);
        assertEquals(player.getPowerUps().get(0), powerUp2);
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

        player.removeWeapon(gameBoard, weapon2);
        assertEquals(player.getWeapons().size(), 2);
        assertEquals(player.getWeapons().get(0), weapon1);
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
    void movePlayerThrowsNoAvailableMoves() {
        player.setMoveCounter(0);
        Square current = new Square(Square.Color.BLUE, false);
        player.setPosition(current);
        Square target = new Square(Square.Color.BLUE, false);

        assertThrows(IllegalArgumentException.class, () -> {
            player.move(target);
        });
    }

    @Test
    void movePlayerThrowsNoAccessSquare() {
        player.setMoveCounter(1);
        Square current = new Square(Square.Color.BLUE, false);
        player.setPosition(current);
        Square target = new Square(Square.Color.BLUE, false);

        assertThrows(IllegalArgumentException.class, () -> {
            player.move(target);
        });
    }

    @Test
    void movePlayer() {
        player.setMoveCounter(1);

        Square current = new Square(Square.Color.BLUE, false);
        player.setPosition(current);

        Square target = new Square(Square.Color.BLUE, false);
        current.addCanAccessSquare(target);

        player.move(target);
        assertEquals(player.getPosition(), target);
        assertEquals(player.getMoveCounter(), 0);
    }

    @Test
    void calculateDeathPoints() {
        Player playerA = new Player();
        playerA.setNickname("Alfa");
        Player playerB = new Player();
        playerB.setNickname("Beta");
        Player playerC = new Player();
        playerC.setNickname("Delta");
        Player playerD = new Player();
        playerD.setNickname("Gamma");
        GameBoard newGameBoard = new GameBoard();
        newGameBoard.setMap(1);
        ArrayList<Integer> newPlayerPoints = new ArrayList<>();
        newGameBoard.addPlayer(playerA);
        newGameBoard.addPlayer(playerB);
        newGameBoard.addPlayer(playerC);
        newGameBoard.addPlayer(playerD);
        newPlayerPoints.add(1);
        newPlayerPoints.add(1);
        newPlayerPoints.add(2);
        newPlayerPoints.add(4);
        newPlayerPoints.add(6);
        newPlayerPoints.add(8);
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
    }
}

