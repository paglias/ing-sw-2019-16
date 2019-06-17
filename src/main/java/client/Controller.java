package client;

import client.views.Game;
import client.views.Lobby;
import messages.*;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements MessageVisitor  {
    private Connection connection;
    private Scanner keyboard;
    private ExecutorService pool;
    private Lobby lobby;

    Controller(Connection connection, Scanner keyboard) {
        this.connection = connection;
        this.keyboard = keyboard;
        this.pool = Executors.newCachedThreadPool();
    }

    void init () {
        System.out.println("Starting the GUI...");

        pool.submit(() -> {
            Game.startGame(this);
        });
    }

    public void setLobby (Lobby lobby) {
        this.lobby = lobby;
    }

    public void sendMsg (AbstractMessage msg) {
        connection.send(msg.serialize());
    }

    void onServerMessage (String msg) {
        System.out.println("From server >>> " + msg);
        // TODO handle errors from deserialization/handling
        AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
        parsedMsg.accept(this);
    }

    private String waitForUserInput () {
        return keyboard.nextLine();
    }

    public void visit(GameStateMessage gameStateMessage) {
        System.out.println("handling game state msg" );
        lobby.updateNickname(gameStateMessage.playerYouData.nickname);
    }
    public void visit(EndGameMessage endGameMessage) {
        System.out.println("handling end game msg" + endGameMessage.serialize());
    }

    public void visit(ErrorMessage errorMessage) {
        System.out.println("Received error message from server" + errorMessage.getErrorMsg());
    }

    public void visit(DisconnectMessage disconnectMessage) {
        // Not implemented, server side only
    }
    public void visit(ChooseNicknameMessage chooseNicknameMessage) {
        // Not implemented, server side only
    }
    public void visit(GameSettingsMessage gameSettingsMessage) {
        // Not implemented, server side only
    }
    public void visit(ActionStartMessage actionStartMessage) {
        // Not implemented, server side only
    }
    public void visit(ActionMessage actionMessage) {
        // Not implemented, server side only
    }
    public void visit(ActionEndMessage actionEndMessage) {
        // Not implemented, server side only
    }
    public void visit(EndTurnMessage endTurnMessage) {
        // Not implemented, server side only
    }
}
