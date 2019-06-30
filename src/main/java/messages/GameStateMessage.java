package messages;

import controllers.ClientController;
import controllers.GameController;
import messages.client_data.GameBoardData;
import messages.client_data.PlayerYouData;
import models.GameBoard;
import utils.Logger;

import java.util.ArrayList;

public class GameStateMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.GAME_STATE;
    public static ArrayList<String> actionsHistoryTemp = new ArrayList<>();

    public GameBoardData gameBoardData;
    public PlayerYouData playerYouData;
    public ArrayList<String> actionsHistory;

    public static void updateClients (GameController gameController) {
        GameBoard gameBoard = gameController.getGameBoard();
        GameBoardData gameBoardData = new GameBoardData(gameBoard);

        gameBoard.getPlayers().stream().forEach(p -> {
            ClientController clientForPlayer = gameController.getClientForPlayer(p);
            PlayerYouData playerYouData = new PlayerYouData(p);
            GameStateMessage gameStateMessage = new GameStateMessage(gameBoardData, playerYouData);

            if (clientForPlayer != null && p.isConnected()) clientForPlayer.sendMsg(gameStateMessage);
        });
        actionsHistoryTemp.clear();
    }

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public GameStateMessage (GameBoardData gameBoardData, PlayerYouData playerYouData) {
        this.gameBoardData = gameBoardData;
        this.playerYouData = playerYouData;
        this.actionsHistory = new ArrayList<>();
        actionsHistory.addAll(actionsHistoryTemp);
    }
}
