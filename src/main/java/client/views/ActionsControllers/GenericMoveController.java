package client.views.ActionsControllers;

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
        newPosition.getItems().add("0");
        newPosition.getItems().add("1");
        newPosition.getItems().add("2");
        newPosition.getItems().add("3");
        newPosition.getItems().add("4");
        newPosition.getItems().add("5");
        newPosition.getItems().add("6");
        newPosition.getItems().add("7");
        newPosition.getItems().add("8");
        newPosition.getItems().add("9");
        newPosition.getItems().add("10");
        newPosition.getItems().add("11");
    }
}
