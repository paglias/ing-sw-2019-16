package messages;

public class ActionEndMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
