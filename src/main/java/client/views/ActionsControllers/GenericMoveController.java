package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
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
import messages.client_data.PlayerYouData;
import messages.client_data.SquareData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Window that lets the user select a new position and confirm it.
// Confirm sends a message to server.
public class GenericMoveController implements Initializable {

    @FXML public Button confirm;
    @FXML public ChoiceBox<String> newPosition;
    @FXML public Button viewMapButton;

    /**
     * Confirm move event.
     * move the player to the selected position.
     * Takes the selected value from the choicebox, creates clientinput, sends the message to the
     * server
     *
     * @param event the event
     */


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

    public void openMap(){
        GenericWindows window = new GenericWindows();
        int map = Game.controller.getLastGameStateMessage().gameBoardData.nMap;
        window.mapPreview(map);
    }

    public void loadCanAccess () {
        PlayerYouData player = Game.controller.getLastGameStateMessage().playerYouData;
        SquareData square = Game.controller.getLastGameStateMessage().gameBoardData.squares.get(player.position);

        ArrayList<String> positions = new ArrayList<>();

        square.canAccessDirectly.forEach(s -> {
            positions.add(String.valueOf(s));
        });
        ObservableList<String> availableChoices = FXCollections.observableArrayList(positions);
        newPosition.setItems(availableChoices);

        confirm.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> newPosition.getValue() != null, newPosition.valueProperty()));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCanAccess();
    }
}