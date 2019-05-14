package messages;

public class EndGameMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.END_GAME;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
