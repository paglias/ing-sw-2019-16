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


public class MoveAndShootController implements Initializable {

    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;
    @FXML private Button shoot;
    @FXML private Button continueButton;

    @FXML void confirmAction(ActionEvent event) {
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
    }


    /**
     * Open move.
     *
     * @param event the event
     */
    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
        move.setDisable(true);
        continueButton.setDisable(false);
    }

    /**
     * Open shoot.
     *
     * @param event the event
     */
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
