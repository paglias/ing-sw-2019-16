package models;

public class PowerUp extends Card {
    public enum Name {
        TARGETING_SCOPE,
        NEWTON,
        TAGBACK_GRENADE,
        TELEPORTER
    }
    private Name name;
    private Color color;

    /**
     * Instantiates a new Power up.
     *
     * @param name  the name
     * @param color the color
     */
    public PowerUp (Name name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName () {
        return name;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor () {
        return color;
    }
}
