package client;

import client.views.AbstractView;
import client.views.Game;
import client.views.GenericWindows;
import javafx.application.Platform;
import messages.*;
import utils.Constants;
import utils.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controller implements MessageVisitor  {
    private Connection connection;
    private ExecutorService pool;
    private AbstractView currentView;
    private GameStateMessage lastGameStateMessage = null;

    Controller() {
        this.pool = Executors.newCachedThreadPool();
    }

    /**
     * Connect client to server, need a host and a connection port.
     * Send error message if connection is closed wrong or if client is connecting wrong.
     *
     * @param host the host
     * @param port the port
     */
    void connect (String host, int port) {
        // Connect and handle messages from the server in a separate thread to avoid blocking the gui
        pool.submit(() -> {
            try {
                Logger.info("Connecting to server at " + host + ":" + port);

                connection  = new Connection(host, port);
                connection.init();

                String msg;
                do {
                    msg = connection.receive();
                    if (msg != null) onServerMessage(msg);
                } while (msg != null);
            } catch (Throwable e) {
                Logger.err(e, null);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        Logger.err(e, "Error closing connection.");
                    }
                }
                Client.quit();
            }
        });
    }

    /**
     * Start the GUI.
     */
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
     * Messages sent by server.
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
    /**
     * Messages sent by server.
     *
     * @param gameStateMessage for game state.
     */
    public void visit(GameStateMessage gameStateMessage) {
        if (Constants.DEBUG) Logger.info("handling game state msg");
        // Save a reference to the game state message in case we're changing window
        // and it can't update right away
        lastGameStateMessage = gameStateMessage;
        updateView();
    }
    /**
     * Messages sent by server.
     *
     * @param endGameMessage the ending game message.
     */
    public void visit(EndGameMessage endGameMessage) {
        if (Constants.DEBUG) Logger.info("handling end game msg, winner" + endGameMessage.getWinner());
        Platform.runLater(() -> {
            GenericWindows winnerWindow = new GenericWindows();
            winnerWindow.winnerMessage(endGameMessage.getWinner());
        });
    }
    /**
     * Manages error messages.
     *
     * @param errorMessage the error message.
     */
    public void visit(ErrorMessage errorMessage) {
        if (Constants.DEBUG) Logger.info("handling error msg" + errorMessage.getErrorMsg());
        Platform.runLater(() -> {
            GenericWindows errorWindow = new GenericWindows();
            errorWindow.errorMessage(errorMessage.getErrorMsg());
        });
    }
    /**
     * Choose nickname message for players, server only.
     *
     * @param chooseNicknameMessage the message for choosing nickname.
     */
    public void visit(ChooseNicknameMessage chooseNicknameMessage) {

    }
    /**
     * Manages game setting messages, server only.
     *
     * @param gameSettingsMessage the message for game settings.
     */
    public void visit(GameSettingsMessage gameSettingsMessage) {

    }
    /**
     * New action start message, server only.
     *
     * @param actionStartMessage for action to start.
     */
    public void visit(ActionStartMessage actionStartMessage) {
        // Not implemented, server side only
    }
    /**
     * New action message, server only.
     *
     * @param actionMessage for action to be made.
     */
    public void visit(ActionMessage actionMessage) {
        // Not implemented, server side only
    }
    /**
     * New action end message, server only.
     *
     * @param actionEndMessage for action to end.
     */
    public void visit(ActionEndMessage actionEndMessage) {
        // Not implemented, server side only
    }
    /**
     * New end turn message, server only.
     *
     * @param endTurnMessage for turn to end.
     */
    public void visit(EndTurnMessage endTurnMessage) {
        // Not implemented, server side only
    }
}
