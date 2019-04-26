package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
