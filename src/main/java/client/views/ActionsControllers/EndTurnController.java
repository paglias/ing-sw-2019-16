package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import messages.EndTurnMessage;

//Controller of a window that has 2 buttons, reload and end turn. Lets user either reload a weapon or end his turn.
public class EndTurnController {

    private GenericWindows window = new GenericWindows();

    @FXML private Button endTurnButton;

    @FXML private Button reloadButton;

    /**
     * End turn event, closes the window.
     *
     * @param event the event
     */

    @FXML void endTurn(ActionEvent event) {
        EndTurnMessage message = new EndTurnMessage();
        Game.controller.sendMsg(message);
        Stage stage = (Stage) endTurnButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Open a new window where the player can reload.
     *
     * @param event the event
     */

    @FXML void reload(ActionEvent event) {
        window.reloadWindow();
    }

}
