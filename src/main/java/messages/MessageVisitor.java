package messages;

public interface MessageVisitor {
    void visit(ConnectionMessage l);
    void visit(GameStartedMessage i);
}
