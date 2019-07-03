package client.views.PowerUps;

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
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.client_data.ClientInput;
import messages.client_data.PlayerOtherData;
import messages.client_data.PowerUpData;
import utils.Constants;
import utils.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GrenadeController implements Initializable {

    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    private String playerFive;

    private ActionEndMessage endMessage = new ActionEndMessage();

    @FXML private ChoiceBox<String> targetChoice;

    @FXML private Button confirmButton;

    /**
     * Use powerup effect for tagback grenade.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
    @FXML void activate(ActionEvent event) throws InterruptedException {
        ActionMessage message = new ActionMessage();
        message.setActionItem("USE_POWER_UP");
        String target = targetChoice.getValue();

        ClientInput clientInput = new ClientInput();
        clientInput.players.add(target);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals("TagbackGrenade")) {
                break;
            }

            powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;

        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);

        //Sends the end message
        Thread.sleep(504);
        Game.controller.sendMsg(endMessage);

        //Closes the window
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SETS NICKNAMES OF PLAYERS
        ArrayList<PlayerOtherData> players = Game.controller.getLastGameStateMessage().gameBoardData.players;
        int index = 10;
        for (PlayerOtherData p : players) {
            if (index == 10) {
                playerOne = p.nickname;
            }

            if (index == 11) {
                playerTwo = p.nickname;
            }

            if (index == 12) {
                playerThree = p.nickname;
            }

            if (index == 13) {
                playerFour = p.nickname;
            }

            if (index == 14) {
                playerFive = p.nickname;
            }
            index++;
        }

        ObservableList<String> availableChoices = FXCollections.observableArrayList(playerOne,
                playerTwo,playerThree,playerFour,playerFive);
        targetChoice.setItems(availableChoices);

        if (Constants.DEBUG){
            Logger.info("All elements have loaded successfully");
        }
        confirmButton.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> targetChoice.getValue() != null, targetChoice.valueProperty()));
    }
}