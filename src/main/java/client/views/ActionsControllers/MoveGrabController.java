package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MoveGrabController {
    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;

    @FXML private Button grab;

    @FXML void openGrab(ActionEvent event) {
        //TODO SEND MESSAGE TO SERVER
    }

    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
    }
}
