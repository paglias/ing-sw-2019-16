package models.decks;

import models.Player;
import models.cards.Card;
import models.cards.PowerUp;

import java.util.ArrayList;
import java.util.Collections;

public class PowerUpsDeck extends Deck {
    @Override
    void generateCards() {
        for (Card.Color color : Card.Color.values()) {
            ArrayList<PowerUp> powerUps1 = PowerUp.loadPowerUps();
            for (PowerUp powerUp : powerUps1) {
                powerUp.setColor(color);
                availableCards.add(powerUp);
            }

            ArrayList<PowerUp> powerUps2 = PowerUp.loadPowerUps();
            for (PowerUp powerUp : powerUps2) {
                powerUp.setColor(color);
                availableCards.add(powerUp);
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
        player.removePowerUp(powerUp);
    }


}