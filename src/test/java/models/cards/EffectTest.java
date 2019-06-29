package models.cards;

import static org.junit.jupiter.api.Assertions.*;
import models.Player;
import models.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EffectTest {
    Weapon weapon;
    Player player1;
    Player player2;
    Player player3;
    Square square1;
    Square square2;
    Square square3;
    Square square4;

    @BeforeEach
    void setup() {
        weapon = new Weapon();
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        square1 = new Square(Square.Color.PURPLE, false);
        square2 = new Square(Square.Color.RED, false);
        square3 = new Square(Square.Color.PURPLE, false);
        square4 = new Square(Square.Color.PURPLE, false);


    }


    @Test
    void shootSamePosition() {
        player1.setPosition(square1);
        player2.setPosition(square1);
        weapon.addPlayerTarget(player2);
        weapon.setDamagingPlayer(player1);
        int nDamage = player2.getDamage().size();
        weapon.shoot();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));
    }


    @Test
    void shootNotSamePosition() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        int nDamage = player2.getDamage().size();

        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);

        assertThrows(IllegalArgumentException.class, () -> {
            weapon.shoot();
        });
        assertFalse(player2.getDamage().contains(player1));
        assertEquals(player2.getDamage().size(), nDamage);
    }

    @Test
    void mark() {
        player1.setPosition(square1);
        player2.setPosition(square1);

        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        int nMarks = player2.getMarks().size();
        weapon.mark();
        assertEquals(player2.getMarks().size(), nMarks + 1);
        assertTrue(player2.getMarks().contains(player1));

    }

    @Test
    void move() {
        player1.setPosition(square1);
        weapon.setDamagingPlayer(player1);
        weapon.addPosition(square2);
        square1.addCanAccessSquare(square2);
        weapon.move();
        assertEquals(player1.getPosition(), square2);
    }

    @Test
    void shootOneAwayView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.addPlayerTarget(player2);
        weapon.setDamagingPlayer(player1);
        square1.addCanAccessSquare(square2);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getDamage().size();
        weapon.shootOneAwayView();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));
    }

    @Test
    void shootTwoAwayView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.addPlayerTarget(player2);
        weapon.setDamagingPlayer(player1);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getDamage().size();
        weapon.shootTwoAwayView();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));

    }

    @Test
    void markOneAwayView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.addPlayerTarget(player2);
        weapon.setDamagingPlayer(player1);
        square1.addCanAccessSquare(square2);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getMarks().size();
        weapon.markOneAwayView();
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertTrue(player2.getMarks().contains(player1));

    }

    @Test
    void markTwoAwayView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.addPlayerTarget(player2);
        weapon.setDamagingPlayer(player1);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getMarks().size();
        weapon.markTwoAwayView();
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertTrue(player2.getMarks().contains(player1));

    }

    @Test
    void shootEvery() {
        player1.setPosition(square1);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.setDamagingPlayer(player1);
        player2.setPosition(square1);
        player3.setPosition(square1);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootEvery();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }


    @Test
    void markEvery() {
        player1.setPosition(square1);
        Player player3 = new Player();
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.setDamagingPlayer(player1);
        weapon.setDamagingPlayer(player1);
        player2.setPosition(square1);
        player3.setPosition(square1);
        int nDamage = player2.getMarks().size();
        int nDamage3 = player3.getMarks().size();
        weapon.markEvery();
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertEquals(player3.getMarks().size(), nDamage3 + 1);
        assertTrue(player2.getMarks().contains(player1));
        assertTrue(player3.getMarks().contains(player1));
    }

    @Test
    void shootRoomCanSee() {
        player1.setPosition(square1);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.setDamagingPlayer(player1);
        weapon.addPosition(square2);
        player2.setPosition(square2);
        player3.setPosition(square3);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootRoomCanSee();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 );
        assertTrue(player2.getDamage().contains(player1));
        assertFalse(player3.getDamage().contains(player1));
    }

    @Test
    void shootEveryOneAwayView() {
        player1.setPosition(square1);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.setDamagingPlayer(player1);
        weapon.setDamagingPlayer(player1);
        player2.setPosition(square2);
        player3.setPosition(square2);
        square1.addCanAccessSquare(square2);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootEveryOneAwayView();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }

    @Test
    void markEveryOneAwayView() {
        player1.setPosition(square1);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.setDamagingPlayer(player1);
        weapon.setDamagingPlayer(player1);
        player2.setPosition(square2);
        player3.setPosition(square2);
        square1.addCanAccessSquare(square2);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getMarks().size();
        int nDamage3 = player3.getMarks().size();
        weapon.markEveryOneAwayView();
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertEquals(player3.getMarks().size(), nDamage3 + 1);
        assertTrue(player2.getMarks().contains(player1));
        assertTrue(player3.getMarks().contains(player1));
    }

    @Test
    void shootView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getDamage().size();
        weapon.shootView();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertTrue(player2.getDamage().contains(player1));
    }

    @Test
    void markView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        square1.addCanViewSquare(square2);
        int nDamage = player2.getMarks().size();
        weapon.markView();
        assertEquals(player2.getMarks().size(), nDamage + 1);
        assertTrue(player2.getMarks().contains(player1));
    }

    @Test
    void moveTarget() {
        player1.setPosition(square1);
        weapon.addPlayerTarget(player1);
        square1.addCanAccessSquare(square2);
        weapon.addPosition(square2);
        weapon.moveTarget();
        assertEquals(player1.getPosition(), square2);
    }

    @Test
    void shootDirection() {
        player1.setPosition(square1);
        weapon.setDamagingPlayer(player1);
        weapon.addPosition(square1);
        weapon.addPosition(square2);
        weapon.addPosition(square3);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        player2.setPosition(square2);
        player3.setPosition(square3);
        square1.setNumber(0);
        square2.setNumber(4);
        square3.setNumber(8);
        weapon.setDirection(Square.Direction.SOUTH);
        int nDamage = player2.getDamage().size();
        int nDamage3= player3.getDamage().size();
        weapon.shootDirection();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3+1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }

    @Test
    void shootTargetView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        Player player3 = new Player();
        player3.setPosition(square3);
        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.addPosition(square2);
        weapon.addPosition(square3);
        square1.addCanViewSquare(square2);
        square2.addCanViewSquare(square3);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        weapon.shootTargetView();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
    }

    @Test
    void ShootSecondTargetView() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        Player player4 = new Player();
        player3.setPosition(square3);
        player4.setPosition(square4);
        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        weapon.addPlayerTarget(player3);
        weapon.addPlayerTarget(player4);
        weapon.addPosition(square2);
        weapon.addPosition(square3);
        weapon.addPosition(square4);
        square1.addCanViewSquare(square2);
        square2.addCanViewSquare(square3);
        square3.addCanViewSquare(square4);
        int nDamage = player2.getDamage().size();
        int nDamage3 = player3.getDamage().size();
        int nDamage4 = player4.getDamage().size();
        weapon.shootSecondTargetView();
        assertEquals(player2.getDamage().size(), nDamage + 1);
        assertEquals(player3.getDamage().size(), nDamage3 + 1);
        assertEquals(player4.getDamage().size(), nDamage4 + 1);
        assertTrue(player2.getDamage().contains(player1));
        assertTrue(player3.getDamage().contains(player1));
        assertTrue(player4.getDamage().contains(player1));
    }

    @Test
    void AttractTarget() {
        player1.setPosition(square1);
        player2.setPosition(square2);

        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        // TODO this is wrong, the manual doesn't mention direction
        weapon.setDirection(Square.Direction.EAST);

        square1.setNumber(7);
        square2.setNumber(4);


        weapon.attractTarget();

        assertEquals(player2.getPosition(), square1);
    }

    @Test
    void ShootCantSee() {
        player1.setPosition(square1);
        player2.setPosition(square2);
        weapon.setDamagingPlayer(player1);
        weapon.addPlayerTarget(player2);
        int nDamage = player2.getDamage().size();
        weapon.ShootCantSee();
        assertEquals(player2.getDamage().size(), nDamage+1);
        assertTrue(player2.getDamage().contains(player1));
    }
    @Test
    void getType(){
        Action action= new Action();
        Action.Type type=action.getType();
        assertFalse(type!=null);
    }

}

