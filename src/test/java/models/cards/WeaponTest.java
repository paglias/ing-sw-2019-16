package models.cards;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeaponTest {
    @Test
    public void testloadMap () throws IOException { // TODO how to handle methods with exceptions?
        // Test that loading doesn't fail
        ArrayList<Weapon> weapons = Weapon.loadWeapons();
        assertEquals(weapons.size(), 21);
    }

}
