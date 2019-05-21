package messages;

public class GameSettingsMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_SETTINGS;
    private int mapNumber;
    private int skullsNumber;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public int getMapNumber () { return mapNumber; }
    public void setMapNumber (int mapNumber) { this.mapNumber = mapNumber; }

    public int getSkullsNumber () { return skullsNumber; }
    public void setSkullsNumber (int skullsNumber) { this.skullsNumber = skullsNumber; }
}
