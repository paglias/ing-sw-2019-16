package models.cards;

import java.util.ArrayList;

public class WeaponEffect {
    public enum Input {
        DIRECTION,
        POSITION,
        TARGET,
    }

    private ArrayList<Input> input;
    private ArrayList<Card.Color> cost;
    private ArrayList<WeaponAction> actions;

    public ArrayList<Card.Color> getCost () {
        return cost;
    }

    public ArrayList<WeaponAction> getActions () {
        return actions;
    }

    public ArrayList<Input> getInput () {
        return input;
    }
}
