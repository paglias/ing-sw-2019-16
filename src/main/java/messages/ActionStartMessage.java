package messages;

import controllers.ActionController;

public class ActionStartMessage extends AbstractMessage {
    public final MessageTopic topic = MessageTopic.ACTION_START;
    private String action; // Single action

    public void accept(MessageVisitor v) {
        v.visit(this);
    }

    /**
     * Gets action.
     *
     * @return the action
     */
    public ActionController.Action getAction() {
        return ActionController.Action.valueOf(action);
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
