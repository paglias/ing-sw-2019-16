package models.cards;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    @Test
    void loadWeapons () {
        // Test that loading doesn't fail
        ArrayList<Weapon> weapons = Weapon.loadWeapons();
        assertEquals(weapons.size(), 21);
    }

    @Test
    void isLoaded () {
        // When created Weapons are loaded
        Weapon weapon = new Weapon();
        assertTrue(weapon.isLoaded());
    }
}
