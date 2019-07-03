package messages;

public class ChooseNicknameMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.CHOOSE_NICKNAME;
    private String nickname;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname () { return nickname; }

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname (String nickname) { this.nickname = nickname; }
}
