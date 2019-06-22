package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MoveAndShootController {

    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;

    @FXML private Button shoot;


    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
    }

    @FXML void openShoot(ActionEvent event) {
        genericWindow.shootWindow();
    }
}
