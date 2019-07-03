package client.views;

import javafx.fxml.Initializable;
import messages.GameStateMessage;

/**
 *  Abstract view.
 */
public abstract class AbstractView implements Initializable {
    public abstract void updateWithData (GameStateMessage gameStateMessage);
}