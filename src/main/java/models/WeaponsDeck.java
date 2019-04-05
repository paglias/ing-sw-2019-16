package models;

import java.util.Collections;

public class WeaponsDeck extends Deck {
    @Override
    void generateCards () {
        for (Color color : Color.values()) {
            for (int i=0; i<7; i++) {
                availableCards.add(new Weapon(color));
            }
        }
        Collections.shuffle(availableCards);
    }

    public WeaponsDeck () {
        super(false, 21);
    }
}