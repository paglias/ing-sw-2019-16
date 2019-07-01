package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SkullsTest {
    @Test
    void setNRemaining () {
        Skulls skulls = new Skulls();
        assertEquals(skulls.setNRemaining(5), 5);
        assertEquals(skulls.getNRemaining(), 5);
        skulls.setNRemaining(7);
        assertEquals(skulls.getNRemaining(), 7);

        assertThrows(IllegalArgumentException.class, () -> {
           skulls.setNRemaining(-1);
        });
    }

    @Test
    void getNRemaining () {
        Skulls skulls = new Skulls();
        skulls.setNRemaining(5);
        assertEquals(skulls.getNRemaining(), 5);
    }

    @Test
    void decreaseNRemaining () {
        Skulls skulls = new Skulls();
        skulls.setNRemaining(5);
        assertEquals(skulls.getNRemaining(), 5);

        skulls.decreaseSkullsRemaining();
        assertEquals(skulls.getNRemaining(), 4);

        skulls.setNRemaining(0);
        assertThrows(IllegalArgumentException.class, () -> {
            skulls.decreaseSkullsRemaining();
        });
    }
    @Test
    void addKiller(){
        GameBoard gameBoard = new GameBoard();
        Skulls skulls = new Skulls();
        Player player = new Player();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        skulls.setNRemaining(3);
        skulls.addKiller(player);
        skulls.addKiller(player1);
        assertEquals(skulls.getKillers().get(0), player);
        assertEquals(skulls.getKillers().get(1), player1);
        skulls.addKiller(player2);
        assertEquals(skulls.getKillers().get(2), player2);
        skulls.setNRemaining(0);
        assertThrows(IllegalArgumentException.class, () -> {
            skulls.addKiller(player3);
        });
    }
}
