package messages;

public class EndGameMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.END_GAME;
    private String winner;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public String getWinner () { return winner; }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner (String winner) { this.winner = winner; }
}
