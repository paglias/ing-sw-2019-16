package messages;

public class GameStateMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_STATE;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
