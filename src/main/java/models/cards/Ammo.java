package models.cards;

import java.util.ArrayList;

public class Ammo extends Card {
    protected ArrayList<Color> cubes;
    protected int nYellowCubes;
    protected int nBlueCubes;
    protected int nRedCubes;
    protected Boolean hasPowerUp;

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