package messages;

import controllers.GameController;

public class GameStateMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_STATE;
    public String gameBoard;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public GameStateMessage (GameController gameController) {
        this.gameBoard = gson.toJson(gameController.getGameBoard());
    }
}
