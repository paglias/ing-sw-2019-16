package messages;

public class DisconnectMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.DISCONNECT;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}

