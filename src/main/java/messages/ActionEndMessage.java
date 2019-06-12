package messages;

import controllers.ActionController;
import messages.client_data.ClientInput;

public class ActionEndMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION;
    private ClientInput clientInput;
    private String action; // Action item

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public ActionController.Action getAction() {
        return ActionController.Action.valueOf(action);
    }

    public void setAction(String action) {
        this.action = action;
    }
}
