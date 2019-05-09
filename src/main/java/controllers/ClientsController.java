package controllers;

import server.ClientHandler;

import java.util.ArrayList;

public class ClientsController {
    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public void addClient (ClientHandler client) {
        clients.add(client);
    }

    public void removeClient (ClientHandler client) {
        clients.remove(client);
    }
}
