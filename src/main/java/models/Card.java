package models;

public abstract class Card {
    protected Color color;
    protected int nYellowCubes;
    protected int nBlueCubes;
    protected int nRedCubes;
    protected boolean powerUpAmmo;

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor () {
        return color;
    }

    /**
     * Instantiates a new Card.
     *
     * @param color the color
     */
    public Card(Color color){
        this.color= color;
    }

    /**
     * Instantiates a new Card.
     *
     * @param nYellowCubes the n yellow cubes
     * @param nBlueCubes   the n blue cubes
     * @param nRedCubes    the n red cubes
     * @param powerUpAmmo  the power up ammo
     */
    public Card(int nYellowCubes, int nBlueCubes, int nRedCubes, boolean powerUpAmmo){
        this.nBlueCubes= nBlueCubes;
        this.nRedCubes= nRedCubes;
        this.nYellowCubes= nYellowCubes;
    }
}

