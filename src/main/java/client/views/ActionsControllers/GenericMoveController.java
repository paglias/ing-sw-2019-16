package client.views.ActionsControllers;

import client.views.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import messages.ActionMessage;
import messages.client_data.ClientInput;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Window that lets the user select a new position and confirm it.
// Confirm sends a message to server.
public class GenericMoveController implements Initializable {

    @FXML public Button confirm;

    @FXML public ChoiceBox<String> newPosition;

    //Takes the selected value from the choicebox, creates clientinput, sends the message to the
    // server
    @FXML void confirmMove(ActionEvent event) {
        String positionSelected = newPosition.getSelectionModel().getSelectedItem();
        int square = Integer.parseInt(positionSelected);
        ActionMessage newPositionMessage = new ActionMessage();
        ClientInput clientInput = new ClientInput();
        clientInput.position= square;
        Game.controller.sendMsg(newPositionMessage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> positions= new ArrayList();
        positions.add("0");
        positions.add("1");
        positions.add("2");
        positions.add("2");
        positions.add("4");
        positions.add("5");
        positions.add("6");
        positions.add("7");
        positions.add("8");
        positions.add("9");
        positions.add("10");
        positions.add("11");
        ObservableList<String> availableChoices = FXCollections.observableArrayList(
                positions.get(0),
                positions.get(1),
                positions.get(2),
                positions.get(3),
                positions.get(4),
                positions.get(5),
                positions.get(6),
                positions.get(7),
                positions.get(8),
                positions.get(9),
                positions.get(10),
                positions.get(11));
        newPosition.setItems(availableChoices);
    }
}
