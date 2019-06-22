package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MoveTwoReloadShootController {
    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;

    @FXML private Button moveTwo;

    @FXML private Button shoot;

    @FXML private Button reload;

    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
    }

    @FXML
    void openReload(ActionEvent event) {
        genericWindow.reloadWindow();
    }

    @FXML
    void openShoot(ActionEvent event) {
        genericWindow.shootWindow();
    }
}
