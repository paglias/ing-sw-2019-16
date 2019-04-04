package models;

public class PowerUpDeck extends Deck {
    @Override
    void generateCards () {
        for (Color color : Color.values()) {
            for (PowerUp.Name name : PowerUp.Name.values()) {
                availableCards.add(new PowerUp(name, color));
                availableCards.add(new PowerUp(name, color));
            }
        }
    }

    public PowerUpDeck () {
        super(true, 24);
    }
}