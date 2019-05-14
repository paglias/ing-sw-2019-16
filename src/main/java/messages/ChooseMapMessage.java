package messages;

public class ChooseMapMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CHOOSE_MAP;
    private int mapNumber;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public int getMapNumber () { return mapNumber; }
    public void setMapNumber (int mapNumber) { this.mapNumber = mapNumber; }
}
