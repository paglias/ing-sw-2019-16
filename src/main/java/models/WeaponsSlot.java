package models;

import models.cards.Weapon;
import models.decks.WeaponsDeck;

import java.util.ArrayList;

public class WeaponsSlot {
    private Square.Color color;
    private ArrayList<Weapon> weapons;

    /**
     * Gets weapons.
     *
     * @return the weapons
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Instantiates a new Weapons slot for each spawn point
     *
     * @param color       the color
     * @param weaponsDeck the weapons deck
     */
    public WeaponsSlot (Square.Color color, WeaponsDeck weaponsDeck) {
        this.color = color;
        this.weapons = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            weapons.add((Weapon) weaponsDeck.pick());
        }
    }

    /**
     * Receives user input on weapon of choice.
     *
     * @return the weapon chosen
     */
    public Weapon weaponChoice()//TODO Define USER CHOICE
    {
        return weaponChoice();
    }
}
