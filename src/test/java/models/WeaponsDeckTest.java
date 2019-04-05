package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponsDeckTest {

    @Test
    public void testWeaponsDeckConstructor(){
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        assertEquals(weaponsDeck.getDeckSize(), 21);
        assertEquals(weaponsDeck.getRemainingCards(), 21);
    }

    @Test
    public void testPickColor(){
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        int nBlue = 0;
        int nRed =0;
        int nYellow=0;

        for (int i=0; i<21; i++){
            Weapon card = (Weapon) weaponsDeck.pick();
            if (card.getColor() == Color.BLUE) {
                nBlue++;
            }

            if (card.getColor() == Color.YELLOW) {
                nYellow++;
            }

            if (card.getColor() == Color.RED) {
                nRed++;
            }
        }

        assertEquals(nBlue, 7);
        assertEquals(nYellow, 7);
        assertEquals(nRed, 7);

    }

    @Test
    public void TestPickRefill() {
        WeaponsDeck weaponsDeck = new WeaponsDeck();

        for (int i=0; i <20; i++) {
            weaponsDeck.pick();
            assertEquals(weaponsDeck.getRemainingCards(),  weaponsDeck.getDeckSize() - i - 1);
        }

        weaponsDeck.pick();
        assertEquals(weaponsDeck.getRemainingCards(), 0);
    }
}

