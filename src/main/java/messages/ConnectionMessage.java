package messages;


public class ConnectionMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CONNECTION;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
