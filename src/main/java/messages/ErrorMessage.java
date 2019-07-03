package messages;

public class ErrorMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ERROR;
    private String errorMsg;

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    /**
     * Gets error msg.
     *
     * @return the error msg
     */
    public String getErrorMsg () {
        return errorMsg;
    }

    /**
     * Sets error msg.
     *
     * @param errorMsg the error msg
     */
    public void setErrorMsg (String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
