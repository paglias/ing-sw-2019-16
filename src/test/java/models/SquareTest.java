package models;

import models.decks.AmmoDeck;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    @Test
    void createWeaponsSlot () {
        Square noSpawnPoint = new Square(Square.Color.PURPLE.toString(), false);
        assertThrows(IllegalStateException.class, () -> {
            noSpawnPoint.createWeaponsSlot(new WeaponsDeck());
        });


        Square spawnPoint = new Square(Square.Color.PURPLE.toString(), true);
        spawnPoint.createWeaponsSlot(new WeaponsDeck());
        // Cannot create it twice
        assertThrows(IllegalStateException.class, () -> {
            spawnPoint.createWeaponsSlot(new WeaponsDeck());
        });
    }

    @Test
    void canViewGetAndSet () {
        Square square = new Square(Square.Color.PURPLE.toString(), false);
        square.addCanViewSquare(new Square(Square.Color.YELLOW.toString(), false));
        square.addCanViewSquare(new Square(Square.Color.BLUE.toString(), false));

        List<Square> canViewList = square.getCanView();
        assertEquals(canViewList.size(), 2);
        assertEquals(canViewList.get(0).getColor(), Square.Color.YELLOW);
        assertEquals(canViewList.get(1).getColor(), Square.Color.BLUE);
    }

    @Test
    void isSpawnPoint () {
        Square square = new Square(Square.Color.PURPLE.toString(), false);
        assertEquals(square.isSpawnPoint(), false);
    }

    @Test
    void getWeaponsSlot () {
        Square square = new Square(Square.Color.PURPLE.toString(), false);
        assertThrows(IllegalStateException.class, square::getWeaponsSlot);

        Square spawnPoint = new Square(Square.Color.PURPLE.toString(), true);
        assertNull(spawnPoint.getWeaponsSlot());
        spawnPoint.createWeaponsSlot(new WeaponsDeck());
        assertNotNull(spawnPoint.getWeaponsSlot());
    }

    @Test
    void canAccessDirectlyGetAndSet () {
        Square square = new Square(Square.Color.PURPLE.toString(), false);
        square.addCanAccessSquare(new Square(Square.Color.YELLOW.toString(), false));
        square.addCanAccessSquare(new Square(Square.Color.BLUE.toString(), false));

        List<Square> canAccessList = square.getCanAccessDirectly();
        assertEquals(canAccessList.size(), 2);
        assertEquals(canAccessList.get(0).getColor(), Square.Color.YELLOW);
        assertEquals(canAccessList.get(1).getColor(), Square.Color.BLUE);
    }

    @Test
    void ammoAndSet () {
        Square square = new Square(Square.Color.PURPLE.toString(), false);
        assertNull(square.getAmmo());

        AmmoDeck ammoDeck = new AmmoDeck();
        square.setAmmo(ammoDeck);

        assertNotNull(square.getAmmo());
        assertNull(square.getAmmo());
    }

    @Test
    void getColor () {
        Square square = new Square(Square.Color.PURPLE.toString(), false);
        assertEquals(square.getColor(), Square.Color.PURPLE);
    }

}
