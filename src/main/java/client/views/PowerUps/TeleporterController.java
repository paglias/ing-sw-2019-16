package client.views.PowerUps;

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

public class TeleporterController implements Initializable {

    @FXML private ChoiceBox<String> positionChoice;

    @FXML private Button teleport;

    @FXML void teleportAction(ActionEvent event) {
        String position = positionChoice.getValue();

        ActionMessage message = new ActionMessage();
        message.setActionItem("USE_POWER_UP");

        ClientInput clientInput = new ClientInput();
        //TODO SET PARAMETERS ON CLIENTINPUT
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
        ObservableList<String> availableChoices = FXCollections.observableArrayList(positions);
        positionChoice.setItems(availableChoices);
    }
}
