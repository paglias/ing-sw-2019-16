package messages;

public class EndGameMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.END_GAME;
    private String winner;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public String getWinner () { return winner; }
    public void setWinner (String winner) { this.winner = winner; }
}
