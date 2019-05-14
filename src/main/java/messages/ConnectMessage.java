package messages;


public class ConnectMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CONNECT;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
