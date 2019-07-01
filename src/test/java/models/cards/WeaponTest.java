package models.cards;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
