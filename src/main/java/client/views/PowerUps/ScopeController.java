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

public class ScopeController implements Initializable {

    String playerOne;
    String playerTwo;
    String playerThree;
    String playerFour;
    String playerFive;

    @FXML private ChoiceBox<String> targetChoice;

    @FXML private Button scopeButton;

    @FXML private ChoiceBox<String> ammoChoice;

    @FXML void confirmScope(ActionEvent event) {
        String target = targetChoice.getValue();
        String ammo = ammoChoice.getValue();

        ActionMessage message = new ActionMessage();
        message.setActionItem("USE_POWER_UP");

        ClientInput clientInput = new ClientInput();
        clientInput.cube = ammo;
        clientInput.players.add(target);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals("TargetingScope")) {
                break;
            }

            powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;

        Game.controller.sendMsg(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set playernicknames
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

        //Populate target choicebox
        ArrayList<String> playerTarget = new ArrayList();
        playerTarget.add(playerOne);
        playerTarget.add(playerTwo);
        playerTarget.add(playerThree);
        playerTarget.add(playerFour);
        playerTarget.add(playerFive);

        ObservableList<String> availableChoices = FXCollections.observableArrayList(playerTarget);
        targetChoice.setItems(availableChoices);

        //Populate cube choicebox
        ArrayList<String> cubeColors = new ArrayList<>();
        cubeColors.add("RED");
        cubeColors.add("BLUE");
        cubeColors.add("YELLOW");

        ObservableList<String> colorChoices = FXCollections.observableArrayList(cubeColors);
        ammoChoice.setItems(colorChoices);
    }

}

