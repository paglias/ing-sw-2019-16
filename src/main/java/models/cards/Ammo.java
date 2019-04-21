package models.cards;

import java.util.ArrayList;

public class Ammo extends Card {
    protected ArrayList<Color> cubes;
    protected int nYellowCubes;
    protected int nBlueCubes;
    protected int nRedCubes;
    protected Boolean hasPowerUp;

    /**
     * Decrease yellow cubes.
     */
    public void decreasenYellowCubes() {
        this.nYellowCubes--;
    }

    /**
     * Decrease blue cubes.
     */
    public void decreasenBlueCubes() {
        this.nBlueCubes--;
    }

    /**
     * Decrease red cubes.
     */
    public void decreasenRedCubes() {
        this.nRedCubes--;
    }

    /**
     * Gets boolean has power up.
     *
     * @return the boolean has power up
     */
    public Boolean getHasPowerUp() {
        return hasPowerUp;
    }

    /**
     * Gets blue cubes.
     *
     * @return the blue cubes
     */
    public int getnBlueCubes() {
        return nBlueCubes;
    }

    /**
     * Gets red cubes.
     *
     * @return the red cubes
     */
    public int getnRedCubes() {
        return nRedCubes;
    }

    /**
     * Gets yellow cubes.
     *
     * @return the yellow cubes
     */
    public int getnYellowCubes() {
        return nYellowCubes;
    }

    /**
     * Instantiates a new Ammo.
     *
     * @param nYellowCubes the n yellow cubes
     * @param nBlueCubes   the n blue cubes
     * @param nRedCubes    the n red cubes
     * @param powerUpAmmo  the power up ammo
     */
    public Ammo (int nYellowCubes, int nBlueCubes, int nRedCubes, boolean powerUpAmmo) {
        super();
        this.nBlueCubes= nBlueCubes;
        this.nRedCubes= nRedCubes;
        this.nYellowCubes= nYellowCubes;
        this.hasPowerUp = powerUpAmmo;
    }
}