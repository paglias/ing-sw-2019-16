package messages;

public interface MessageInterface {
    void accept(MessageVisitor v);
}
