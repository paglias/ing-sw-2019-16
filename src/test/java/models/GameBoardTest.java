package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void calculateGamePoints(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        player.setNickname("Alpha");
        player1.setNickname("Beta");
        player2.setNickname("Gamma");
        player3.setNickname("Delta");
        gameBoard.setMap(1);
        gameBoard.addPlayer(player);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);
        gameBoard.getSkulls().setNRemaining(6);
        gameBoard.getSkulls().addKiller(player);
        gameBoard.getSkulls().addKiller(player1);
        gameBoard.getSkulls().addKiller(player2);
        gameBoard.getSkulls().addKiller(player3);
        gameBoard.getSkulls().addKiller(player);
        gameBoard.getSkulls().addKiller(player);
        gameBoard.getSkulls().addKiller(player2);
        gameBoard.getSkulls().addKiller(player);
        gameBoard.getSkulls().addKiller(player2);
        gameBoard.getSkulls().addKiller(player1);
        gameBoard.calculateGamePoints();
        assertEquals(player.getTotalPoints(), 8);
        assertEquals(player2.getTotalPoints(),6);
        assertEquals(player3.getTotalPoints(),2 );
        assertEquals(player1.getTotalPoints(), 4);
    }
    @Test
    void assignPoints(){
        List<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(1);
        points.add(2);
        points.add(3);
        points.add(8);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        players.add(player);
        players.add(player1);
        players.add(player3);
        players.add(player2);
        gameBoard.assignPoints(points, players);
        assertEquals(player.getTotalPoints(), 1);
        assertEquals(player1.getTotalPoints(), 2);
        assertEquals(player2.getTotalPoints(), 8);
        assertEquals(player3.getTotalPoints(), 3);
    }

    @Test
    void finalFrenzy(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        gameBoard.addPlayer(player);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);

        player.addDamage(player1);
        player.addDamage(player3);
        player1.addDamage(player);

        player.increaseAdrenaline();
        player1.increaseAdrenaline();
        player2.increaseAdrenaline();
        player3.increaseAdrenaline();

        ArrayList<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(1);
        points.add(1);
        points.add(5);

        assertTrue(player.isFirstPlayer());

        gameBoard.finalFrenzy();

        assertEquals(player1.getActionCounter(),1);
        assertEquals(player3.getActionCounter(), 1);
        player.setFirstPlayer(false);
        assertFalse(player.isFirstPlayer());
        player2.setFirstPlayer(true);

        gameBoard.finalFrenzy();

        assertEquals(player.getActionCounter(), 2);
        assertEquals(player1.getActionCounter(), 2);
        assertEquals(player2.getActionCounter(), 1);
        assertEquals(player3.getActionCounter(),1);
        assertEquals(player2.getGivenPoints(), points);
        assertEquals(player3.getGivenPoints(), points);
        assertEquals(player.getGivenPoints().get(0), 1);
        assertEquals(player.getGivenPoints().get(3),4);
        assertEquals(player.getGivenPoints().get(5), 8);
        assertEquals(player1.getGivenPoints().get(5),8);
        assertEquals(player.getAdrenaline(), 1);
        assertEquals(player2.getAdrenaline(), 0);
    }

    @Test
    void nextPlayer(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        gameBoard.addPlayer(player);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);
        player.setActive(true);
        gameBoard.nextPlayer(player);
        assertTrue(player1.isActive());
        assertFalse(player.isActive());
    }

    @Test
    void addPlayer(){
        assertFalse(gameBoard.getPlayers().contains(player));
        gameBoard.addPlayer(player);
        assertTrue(gameBoard.getPlayers().contains(player));
    }

    @Test
    void getActivePlayer(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        gameBoard.addPlayer(player);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);

        player2.setActive(true);
        Player temp = gameBoard.getActivePlayer();
        assertEquals(temp, player2);
        assertNotEquals(temp, player1);
    }

    @Test
    void getPlayerByNickname(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        player.setNickname("Alpha");
        player1.setNickname("Beta");
        player2.setNickname("Gamma");
        player3.setNickname("Delta");
        gameBoard.addPlayer(player);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.addPlayer(player3);

        String x = "Alpha";
        String y = "Deltaa";

        assertEquals(gameBoard.getPlayerByNickname(x), player);
        assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.getPlayerByNickname(y);
        });
    }
}
