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
import messages.client_data.PlayerOtherData;
import messages.client_data.PowerUpData;
import utils.Constants;
import utils.Logger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewtonController implements Initializable {

    @FXML private ChoiceBox<String> directionChoice;

    @FXML private Button confirm;

    @FXML private ChoiceBox<String> targetChoice;

    ActionMessage message = new ActionMessage();

    String playerOne;
    String playerTwo;
    String playerThree;
    String playerFour;
    String playerFive;

    @FXML
    void confirmNewton(ActionEvent event) throws InterruptedException {

        //Loads input on message and sends it to server
        String target = targetChoice.getValue();
        String direction = directionChoice.getValue();

        message.setActionItem("USE_POWER_UP");

        ClientInput clientInput = new ClientInput();
        clientInput.direction = direction;
        clientInput.players.add(target);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals("Newton")) {
                break;
            }

            powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;
        Game.controller.sendMsg(message);

        //Sends the end message
        Thread.sleep(500);
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);

        //Closes the window
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
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
        List<String> targets = new ArrayList();
        targets.add(playerOne);
        targets.add(playerTwo);
        targets.add(playerThree);
        targets.add(playerFour);
        targets.add(playerFive);

        ObservableList<String> availableChoices = FXCollections.observableArrayList(targets);
        targetChoice.setItems(availableChoices);

        //Populate cube choicebox
        ArrayList<String> directions = new ArrayList<>();
        directions.add("NORTH");
        directions.add("SOUTH");
        directions.add("WEST");
        directions.add("EAST");

        ObservableList<String> choices = FXCollections.observableArrayList(directions);
        directionChoice.setItems(choices);

        if (Constants.DEBUG){
            Logger.info("All elements have loaded successfully");
        }
    }
}