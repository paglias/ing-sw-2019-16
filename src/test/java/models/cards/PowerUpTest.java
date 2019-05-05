package models.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Player;
import models.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {
    @Test
    void getName() {
        PowerUp powerUp = new PowerUp(PowerUp.Name.NEWTON, Card.Color.BLUE);
        assertEquals(powerUp.getName(), PowerUp.Name.NEWTON);
    }


    @Test
    void TeleporterEffect() {
        PowerUp powerUp = new PowerUp(PowerUp.Name.TELEPORTER, Card.Color.BLUE);
        Player player = new Player();
        player.setPosition(player.getPosition());
        Square position = new Square(Square.Color.PURPLE, false);
        powerUp.effect(player, position);
        assertEquals(player.getPosition(), position);
    }

    @Test
    void TagbackEffect(){
        PowerUp powerUp= new PowerUp(PowerUp.Name.TAGBACK_GRENADE, Card.Color.BLUE);
        Player player= new Player();
        Player player2= new Player();
        Square square1= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, true);
        int nMarks= player2.getMarks().size();
        player.setPosition(square1);
        player2.setPosition(square2);
        square1.addCanViewSquare(square2);
        powerUp.effect(player, player2);
        assertEquals(player2.getMarks().size(),nMarks+1 );
        assertTrue(player2.getMarks().contains(player));
    }

    @Test
    void TargetingEffect(){
        PowerUp powerUp= new PowerUp(PowerUp.Name.TARGETING_SCOPE, Card.Color.BLUE);
        Player player=new Player();
        player.addCube(Card.Color.BLUE);
        player.addCube(Card.Color.RED);
        player.addCube(Card.Color.YELLOW);
        Player player2=new Player();
        int nCubes= player.getCubes().size();
        int nDamages= player2.getDamage().size();
        powerUp.effect(player, Card.Color.BLUE, player2);
        assertEquals(player2.getDamage().size(), nDamages+1);
        assertTrue(player2.getDamage().contains(player));
        assertEquals(player.getCubes().size(), nCubes-1);
    }

    @Test
    void NewtonEffect(){
        PowerUp powerUp= new PowerUp(PowerUp.Name.NEWTON, Card.Color.BLUE);
        Player player1=new Player();
        Player player2=new Player();
        Square square1= new Square(Square.Color.BLUE, false);
        Square square2=new Square(Square.Color.BLUE, false);
        player1.setPosition(square1);
        List<Square> squares= Arrays.asList(square1,square2);
        player2.setPosition(square1);
        square1.setNumber(0);
        square2.setNumber(4);
        powerUp.effect(player1, player2, square1,square2, Square.Direction.SOUTH, squares);
        assertEquals(player2.getPosition(), square2);
    }



}