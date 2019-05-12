package messages;

public class GameStartedMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_STARTED;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
