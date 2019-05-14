package messages;

public class ChooseMapMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CHOOSE_MAP;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
