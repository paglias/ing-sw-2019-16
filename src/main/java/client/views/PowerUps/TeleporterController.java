package client.views.PowerUps;

import client.views.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.client_data.ClientInput;
import messages.client_data.PowerUpData;
import utils.Constants;
import utils.Logger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeleporterController implements Initializable {

    @FXML private ChoiceBox<String> positionChoice;
    @FXML private Button teleport;

    private ActionEndMessage endMessage = new ActionEndMessage();

    @FXML void teleportAction(ActionEvent event) throws InterruptedException {
        String position = positionChoice.getValue();

        ActionMessage message = new ActionMessage();
        message.setActionItem("USE_POWER_UP");

        ClientInput clientInput = new ClientInput();
        clientInput.positions.add(Integer.parseInt(position));

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals("Teleporter")) {
                break;
            }
            powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);

        //Sends the end message
        Thread.sleep(508);
        Game.controller.sendMsg(endMessage);

        //Closes the window
        Stage stage = (Stage) teleport.getScene().getWindow();
        stage.close();
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
        positionChoice.setItems(availableChoices);

        if (Constants.DEBUG){
            Logger.info("All elements have loaded successfully");
        }
    }
}