package messages;

public class ChooseNicknameMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CHOOSE_NICKNAME;
    private String nickname;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public String getNickname () { return nickname; }
    public void setNickname (String nickname) { this.nickname = nickname; }
}
