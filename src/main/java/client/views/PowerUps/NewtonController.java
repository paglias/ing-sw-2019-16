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
import java.util.List;
import java.util.ResourceBundle;

public class NewtonController implements Initializable {

    @FXML private ChoiceBox<String> directionChoice;
    @FXML private Button confirm;
    @FXML private ChoiceBox<String> targetChoice;
    @FXML private ChoiceBox<String> positionChoice;

    private ActionMessage message = new ActionMessage();

    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    private String playerFive;

    /**
     * Use powerup effect for Newton powerup.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
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
        clientInput.positions.add(Integer.parseInt(positionChoice.getValue()));
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);

        //Sends the end message
        Thread.sleep(502);
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
        int index = 20;
        for (PlayerOtherData p : players) {
            if (index == 20) {
                playerOne = p.nickname;
            }
            if (index == 21) {
                playerTwo = p.nickname;
            }
            if (index == 22) {
                playerThree = p.nickname;
            }
            if (index == 23) {
                playerFour = p.nickname;
            }
            if (index == 24) {
                playerFive = p.nickname;
            }
            index++;
        }

        //Populate position choicebox

        positionChoice.setItems(FXCollections.observableArrayList(
                "0", "1", "2", "3", "4", "5",
                "6","7","8","9","10","11"));

        //Populate target choicebox
        ObservableList<String> availableChoices = FXCollections.observableArrayList(playerOne,playerTwo,
                playerThree,playerFour,playerFive);
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

        confirm.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> targetChoice.getValue() != null, targetChoice.valueProperty()));
    }
}