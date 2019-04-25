package models.cards;

public abstract class Card {
    public enum Color {
        YELLOW,
        RED,
        BLUE;
    }

    protected Color color = null;

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

