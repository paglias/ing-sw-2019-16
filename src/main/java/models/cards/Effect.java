package models.cards;

import java.util.ArrayList;

public class Effect {
    public enum Input {
        DIRECTION,
        POSITION,
        TARGET,
    }

    private ArrayList<Input> input;
    private ArrayList<Card.Color> cost;
    private ArrayList<Action> actions;

    public ArrayList<Card.Color> getCost () {
        return cost;
    }

    public ArrayList<Action> getActions () {
        return actions;
    }

    public ArrayList<Input> getInput () {
        return input;
    }
}
