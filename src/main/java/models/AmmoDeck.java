package models;

import java.util.Collections;

public class AmmoDeck extends Deck {
    @Override
    void generateCards () {
        for (int i = 0; i < 3; i++) {
            availableCards.add(new Ammo(1, 2, 0, false));
            availableCards.add(new Ammo(1, 0, 2, false));
            availableCards.add(new Ammo(2, 1, 0, false));
            availableCards.add(new Ammo(0, 1, 2, false));
            availableCards.add(new Ammo(0, 2, 1, false));
            availableCards.add(new Ammo(0, 0, 1, false));
        }
        for (int i = 0; i < 4; i++) {
            availableCards.add(new Ammo(0, 1, 1, true));
            availableCards.add(new Ammo(1, 1, 0, true));
            availableCards.add(new Ammo(1, 0, 1, true));
        }
        for (int i = 0; i < 2; i++){
            availableCards.add(new Ammo(0, 0, 2, true));
            availableCards.add(new Ammo(0, 2, 0, true));
            availableCards.add(new Ammo(2, 0, 0, true));
    }
                Collections.shuffle(availableCards);
            }
    public AmmoDeck () {
        super(true, 36);
    }
}
