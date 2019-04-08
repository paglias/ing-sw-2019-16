package models;

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

    /**
     * Instantiates a new Power up deck.
     */
    public PowerUpDeck () {
        super(true, 24);
    }
}