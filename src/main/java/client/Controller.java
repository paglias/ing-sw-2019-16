package client;

import client.views.AbstractView;
import client.views.Game;
import client.views.Lobby;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import messages.*;
import utils.Constants;
import utils.Logger;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements MessageVisitor  {
    private Connection connection;
    private Scanner keyboard;
    private ExecutorService pool;
    private AbstractView currentView;

    Controller(Connection connection, Scanner keyboard) {
        this.connection = connection;
        this.keyboard = keyboard;
        this.pool = Executors.newCachedThreadPool();
    }

    void init () {
        Logger.info("Starting the GUI...");

        pool.submit(() -> {
            Game.startGame(this);
        });
    }

    public void registerCurrentView (AbstractView abstractView) {
        currentView = abstractView;
    }

    public void sendMsg (AbstractMessage msg) {
        connection.send(msg.serialize());
    }

    void onServerMessage (String msg) {
        if (Constants.DEBUG) Logger.info("From server >>> " + msg);
        // TODO handle errors from deserialization/handling
        AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
        parsedMsg.accept(this);
    }

    private String waitForUserInput () {
        return keyboard.nextLine();
    }

    public void visit(GameStateMessage gameStateMessage) {
        if (Constants.DEBUG) Logger.info("handling game state msg");
        if (currentView != null) currentView.updateWithData(gameStateMessage);

    }
    public void visit(EndGameMessage endGameMessage) {
        if (Constants.DEBUG) Logger.info("handling end game msg" + endGameMessage.serialize());
    }

    public void visit(ErrorMessage errorMessage) {
        // TODO show popup
        Logger.info("Received error message from server: " + errorMessage.getErrorMsg());
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
