package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import messages.ActionEndMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveReloadShootController implements Initializable {
    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;
    @FXML private Button shoot;
    @FXML private Button continueButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setDisable(true);
    }

    @FXML
    void confirmAction(ActionEvent event) {

        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Open move action window.
     *
     * @param event the event
     */
    @FXML void openMove(ActionEvent event) {
        move.setDisable(true);
        genericWindow.moveWindow();
        continueButton.setDisable(false);
    }

    /**
     * Open reload action window.
     *
     * @param event the event
     */
    @FXML void openReload(ActionEvent event) {
        genericWindow.reloadWindow();
    }

    /**
     * Open shoot action window.
     *
     * @param event the event
     */
    @FXML void openShoot(ActionEvent event) {
        genericWindow.shootWindow();
        Stage stage = (Stage) shoot.getScene().getWindow();
        stage.close();
    }
}