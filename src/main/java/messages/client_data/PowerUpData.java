package messages.client_data;

import models.cards.PowerUp;

public class PowerUpData {
    public String color;
    public String name;

    public EffectData primaryEffect;

    /**
     * Instantiates a new Power up data.
     * Gets powerups color and name.
     *
     * @param powerUp the power up
     */
    public PowerUpData (PowerUp powerUp) {
        color = powerUp.getColor().toString();
        name = powerUp.getName();

        primaryEffect = new EffectData(powerUp.getEffects(1).get(0));
    }
}
