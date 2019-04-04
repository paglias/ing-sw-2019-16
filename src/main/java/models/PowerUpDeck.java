package models;

import java.util.Collection;
import java.util.Collections;

public class PowerUpDeck extends Deck {
    @Override
    void generateCards () {
        for (Color color : Color.values()) {
            for (PowerUp.Name name : PowerUp.Name.values()) {

                availableCards.add(new PowerUp(name, color));
                availableCards.add(new PowerUp(name, color));

            }
        }
        Collections.shuffle(availableCards);
    }

    public PowerUpDeck () {
        super(true, 24);
    }
}