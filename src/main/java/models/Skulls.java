package models;

import java.util.ArrayList;

public class Skulls {
    // number of skulls, kills, remaining, before endgame starts
    private int nRemaining;
    // List of players on the gameboard who killed someone. Used for final scoring
    private ArrayList<Player> killers;


    /**
     * Gets killers.
     *
     * @return the killers
     */
    public ArrayList<Player> getKillers() {
        return killers;
    }

    /**
     * Sets killers.
     *
     * @param killers the killers
     */
    public void setKillers(ArrayList<Player> killers) {
        this.killers = killers;
    }

    /**
     * Gets remaining.
     *
     * @return the remaining skulls
     */
    public int getNRemaining() {
        return nRemaining;
    }

    /**
     * Decrease skulls remaining int.
     *
     * @return the int
     */
    public int decreaseSkullsRemaining() {
        if (nRemaining == 0) throw new IllegalArgumentException("Remaining skulls can't be negative.");
        return nRemaining--;
    }

    /**
     * Sets remaining.
     *
     * @param nRemaining the n remaining
     */
    public int setNRemaining(int nRemaining) {
        if (nRemaining < 0) throw new IllegalArgumentException("Remaining skulls can't be negative.");
        this.nRemaining = nRemaining;
        return this.nRemaining;
    }

    public Skulls () {
        killers = new ArrayList<>();
    }
}
