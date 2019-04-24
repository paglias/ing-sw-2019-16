package models;

import java.util.ArrayList;

public class Skulls {
    private int nRemaining;  //number of skulls, kills, remaining, before endgame starts
    private ArrayList<Player> killers;

    /**
     * Gets remaining.
     *
     * @return the remaining
     */
    public int getnRemaining() {
        return nRemaining;
    }
    public int decreaseSkullsRemaining(){
        return nRemaining--;
    }

    /**
     * Sets remaining.
     *
     * @param nRemaining the n remaining
     */
    public void setnRemaining(int nRemaining) {
        this.nRemaining = nRemaining;
    }

    //arraylist of players on the gameboard who killed someone. Used for final scoring
    public Skulls () {
        killers = new ArrayList<>();
    }
}
