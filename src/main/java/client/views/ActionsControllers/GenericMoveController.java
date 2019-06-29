package client.views.ActionsControllers;

import client.views.Game;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
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

        if (newPosition.getValue()!=null){
            String positionSelected = newPosition.getSelectionModel().getSelectedItem();
            int square = Integer.parseInt(positionSelected);

            ActionMessage newPositionMessage = new ActionMessage();
            newPositionMessage.setActionItem("MOVE");
            ClientInput clientInput = new ClientInput();
            clientInput.position= square;
            newPositionMessage.setClientInput(clientInput);
            Game.controller.sendMsg(newPositionMessage);

            Stage stage = (Stage) confirm.getScene().getWindow();
            stage.close();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> positions= new ArrayList<>();
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
        ObservableList<String> availableChoices = FXCollections.observableArrayList(positions);
        newPosition.setItems(availableChoices);

        confirm.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> newPosition.getValue() != null, newPosition.valueProperty()));
    }
}