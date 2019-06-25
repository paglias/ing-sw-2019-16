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

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public ArrayList<Card.Color> getCost () {
        return cost;
    }

    /**
     * Gets actions.
     *
     * @return the actions
     */
    public ArrayList<Action> getActions () {
        return actions;
    }

    /**
     * Gets input.
     *
     * @return the input
     */
    public ArrayList<Input> getInput () {
        return input;
    }
}
