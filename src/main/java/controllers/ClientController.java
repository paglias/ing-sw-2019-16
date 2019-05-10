package controllers;

import server.ClientHandler;

public class ClientController {
    private ClientHandler clientHandler;
    private GameController gameController;

    public ClientController (GameController gameController, ClientHandler clientHandler) {
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    public void onClientMessage (String msg) {
        System.out.println("From client >>> " + msg);
        clientHandler.sendMessage(msg);
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
