package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.client_data.ClientInput;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveTwiceGrabController implements Initializable {
    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;

    @FXML private Button moveTwo;

    @FXML private Button grab;

    ActionMessage message = new ActionMessage();
    ClientInput clientInput = new ClientInput();
    ActionEndMessage endMessage = new ActionEndMessage();

    @FXML void grab(ActionEvent event) {

    }

    @FXML
    void openMove(ActionEvent event) {
        genericWindow.moveWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grab.setDisable(true);
        moveTwo.setDisable(true);
        moveOne.setDisable(false);
    }
}
