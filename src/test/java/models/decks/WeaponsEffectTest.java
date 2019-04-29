package models.decks;

import static org.junit.jupiter.api.Assertions.*;
import models.Player;
import models.Square;
import models.cards.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeaponsEffectTest {
    Weapon weapon;
    Player player1;
    Player player2;


    @BeforeEach
    void setup() {
        weapon = new Weapon();
        player1 = new Player();
        player2 = new Player();
    }


    @Test
    void shoot() {
        player1.setPosition(1);
        player2.setPosition(1);
        int nDamage = player2.getDamage().size();
        weapon.shoot(player1, player2);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));
    }

    @Test
    void mark() {
        player1.setPosition(1);
        player2.setPosition(1);
        int nMarks = player2.getMarks().size();
        weapon.mark(player1, player2);
        assertEquals(player2.getMarks().size(), nMarks + 1);
        assertTrue(player2.getMarks().contains(player1));

    }

    @Test
    void move() {
        player1.setPosition(1);
        Square newPosition = new Square(Square.Color.PURPLE, false);
        newPosition.setPosition(5);
        weapon.move(player1, newPosition);
        assertEquals(newPosition, 1);
    }

    @Test
    void shootOneAwayView() {
        player1.setPosition(1);
        player2.setPosition(4);
        int nDamage = player2.getDamage().size();
        weapon.shootOneAwayView(player1, player2);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertFalse(player2.getDamage().contains(player1));
    }

    @Test
    void shootTwoAwayView() {
        player1.setPosition(1);
        player2.setPosition(3);
        int nDamage = player2.getDamage().size();
        weapon.shootTwoAwayView(player1, player2);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertFalse(player2.getDamage().contains(player1));

    }

    @Test
    void markOneAwayView() {
        player1.setPosition(1);
        player2.setPosition(4);
        int nDamage = player2.getMarks().size();
        weapon.markOneAwayView(player1, player2);
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertFalse(player2.getMarks().contains(player1));

    }

    @Test
    void markTwoAwayView() {
        player1.setPosition(1);
        player2.setPosition(3);
        int nDamage = player2.getMarks().size();
        weapon.markTwoAwayView(player1, player2);
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertFalse(player2.getMarks().contains(player1));

    }

    @Test
    void shootEvery() {
        player1.setPosition(1);
        Player player3 = new Player();
        ArrayList<Player> Players = new ArrayList<Player>();
        Players.add(player2);
        Players.add(player3);
        player2.setPosition(1);
        player3.setPosition(1);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootEvery(player1, Players);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }


    @Test
    void markEvery() {
        player1.setPosition(1);
        Player player3 = new Player();
        ArrayList<Player> Players = new ArrayList<Player>();
        Players.add(player2);
        Players.add(player3);
        player2.setPosition(1);
        player3.setPosition(1);
        int nDamage = player2.getMarks().size();
        int nDamage3 = player3.getMarks().size();
        weapon.markEvery(player1, Players);
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertEquals(player3.getMarks().size(), nDamage3 + 1);
        assertTrue(player2.getMarks().contains(player1));
        assertTrue(player3.getMarks().contains(player1));
    }

    @Test
    void shootRoomCanSee() {
        player1.setPosition(1);
        Player player3 = new Player();
        ArrayList<Player> Players = new ArrayList<Player>();
        Players.add(player2);
        Players.add(player3);
        player2.setPosition(4);
        player3.setPosition(5);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootRoomCanSee(player1, Players);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }

    @Test
    void shootEveryOneAwayView() {
        player1.setPosition(1);
        Player player3 = new Player();
        ArrayList<Player> Players = new ArrayList<Player>();
        Players.add(player2);
        Players.add(player3);
        player2.setPosition(2);
        player3.setPosition(2);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootEveryOneAwayView(player1, Players);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }

    @Test
    void markEveryOneAwayView() {
        player1.setPosition(1);
        Player player3 = new Player();
        ArrayList<Player> Players = new ArrayList<Player>();
        Players.add(player2);
        Players.add(player3);
        player2.setPosition(2);
        player3.setPosition(2);
        int nDamage = player2.getMarks().size();
        int nDamage3 = player3.getMarks().size();
        weapon.markEveryOneAwayView(player1, Players);
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertEquals(player3.getMarks().size(), nDamage3 + 1);
        assertTrue(player2.getMarks().contains(player1));
        assertTrue(player3.getMarks().contains(player1));
    }

    @Test
    void shootView() {
        player1.setPosition(1);
        player2.setPosition(2);
        int nDamage = player2.getDamage().size();
        weapon.shootView(player1, player2);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));
    }

    @Test
    void markView() {
        player1.setPosition(1);
        player2.setPosition(2);
        int nDamage = player2.getMarks().size();
        weapon.markView(player1, player2);
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertTrue(player2.getMarks().contains(player1));
    }

    @Test
    void moveTarget() {
        player1.setPosition(1);
        Square newPosition = new Square(Square.Color.PURPLE, false);
        newPosition.setPosition(5);
        weapon.moveTarget(player1, newPosition);
        assertEquals(newPosition, 1);
    }

    @Test
    void shootDirection() {
        player1.setPosition(1);
        player2.setPosition(3);
        int nDamage = player2.getDamage().size();
        weapon.shootDirection(player1, player2);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));
    }

    @Test
    void shootTargetView() {
        player1.setPosition(1);
        player2.setPosition(2);
        Player player3 = new Player();
        player3.setPosition(3);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootTargetView(player1, player2, player3);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }

    @Test
    void ShootSecondTargetView() {
        player1.setPosition(1);
        player2.setPosition(2);
        Player player3 = new Player();
        Player player4 = new Player();
        player3.setPosition(3);
        player4.setPosition(4);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        int nDamage4 = player4.getDamage().size();
        weapon.shootSecondTargetView(player1, player2, player3, player4);
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertEquals(player4.getDamage().size(), nDamage4 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
        assertTrue(player4.getDamage().contains(player1));
    }

    @Test
    void AttractTarget() {
        player1.setPosition(1);
        player2.setPosition(2);
        Square newPosition = new Square(Square.Color.PURPLE, false);
        newPosition.setPosition(5);
        weapon.attractTarget(player1, player2, newPosition);
        assertEquals(newPosition, 1);

    }

    @Test
    void ShootCantSee() {
        player1.setPosition(1);
        player2.setPosition(10);
        int nDamage = player2.getDamage().size();
        weapon.ShootCantSee(player1, player2);
        assertEquals(player2.getDamage().size(), nDamage);
        assertFalse(player2.getDamage().contains(player1));
    }
}
