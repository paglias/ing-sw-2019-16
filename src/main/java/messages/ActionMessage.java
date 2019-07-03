package messages;

import controllers.ActionController;
import messages.client_data.ClientInput;

public class ActionMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION;
    private ClientInput clientInput;
    private String action; // Single action item

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    /**
     * Gets client input.
     *
     * @return the client input
     */
    public ClientInput getClientInput() {
        return clientInput;
    }

    /**
     * Sets client input.
     *
     * @param clientInput the client input
     */
    public void setClientInput(ClientInput clientInput) {
        this.clientInput = clientInput;
    }

    /**
     * Gets action item.
     *
     * @return the action item
     */
    public ActionController.ActionItem getActionItem() {
        return ActionController.ActionItem.valueOf(action);
    }

    /**
     * Sets action item.
     *
     * @param action the action
     */
    public void setActionItem(String action) {
        this.action = action;
    }
}