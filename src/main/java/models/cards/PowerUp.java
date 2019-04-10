package models.cards;

public class PowerUp extends Card {
    public enum Name {
        TARGETING_SCOPE,
        NEWTON,
        TAGBACK_GRENADE,
        TELEPORTER
    }
    private Name name;


    /**
     * Instantiates a new Power up.
     *
     * @param name  the name
     * @param color the color
     */
    public PowerUp (Name name, Color color) {
        super(color);
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName () {
        return name;
    }
}
