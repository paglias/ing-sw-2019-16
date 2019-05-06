package models.decks;

import models.Player;
import models.cards.Card;
import models.cards.PowerUp;

import java.util.Collections;

public class PowerUpsDeck extends Deck {
    @Override
    void generateCards() {
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
    public PowerUpsDeck() {
        super(true, 24);
    }

    /**
     * Sell.
     *
     * @param player  the player
     * @param powerUp the power up
     */
    public void sell (Player player, PowerUp powerUp) {
        player.addCube(powerUp.getColor());
        discard(powerUp);

    }


}