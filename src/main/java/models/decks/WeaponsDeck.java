package models.decks;

import models.cards.Card;
import models.cards.Weapon;

import java.util.Collections;

public class WeaponsDeck extends Deck {
    @Override
    void generateCards () {
        for (Card.Color color : Card.Color.values()) {
            for (int i=0; i<7; i++) {
                availableCards.add(new Weapon(color));
            }
        }
        Collections.shuffle(availableCards);
    }

    /**
     * Instantiates a new Weapons deck.
     */
    public WeaponsDeck () {
        super(false, 21);
    }
}