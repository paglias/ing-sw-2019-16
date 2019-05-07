package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);
        assertTrue(gameBoard.getPlayers().contains(player1));
        assertTrue(gameBoard.getPlayers().contains(player2));
        assertTrue(gameBoard.getPlayers().contains(player3));
        assertTrue(player1.isFirstPlayer());
    }
}
