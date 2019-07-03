package messages;

/**
 * The type Action end message.
 */
public class ActionEndMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION_END;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
