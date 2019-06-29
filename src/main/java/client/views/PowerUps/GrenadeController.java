package client.views.PowerUps;

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
import messages.client_data.PlayerOtherData;
import messages.client_data.PowerUpData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GrenadeController implements Initializable {

    String playerOne;
    String playerTwo;
    String playerThree;
    String playerFour;
    String playerFive;

    @FXML private ChoiceBox<String> targetChoice;

    @FXML private Button confirmButton;

    @FXML void activate(ActionEvent event) {
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

        Game.controller.sendMsg(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SETS NICKNAMES OF PLAYERS
        ArrayList<PlayerOtherData> players = Game.controller.getLastGameStateMessage().gameBoardData.players;
        int index = 0;
        for (PlayerOtherData p : players) {
            if (index == 0) {
                playerOne = p.nickname;
            }

            if (index == 1) {
                playerTwo = p.nickname;
            }

            if (index == 2) {
                playerThree = p.nickname;
            }

            if (index == 3) {
                playerFour = p.nickname;
            }

            if (index == 4) {
                playerFive = p.nickname;
            }
            index++;
        }
        ArrayList<String> targets = new ArrayList();
        targets.add(playerOne);
        targets.add(playerTwo);
        targets.add(playerThree);
        targets.add(playerFour);
        targets.add(playerFive);

        ObservableList<String> availableChoices = FXCollections.observableArrayList(targets);
        targetChoice.setItems(availableChoices);
    }
}

