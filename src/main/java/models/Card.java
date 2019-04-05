package models;

public abstract class Card {
    protected Color color;

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor () {
        return color;
    }
    public Card(Color color){
        this.color= color;
    }
}

