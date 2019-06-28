package models;

import models.decks.AmmoDeck;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    @Test
    void createWeaponsSlot () {
        Square noSpawnPoint = new Square(Square.Color.PURPLE, false);
        assertThrows(IllegalStateException.class, () -> {
            noSpawnPoint.createWeaponsSlot(new WeaponsDeck());
        });


        Square spawnPoint = new Square(Square.Color.PURPLE, true);
        spawnPoint.createWeaponsSlot(new WeaponsDeck());
        // Cannot create it twice
        assertThrows(IllegalStateException.class, () -> {
            spawnPoint.createWeaponsSlot(new WeaponsDeck());
        });
    }

    @Test
    void canViewGetAndSet () {
        Square square = new Square(Square.Color.PURPLE, false);
        square.addCanViewSquare(new Square(Square.Color.YELLOW, false));
        square.addCanViewSquare(new Square(Square.Color.BLUE, false));

        List<Square> canViewList = square.getCanView();
        assertEquals(canViewList.size(), 2);
        assertEquals(canViewList.get(0).getColor(), Square.Color.YELLOW);
        assertEquals(canViewList.get(1).getColor(), Square.Color.BLUE);
    }

    @Test
    void isSpawnPoint () {
        Square square = new Square(Square.Color.PURPLE, false);
        assertEquals(square.isSpawnPoint(), false);
    }
    @Test
    void position () {
        Square square = new Square(Square.Color.PURPLE, false);
        square.setNumber(4);
        assertEquals(square.getNumber(), 4);
    }
    @Test
    void samePositionVertical(){
        Square square= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, false);
        square.setNumber(0);
        square2.setNumber(4);
        assertTrue(square.sameDirection(square2));
    }
    @Test
    void samePositionHorizontal1(){
        Square square= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, false);
        square.setNumber(0);
        square2.setNumber(3);
        assertTrue(square.sameDirection(square2));
    }
    @Test
    void samePositionHorizontal2(){
        Square square= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, false);
        square.setNumber(4);
        square2.setNumber(11);
        assertFalse(square.sameDirection(square2));
    }
    @Test
    void samePositionHorizontal3(){
        Square square= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, false);
        square.setNumber(4);
        square2.setNumber(7);
        assertTrue(square.sameDirection(square2));
    }
    @Test
    void samePositionHorizontalF(){
        Square square= new Square(Square.Color.PURPLE, false);
        Square square2= new Square(Square.Color.PURPLE, false);
        square.setNumber(8);
        square2.setNumber(11);
        assertTrue(square.sameDirection(square2));
    }

    @Test
    void getWeaponsSlot () {
        Square square = new Square(Square.Color.PURPLE, false);
        assertThrows(IllegalStateException.class, square::getWeaponsSlot);

        Square spawnPoint = new Square(Square.Color.PURPLE, true);
        assertNull(spawnPoint.getWeaponsSlot());
        spawnPoint.createWeaponsSlot(new WeaponsDeck());
        assertNotNull(spawnPoint.getWeaponsSlot());
    }

    @Test
    void canAccessDirectlyGetAndSet () {
        Square square = new Square(Square.Color.PURPLE, false);
        square.addCanAccessSquare(new Square(Square.Color.YELLOW, false));
        square.addCanAccessSquare(new Square(Square.Color.BLUE, false));

        List<Square> canAccessList = square.getCanAccessDirectly();
        assertEquals(canAccessList.size(), 2);
        assertEquals(canAccessList.get(0).getColor(), Square.Color.YELLOW);
        assertEquals(canAccessList.get(1).getColor(), Square.Color.BLUE);
    }

    @Test
    void ammoAndSet () {
        Square square = new Square(Square.Color.PURPLE, false);
        assertNull(square.getAmmo());

        AmmoDeck ammoDeck = new AmmoDeck();
        square.setAmmo(ammoDeck);

        assertNotNull(square.getAmmo());
        assertNull(square.getAmmo());
    }

    @Test
    void getColor () {
        Square square = new Square(Square.Color.PURPLE, false);
        assertEquals(square.getColor(), Square.Color.PURPLE);
    }

    @Test
    void sameDirectionSouth(){
        Square square1=new Square(Square.Color.PURPLE,false);
        Square square2=new Square(Square.Color.PURPLE,true);
        square1.setNumber(0);
        square2.setNumber(4);
        assertTrue(square1.sameDirection(square2, Square.Direction.SOUTH));

    }

    @Test
    void sameDirectionEast(){
        Square square1=new Square(Square.Color.PURPLE,false);
        Square square2=new Square(Square.Color.PURPLE,true);
        square1.setNumber(0);
        square2.setNumber(3);
        assertTrue(square1.sameDirection(square2, Square.Direction.EAST));

    }
    @Test
    void sameDirectionWest(){
        Square square1=new Square(Square.Color.PURPLE,false);
        Square square2=new Square(Square.Color.PURPLE,true);
        square1.setNumber(3);
        square2.setNumber(0);
        assertTrue(square2.sameDirection(square1, Square.Direction.WEST));

    }
    @Test
    void sameDirectionNorth(){
        Square square1=new Square(Square.Color.PURPLE,false);
        Square square2=new Square(Square.Color.PURPLE,true);
        square1.setNumber(0);
        square2.setNumber(4);
        assertTrue(square2.sameDirection(square1, Square.Direction.NORTH));

    }

}
