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
     * Adds a new killer in the arraylist of players who killed someone.
     * The check is not on killers but on nRemaining.
     * @param killer the killer
     */
    public void addKiller(Player killer) {
        if (nRemaining != 0 ) {
            this.killers.add(killer);
        }
        else{
            throw new IllegalArgumentException("No more killers can be added, FinalFrenzy activated");
        }
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
     * @return number of skulls remaining.
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
