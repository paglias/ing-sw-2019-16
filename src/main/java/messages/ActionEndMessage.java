package messages;

public class ActionEndMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION_END;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
