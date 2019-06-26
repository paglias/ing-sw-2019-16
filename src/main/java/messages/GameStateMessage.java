package messages;

import controllers.ClientController;
import controllers.GameController;
import messages.client_data.GameBoardData;
import messages.client_data.PlayerYouData;
import models.GameBoard;
import utils.Logger;

public class GameStateMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_STATE;

    public GameBoardData gameBoardData;
    public PlayerYouData playerYouData;

    public static void updateClients (GameController gameController) {
        GameBoard gameBoard = gameController.getGameBoard();
        GameBoardData gameBoardData = new GameBoardData(gameBoard);

        gameBoard.getPlayers().stream().forEach(p -> {
            ClientController clientForPlayer = gameController.getClientForPlayer(p);
            PlayerYouData playerYouData = new PlayerYouData(p);
            GameStateMessage gameStateMessage = new GameStateMessage(gameBoardData, playerYouData);

            if (clientForPlayer != null) clientForPlayer.sendMsg(gameStateMessage);
        });
    }

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public GameStateMessage (GameBoardData gameBoardData, PlayerYouData playerYouData) {
        this.gameBoardData = gameBoardData;
        this.playerYouData = playerYouData;
    }
}
