package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionStartMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveThreeController implements Initializable {
    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;
    @FXML private Button moveTwo;
    @FXML private Button moveThree;
    @FXML private Button confirm;

    @FXML
    void closeWindow(ActionEvent event) {

        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    @FXML void openMoveOne(ActionEvent event) {
        genericWindow.moveWindow();
        confirm.setVisible(true);
        moveOne.setVisible(false);
        moveTwo.setVisible(true);
    }

    @FXML void openMoveTwo(ActionEvent event) {
        genericWindow.moveWindow();
        moveTwo.setVisible(false);
        moveThree.setVisible(true);
    }
    @FXML void openMoveThree(ActionEvent event) {
        genericWindow.moveWindow();
        moveThree.setVisible(false);
    }

    public void initialize(URL location, ResourceBundle resources) {
        confirm.setVisible(false);
        moveTwo.setVisible(false);
        moveThree.setVisible(false);
    }
}