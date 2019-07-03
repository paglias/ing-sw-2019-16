package messages;

public class GameSettingsMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_SETTINGS;
    private int mapNumber;
    private int skullsNumber;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    /**
     * Gets map number.
     *
     * @return the map number
     */
    public int getMapNumber () { return mapNumber; }

    /**
     * Sets map number.
     *
     * @param mapNumber the map number
     */
    public void setMapNumber (int mapNumber) { this.mapNumber = mapNumber; }

    /**
     * Gets skulls number.
     *
     * @return the skulls number
     */
    public int getSkullsNumber () { return skullsNumber; }

    /**
     * Sets skulls number.
     *
     * @param skullsNumber the skulls number
     */
    public void setSkullsNumber (int skullsNumber) { this.skullsNumber = skullsNumber; }
}
