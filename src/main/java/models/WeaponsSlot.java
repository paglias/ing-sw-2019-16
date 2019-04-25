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
    public WeaponsSlot (Square.Color color, WeaponsDeck weaponsDeck) {
        this.color = color;
        this.weapons = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            weapons.add((Weapon) weaponsDeck.pick());
        }
    }

    /**
     * Receives user input on weapon of choice.
     *
     * @return the weapon chosen
     */
    public Weapon weaponChoice () {
        // TODO what is this?
        // TODO define USER CHOICE
        return weaponChoice();
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
