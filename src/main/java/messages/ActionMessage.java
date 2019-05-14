package messages;

public class ActionMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
