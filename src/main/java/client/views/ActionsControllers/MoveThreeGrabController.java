package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MoveThreeGrabController {
    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;

    @FXML private Button moveThree;

    @FXML private Button grab;

    @FXML private Button moveTwo;

    @FXML void grab(ActionEvent event) {
        //TODO SEND MESSAGE TO SERVER
    }

    @FXML
    void openMove(ActionEvent event) {
        genericWindow.moveWindow();
    }
}
