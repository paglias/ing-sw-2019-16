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

public class MoveFourController implements Initializable {

    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;
    @FXML private Button moveTwo;
    @FXML private Button moveThree;
    @FXML private Button confirm;
    @FXML private Button moveFour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirm.setDisable(true);
        moveTwo.setDisable(true);
        moveThree.setDisable(true);
        moveFour.setDisable(true);
    }

    /**
     * Close window.
     *
     * @param event the event
     */
    @FXML void closeWindow(ActionEvent event) {
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    @FXML void openMoveFour(ActionEvent event) {
        genericWindow.moveWindow();
        moveFour.setDisable(true);

    }

    /**
     * Open move.
     *
     * @param event the event
     */
    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
        moveOne.setDisable(true);
        moveTwo.setDisable(false);
        confirm.setDisable(false);
    }

    @FXML void openMoveThree(ActionEvent event) {
        genericWindow.moveWindow();
        moveThree.setDisable(true);
        moveFour.setDisable(false);
    }

    @FXML void openMoveTwo(ActionEvent event) {
        genericWindow.moveWindow();
        moveTwo.setDisable(true);
        moveThree.setDisable(false);
    }
}