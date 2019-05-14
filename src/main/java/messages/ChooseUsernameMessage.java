package messages;

public class ChooseUsernameMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CHOOSE_USERNAME;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }
}
