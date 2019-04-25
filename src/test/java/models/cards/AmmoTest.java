package models.cards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmmoTest {
    @Test
    void constructorAndGetters () {
        Ammo ammo = new Ammo(3, 5, 6, false);
        assertEquals(ammo.getBlueCubes(), 5);
        assertEquals(ammo.getRedCubes(), 6);
        assertEquals(ammo.getYellowCubes(), 3);
        assertEquals(ammo.getHasPowerUp(), false);
    }

    @Test
    void decreaseCubes () {
        Ammo ammo = new Ammo(3, 5, 6, false);

        ammo.decreaseBlueCubes();
        ammo.decreaseBlueCubes();
        assertEquals(ammo.getBlueCubes(), 3);

        ammo.decreaseRedCubes();
        ammo.decreaseRedCubes();
        assertEquals(ammo.getRedCubes(), 4);

        ammo.decreaseYellowCubes();
        ammo.decreaseYellowCubes();
        assertEquals(ammo.getYellowCubes(), 1);
    }
}
