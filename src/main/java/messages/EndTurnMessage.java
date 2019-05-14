package messages;

public class EndTurnMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.END_TURN;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
