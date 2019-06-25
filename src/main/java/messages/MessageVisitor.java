package messages;

public interface MessageVisitor {
    void visit(ChooseNicknameMessage msg);
    void visit(GameSettingsMessage msg);

    void visit(ActionStartMessage msg);
    void visit(ActionMessage msg);
    void visit(ActionEndMessage msg);

    void visit(EndTurnMessage msg);

    // Server -> Client(s)
    void visit(GameStateMessage msg);
    void visit(EndGameMessage msg);

    // Both way
    void visit(ErrorMessage msg);
}
