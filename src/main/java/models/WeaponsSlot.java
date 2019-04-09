package models;

import java.util.ArrayList;

public class WeaponsSlot {
    private Color color;
    private ArrayList<Weapon> weapons;

    public WeaponsSlot (Color color, WeaponsDeck weaponsDeck) {
        this.color = color;
        this.weapons = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            weapons.add((Weapon) weaponsDeck.pick());
        }
    }
}
