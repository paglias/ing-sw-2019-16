package client.views.ActionsControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

//Window that lets the user select a new position and confirm it.
// Confirm sends a message to server.

public class GenericMoveController {

    @FXML private Button confirm;

    @FXML private ChoiceBox<String> newPosition;

    @FXML void confirmMove(ActionEvent event) {

    }

    public void initialize(URL location, ResourceBundle resources) {
        //TODO ADD POSITIONS
    }
}
