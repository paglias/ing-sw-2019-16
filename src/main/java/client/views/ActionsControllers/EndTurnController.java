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

    GenericWindows window = new GenericWindows();

    @FXML private Button endTurnButton;

    @FXML private Button reloadButton;

    //sends endturn message, closes the window.
    @FXML void endTurn(ActionEvent event) {
        EndTurnMessage message = new EndTurnMessage();
        Game.controller.sendMsg(message);
        Stage stage = (Stage) endTurnButton.getScene().getWindow();
        stage.close();
    }

    //Opens a new window where the player can reload
    @FXML void reload(ActionEvent event) {
        window.reloadWindow();
    }

}
