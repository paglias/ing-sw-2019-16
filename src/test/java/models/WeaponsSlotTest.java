package models;

import models.cards.Weapon;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeaponsSlotTest {
    @Test
    void constructor () {
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        WeaponsSlot slot = new WeaponsSlot(Square.Color.BLUE, weaponsDeck);

        assertEquals(weaponsDeck.getRemainingCards(), weaponsDeck.getDeckSize() - 3);
    }

    @Test
    void getWeapons () {
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        WeaponsSlot slot = new WeaponsSlot(Square.Color.BLUE, weaponsDeck);

        assertEquals(slot.getWeapons().size(), 3);
    }
}
