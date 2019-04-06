package models;

public class Ammo extends Card{

    protected PowerUp powerUp;

    /**
     * Instantiates a new Ammo.
     *
     * @param nYellowCubes the n yellow cubes
     * @param nBlueCubes   the n blue cubes
     * @param nRedCubes    the n red cubes
     * @param powerUpAmmo  the power up ammo
     */
    public Ammo (int nYellowCubes, int nBlueCubes, int nRedCubes, boolean powerUpAmmo) {
        super (nBlueCubes, nRedCubes, nYellowCubes, powerUpAmmo);
    }
}