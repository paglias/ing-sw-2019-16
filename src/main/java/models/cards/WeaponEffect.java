package models.cards;

import java.util.ArrayList;

public class WeaponEffect {
    public enum Input {
        DIRECTION,
        TARGET,
    }

    private ArrayList<Input> input;
    private ArrayList<Card.Color> cost;
    private ArrayList<WeaponAction> actions;
}
