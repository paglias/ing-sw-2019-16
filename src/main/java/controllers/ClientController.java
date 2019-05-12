package controllers;

import messages.AbstractMessage;
import messages.ConnectionMessage;
import messages.GameStartedMessage;
import messages.MessageVisitor;
import server.ClientHandler;


public class ClientController implements MessageVisitor {
    private ClientHandler clientHandler;
    private GameController gameController;

    public ClientController (GameController gameController, ClientHandler clientHandler) {
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    public void onClientMessage (String msg) {
        System.out.println("From client >>> " + msg);
        AbstractMessage parsedMsg = AbstractMessage.deserialize(msg);
        parsedMsg.accept(this);
    }

    public void visit(ConnectionMessage connMsg) {
        System.out.println("handling connection msg" + connMsg.serialize());
    }

    public void visit(GameStartedMessage gameStartedMessage) {
        System.out.println("handling gamestarted msg" + gameStartedMessage.serialize());
    }

    private void sendClientMessage (String msg) {
        clientHandler.sendMessage(msg);
    }

    public void init () {
        if (gameController.getGameBoard().hasStarted()) {
            sendClientMessage("Game already started!");
        }
    }
}
