package models.decks;

import models.cards.Card;
import models.cards.Weapon;

import java.util.Collections;

public class WeaponsDeck extends Deck {
    @Override
    void generateCards () {
        availableCards.addAll(Weapon.loadWeapons());
        Collections.shuffle(availableCards);
    }

    /**
     * Instantiates a new Weapons deck.
     */
    public WeaponsDeck () {
        super(false, 21);
    }
}