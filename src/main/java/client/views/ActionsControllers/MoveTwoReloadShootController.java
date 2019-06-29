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

public class MoveTwoReloadShootController implements Initializable {
    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;
    @FXML private Button moveTwo;
    @FXML private Button shoot;
    @FXML private Button continueButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setDisable(true);
        moveTwo.setDisable(true);
    }

    @FXML void openReload(ActionEvent event) {
        genericWindow.reloadWindow();
        moveOne.setDisable(true);
        moveTwo.setDisable(true);
    }

    @FXML void openShoot(ActionEvent event) {
        genericWindow.shootWindow();
        Stage stage = (Stage) shoot.getScene().getWindow();
        stage.close();
    }



    @FXML void confirmAction(ActionEvent event) {
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    @FXML void openMoveOne(ActionEvent event) {
        moveOne.setDisable(true);
        moveTwo.setDisable(false);
        continueButton.setDisable(false);
    }

    @FXML void openMoveTwo(ActionEvent event) {
        moveTwo.setDisable(true);
    }
}