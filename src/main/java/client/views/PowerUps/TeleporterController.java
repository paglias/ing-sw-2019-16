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
import java.util.ResourceBundle;

public class TeleporterController implements Initializable {

    @FXML private ChoiceBox<String> positionChoice;
    @FXML private Button teleport;

    private ActionEndMessage endMessage = new ActionEndMessage();

    /**
     * Use powerup effect for Teleporter powerup..
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
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

        ObservableList<String> availableChoices = FXCollections.observableArrayList("0","1","2",
                "3","4","5","6","7","8","9","10","11");
        positionChoice.setItems(availableChoices);

        if (Constants.DEBUG){
            Logger.info("All elements have loaded successfully");
        }
    }
}