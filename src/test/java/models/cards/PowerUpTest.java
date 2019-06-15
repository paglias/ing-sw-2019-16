package models.cards;

import java.util.ArrayList;
import java.util.List;

import models.GameBoard;
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
        GameBoard gameBoard = new GameBoard();
        Player player = new Player();
        powerUp.setPlayer(player);
        Square position=new Square(Square.Color.BLUE, false);
        powerUp.setNewPosition(position);
        powerUp.effect(PowerUp.Name.TELEPORTER);
        assertEquals(player.getPosition(), position);
    }

    @Test
    void TagbackEffect(){
        PowerUp powerUp= new PowerUp(PowerUp.Name.TAGBACK_GRENADE, Card.Color.BLUE);
        GameBoard gameBoard = new GameBoard();
        Player player = new Player();
        Player player2 = new Player();
        powerUp.setPlayer(player);
        powerUp.setPlayerTarget(player2);
        Square square1= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, true);
        int nMarks= player2.getMarks().size();
        player.setPosition(square1);
        player2.setPosition(square2);
        powerUp.setNewPosition(square1);
        powerUp.setNewPosition(square2);
        square1.addCanViewSquare(square2);
        powerUp.effect(PowerUp.Name.TAGBACK_GRENADE);
        assertEquals(player2.getMarks().size(),nMarks+1 );
        assertTrue(player2.getMarks().contains(player));
    }

    @Test
    void TargetingEffect(){
        GameBoard gameBoard = new GameBoard();

        PowerUp powerUp= new PowerUp(PowerUp.Name.TARGETING_SCOPE, Card.Color.BLUE);
        Player player=new Player();
        player.addCube(Card.Color.BLUE);
        player.addCube(Card.Color.RED);
        player.addCube(Card.Color.YELLOW);
        Player player2=new Player();
        Square position=new Square(Square.Color.RED,true);
        powerUp.setPlayer(player);
        powerUp.setPlayerTarget(player2);
        powerUp.setCubeColor(Card.Color.BLUE);
        powerUp.setNewPosition(position);
        int nCubes= player.getCubes().size();
        int nDamages= player2.getDamage().size();
        powerUp.effect(PowerUp.Name.TARGETING_SCOPE);
        assertEquals(player2.getDamage().size(), nDamages+1);
        assertTrue(player2.getDamage().contains(player));
        assertEquals(player.getCubes().size(), nCubes-1);
    }

    @Test
    void NewtonEffect(){
        PowerUp powerUp= new PowerUp(PowerUp.Name.NEWTON, Card.Color.BLUE);
        GameBoard gameBoard = new GameBoard();

        Player player1=new Player();
        Player player2=new Player();
        Square square1= new Square(Square.Color.BLUE, false);
        Square square2=new Square(Square.Color.BLUE, false);

        player2.setPosition(square2);
        square1.setNumber(0);
        square2.setNumber(4);

        List<Square> squares= new ArrayList<>();
        squares.add(square1);
        squares.add(square2);
        powerUp.setSquares(squares);

        powerUp.setPlayer(player1);
        powerUp.setPlayerTarget(player2);

        player1.setPosition(square1);
        powerUp.setNewPosition(square1);
        powerUp.setNewPosition(square2);
        powerUp.setDirection(Square.Direction.SOUTH);

        powerUp.effect(PowerUp.Name.NEWTON);
        assertEquals(player2.getPosition(), square2);
    }



}