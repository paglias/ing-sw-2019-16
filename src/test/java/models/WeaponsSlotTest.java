package models;

import models.cards.Weapon;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void weaponChoiceExisting () {
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        WeaponsSlot slot = new WeaponsSlot(Square.Color.BLUE, weaponsDeck);

        Weapon toPick = slot.getWeapons().get(0);

        Weapon picked = slot.weaponChoice(toPick);

        assertEquals(slot.getWeapons().size(), 2);
        assertEquals(picked, toPick);
    }

    @Test
    void weaponChoiceNotExisting () {
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        WeaponsSlot slot = new WeaponsSlot(Square.Color.BLUE, weaponsDeck);

        Weapon toPick = new Weapon();

        assertThrows(IllegalArgumentException.class, () -> {
            slot.weaponChoice(toPick);
        });
    }

    @Test
    void refill () {
        WeaponsDeck weaponsDeck = new WeaponsDeck();
        WeaponsSlot slot = new WeaponsSlot(Square.Color.BLUE, weaponsDeck);

        Weapon toPick = slot.getWeapons().get(0);
        slot.weaponChoice(toPick);

        assertEquals(slot.getWeapons().size(), 2);
        slot.refill(weaponsDeck);
        assertEquals(slot.getWeapons().size(), 3);
    }
}
