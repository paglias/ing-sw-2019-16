package models.cards;

public class Ammo extends Card {
    protected int nYellowCubes;
    protected int nBlueCubes;
    protected int nRedCubes;
    protected Boolean hasPowerUp;

    /**
     * Decrease yellow cubes.
     */
    public void decreaseYellowCubes() {
        // TODO for this and the others, error or anyway prevents cubs from going below 0
        this.nYellowCubes--;
    }

    /**
     * Decrease blue cubes.
     */
    public void decreaseBlueCubes() {
        this.nBlueCubes--;
    }

    /**
     * Decrease red cubes.
     */
    public void decreaseRedCubes() {
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
        this.hasPowerUp = powerUpAmmo;
    }
}