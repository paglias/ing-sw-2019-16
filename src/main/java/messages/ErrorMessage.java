package messages;

public class ErrorMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ERROR;
    private String errorMsg;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public String getErrorMsg () {
        return errorMsg;
    }

    public void setErrorMsg (String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
