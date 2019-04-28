package models;

import models.cards.Weapon;
import models.decks.WeaponsDeck;

import java.util.ArrayList;

public class WeaponsSlot {
    private Square.Color color;
    private ArrayList<Weapon> weapons;
    private Player player;

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
     * Receives user input on weapon of choice.
     *
     * @return the weapon chosen
     */
    //TODO CHECK THIS AGAIN
    public void weaponChoice(Weapon weaponChosen) {
        if (weapons.contains(weaponChosen)) {
            player.getWeapons().add(weaponChosen);
        } else {
            throw new IllegalArgumentException("That weapon is not here");
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