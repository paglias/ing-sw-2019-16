package models.cards;

public class Ammo extends Card {
    private int nYellowCubes;
    private int nBlueCubes;
    private int nRedCubes;
    private Boolean hasPowerUp;

    /**
     * Gets boolean has power up.
     *
     * @return the boolean has power up
     */
    public boolean getHasPowerUp() {
        return hasPowerUp;
    }

    /**
     * Gets blue cubes.
     *
     * @return the blue cubes
     */
    public int getBlueCubes() {
        return nBlueCubes;
    }

    /**
     * Gets red cubes.
     *
     * @return the red cubes
     */
    public int getRedCubes() {
        return nRedCubes;
    }

    /**
     * Gets yellow cubes.
     *
     * @return the yellow cubes
     */
    public int getYellowCubes() {
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
        this.nBlueCubes = nBlueCubes;
        this.nRedCubes = nRedCubes;
        this.nYellowCubes = nYellowCubes;
        this.hasPowerUp = false;

        if (powerUpAmmo) {
            this.hasPowerUp = true;
        }
    }
}