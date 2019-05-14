package messages;

public interface MessageVisitor {
    // Client -> Server
    void visit(ConnectMessage msg);
    void visit(DisconnectMessage msg); // TODO from server as well?

    void visit(ChooseUsernameMessage msg);
    void visit(ChooseMapMessage msg);
    void visit(ActionMessage msg);
    void visit(EndTurnMessage msg);


    // Server -> Client(s)
    void visit(GameStateMessage msg);
    void visit(EndGameMessage msg); // TODO can collapse into GameState?

    // Both way
    void visit(ErrorMessage msg);
}
