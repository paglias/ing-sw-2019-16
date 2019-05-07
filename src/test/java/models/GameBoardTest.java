package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    Player player;
    GameBoard gameBoard;

    @BeforeEach
    void setup() {
        player = new Player();
        gameBoard = new GameBoard();
    }

    @Test
    void getActive(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        player1.setActive(true);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);
        assertEquals(gameBoard.getActivePlayer(), player1);
    }

    @Test
    void players(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        gameBoard.addPlayer(player);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);
        assertTrue(gameBoard.getPlayers().contains(player1));
        assertTrue(gameBoard.getPlayers().contains(player2));
        assertTrue(gameBoard.getPlayers().contains(player3));
        assertTrue(player.isFirstPlayer());
        assertFalse(player3.isFirstPlayer());
    }

    @Test
    void assignPoints(){
        ArrayList<Integer> points = new ArrayList();
        points.add(1);
        points.add(2);
        points.add(3);
        points.add(4);
        points.add(8);
        points.add(10);
    }
    @Test
    void calcateGamePoints(){

    }
}
