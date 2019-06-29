package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MoveAndShootController implements Initializable {

    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;
    @FXML private Button shoot;
    @FXML private Button continueButton;

    @FXML
    void confirmAction(ActionEvent event) {
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }


    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
        continueButton.setDisable(false);
    }

    @FXML void openShoot(ActionEvent event) {
        genericWindow.shootWindow();
        Stage stage = (Stage) shoot.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setDisable(true);
    }
}
