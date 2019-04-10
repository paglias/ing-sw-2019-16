package models.decks;

import models.cards.Card;
import models.cards.PowerUp;

import java.util.Collections;

public class PowerUpsDeck extends Deck {
    @Override
    void generateCards () {
        for (Card.Color color : Card.Color.values()) {
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
    public PowerUpsDeck () {
        super(true, 24);
    }
}