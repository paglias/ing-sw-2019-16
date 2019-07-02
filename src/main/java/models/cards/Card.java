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
     * Sets color of the card.
     *
     * @param color the color
     */
    public void setColor (Color color) { this.color = color; }

    /**
     * Instantiates a new Card without color.
     */
    public Card (){ }
}

