package client;

import client.views.AbstractView;
import client.views.Game;
import client.views.GenericWindows;
import client.views.Lobby;
import javafx.application.Platform;
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
    private GameStateMessage lastGameStateMessage = null;

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

    /**
     * Update view.
     */
    void updateView () {
        if (currentView == null) return;

        Platform.runLater(() -> {
            try {
                currentView.updateWithData(lastGameStateMessage);
            } catch (Throwable e) {
                Logger.err(e, "Error updating view.");
            }
        });
    }

    /**
     * Register current view.
     *
     * @param abstractView the abstract view
     */
    public void registerCurrentView (AbstractView abstractView) {
        currentView = abstractView;
        // Update immediately with current data
        updateView();
    }

    /**
     * Send msg.
     *
     * @param msg the msg
     */
    public void sendMsg (AbstractMessage msg) {
        connection.send(msg.serialize());
    }

    /**
     * Gets last game state message.
     *
     * @return the last game state message
     */
    public GameStateMessage getLastGameStateMessage () { return lastGameStateMessage; }

    /**
     * On server message.
     *
     * @param msg the msg
     */
    void onServerMessage (String msg) {
        if (Constants.DEBUG) Logger.info("From server >>> " + msg);
        try {
            AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
            parsedMsg.accept(this);
        } catch (Throwable e) {
            Platform.runLater(() -> {
                GenericWindows errorWindow = new GenericWindows();
                errorWindow.errorMessage(e.getMessage());
            });
        }
    }

    public void visit(GameStateMessage gameStateMessage) {
        if (Constants.DEBUG) Logger.info("handling game state msg");
        // Save a reference to the game state message in case we're changing window
        // and it can't update right away
        lastGameStateMessage = gameStateMessage;
        updateView();
    }

    public void visit(EndGameMessage endGameMessage) {
        if (Constants.DEBUG) Logger.info("handling end game msg, winner" + endGameMessage.getWinner());
        Platform.runLater(() -> {
            GenericWindows winnerWindow = new GenericWindows();
            winnerWindow.winnerMessage(endGameMessage.getWinner());
        });
    }

    public void visit(ErrorMessage errorMessage) {
        if (Constants.DEBUG) Logger.info("handling error msg" + errorMessage.getErrorMsg());
        Platform.runLater(() -> {
            GenericWindows errorWindow = new GenericWindows();
            errorWindow.errorMessage(errorMessage.getErrorMsg());
        });
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
