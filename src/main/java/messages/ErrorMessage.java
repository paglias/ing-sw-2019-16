package messages;

public class ErrorMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ERROR;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
