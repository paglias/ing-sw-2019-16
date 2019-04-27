package models;

import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void totalPoints () {
        Player player = new Player();
        assertEquals(player.getTotalPoints(), 0);

        player.addToTotalPoints(5);
        assertEquals(player.getTotalPoints(), 5);

        player.addToTotalPoints(5);
        assertEquals(player.getTotalPoints(), 10);
    }

    @Test
    void actionCounter () {
        Player player = new Player();
        player.setActionCounter(5);
        assertEquals(player.getActionCounter(), 5);

        player.decreaseActionCounter();
        assertEquals(player.getActionCounter(), 4);
    }

    @Test
    void adrenaline () {
        Player player = new Player();
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
    void cubes () {
        Player player = new Player();
        assertEquals(player.getCubes().size(), 0);

        player.addCube(Card.Color.BLUE);
        assertEquals(player.getCubes().size(), 1);
        assertEquals(player.getCubes().get(0),  Card.Color.BLUE);

        player.addCube(Card.Color.RED);
        assertEquals(player.getCubes().size(), 2);
        assertEquals(player.getCubes().get(1),  Card.Color.RED);

        player.removeCube(Card.Color.BLUE);
        assertThrows(IllegalArgumentException.class, () -> {
            player.removeCube(Card.Color.BLUE);
        });

        assertEquals(player.getCubes().size(), 1);
        assertEquals(player.getCubes().get(0),  Card.Color.RED);
    }

    @Test
    void moveCounter () {
        Player player = new Player();
        player.setMoveCounter(3);
        assertEquals(player.getMoveCounter(), 3);

        player.decreaseMoveCounter();
        assertEquals(player.getMoveCounter(), 2);
    }

    @Test
    void nickname () {
        Player player = new Player();
        player.setNickname("test");

        assertEquals(player.getNickname(), "test");
    }

    @Test
    void marks () {
        Player player = new Player();
        Player player1 = new Player();
        Player player2 = new Player();

        player.addMark(player1);
        player.addMark(player2);
        player.addMark(player2);

        assertEquals(player.getMarks().size(), 3);
        assertEquals(player.getMarks().get(0), player1);
        assertEquals(player.getMarks().get(1), player2);
        assertEquals(player.getMarks().get(2), player2);
    }

    @Test
    void powerUps () {
        Player player = new Player();
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
    void position () {
        Player player = new Player();
        Square position = new Square(Square.Color.YELLOW, false);

        assertNull(player.getPosition());
        player.setPosition(position);
        assertEquals(player.getPosition(), position);
    }

    @Test
    void nDeaths () {
        Player player = new Player();
        assertEquals(player.getNDeaths(), 0);

        player.increaseNDeaths();
        player.increaseNDeaths();
        assertEquals(player.getNDeaths(), 2);
    }

    @Test
    void firstPlayer () {
        Player player = new Player();
        player.setFirstPlayer(true);
        assertTrue(player.getFirstPlayer());
    }

    @Test
    void weapons () {
        Player player = new Player();
        assertEquals(player.getWeapons().size(), 0);

        Weapon weapon1 = new Weapon();
        Weapon weapon2 = new Weapon();

        player.addWeapon(weapon1);
        player.addWeapon(weapon2);
        player.addWeapon(new Weapon());
        player.addWeapon(new Weapon());

        assertThrows(IllegalArgumentException.class, () -> {
            player.addWeapon(new Weapon());
        });

        assertEquals(player.getWeapons().size(), 4);
        assertEquals(player.getWeapons().get(0), weapon1);

        player.removeWeapon(weapon2);
        assertEquals(player.getWeapons().size(), 3);
        assertEquals(player.getWeapons().get(0), weapon1);
    }

    @Test
    void damage () {
        Player player = new Player();
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
    void dead () {
        Player player = new Player();
        assertFalse(player.isDead());

        player.setDead(true);
        assertTrue(player.isDead());
    }
}
