package messages;

import controllers.ActionController;
import messages.client_data.ClientInput;

public class ActionMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION;
    private ClientInput clientInput;
    private String action; // Single action tiem

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    public ClientInput getClientInput() {
        return clientInput;
    }

    public void setClientInput(ClientInput clientInput) {
        this.clientInput = clientInput;
    }

    public ActionController.ActionItem getAction() {
        return ActionController.ActionItem.valueOf(action);
    }

    public void setAction(String action) {
        this.action = action;
    }
}
