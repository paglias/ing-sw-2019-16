package models;

public abstract class Card {
    protected Color color = null;
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
    public Card (Color color){
        this.color = color;
    }

    /**
     * Instantiates a new Card without color.
     */
    public Card (){ }
}

