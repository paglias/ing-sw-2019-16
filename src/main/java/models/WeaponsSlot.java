package models;

import models.cards.Weapon;
import models.decks.WeaponsDeck;

import java.util.ArrayList;

public class WeaponsSlot {
    private Square.Color color;
    private ArrayList<Weapon> weapons;

    /**
     * Instantiates a new Weapons slot for each spawn point
     *
     * @param color       the color
     * @param weaponsDeck the weapons deck
     */
    public WeaponsSlot(Square.Color color, WeaponsDeck weaponsDeck) {
        this.color = color;
        this.weapons = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            weapons.add((Weapon) weaponsDeck.pick());
        }
    }

    /**
     * Pick a weapon from the slot, remove it and pass it to the player.
     *
     * @return the weapon chosen
     */
    public Weapon weaponChoice (Weapon weaponChosen) {
        if (weapons.contains(weaponChosen)) {
            weapons.remove(weaponChosen);
            return weaponChosen;
        } else {
            throw new IllegalArgumentException("That weapon is not here");
        }
    }

    /**
     * Refill the weapon slot after a weapon has been picked.
     */
    public void refill (WeaponsDeck weaponsDeck) {
        int missing = 3 - weapons.size();
        for (int i = 0; i < missing; i++) {
            weapons.add((Weapon) weaponsDeck.pick());
        }
    }

    /**
     * Get the weapons inside the slot.
     *
     * @return the weapons
     */
    public ArrayList<Weapon> getWeapons () {
        return weapons;
    }

}