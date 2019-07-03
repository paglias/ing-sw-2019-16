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

public class MoveThreeController implements Initializable {
    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;
    @FXML private Button moveTwo;
    @FXML private Button moveThree;
    @FXML private Button confirm;

    /**
     * Close window after action is done.
     *
     * @param event the event
     */
    @FXML
    void closeWindow(ActionEvent event) {

        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    /**
     * Open move one action window.
     * You can choose new position.
     *
     * @param event the event
     */
    @FXML void openMoveOne(ActionEvent event) {
        genericWindow.moveWindow();
        confirm.setDisable(false);
        moveOne.setDisable(true);
        moveTwo.setDisable(false);
    }

    /**
     Open move second action window.
     * You can choose new position.
     * @param event the event
     */
    @FXML void openMoveTwo(ActionEvent event) {
        genericWindow.moveWindow();
        moveTwo.setDisable(true);
        moveThree.setDisable(false);
    }

    /**
     Open move third action window.
     * You can choose new position.
     * @param event the event
     */
    @FXML void openMoveThree(ActionEvent event) {
        genericWindow.moveWindow();
        moveThree.setDisable(true);
    }

    public void initialize(URL location, ResourceBundle resources) {
        confirm.setDisable(true);
        moveTwo.setDisable(true);
        moveThree.setDisable(true);
    }
}