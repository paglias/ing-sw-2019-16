package client.views.ActionsControllers;

import client.views.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.ActionStartMessage;
import messages.client_data.ClientInput;
import messages.client_data.PowerUpData;
import java.net.URL;
import java.util.ResourceBundle;

public class DuplicateSpawnController implements Initializable {

    private ActionStartMessage startMessage = new ActionStartMessage();
    private ActionMessage message = new ActionMessage();
    private ClientInput clientInput = new ClientInput();
    private ActionEndMessage endMessage = new ActionEndMessage();

    @FXML private Text firstColor;
    @FXML private Text secondColor;
    @FXML private Button discardOneButton;
    @FXML private Button discardTwoButton;

    private String spawn = "DISCARD_AND_SPAWN";
    private String duplicated = null;

    /**
     * Set duplicated power up.
     *
     * @param powerUp the power up
     */
    public void setDuplicatedPowerUp(String powerUp){
        duplicated=powerUp;
        loadElements();
    }

    @FXML void firstDiscard(ActionEvent event) throws InterruptedException {

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(duplicated)) {
                if (powerUpData.color.equals(firstColor.getText())) {
                    break;
                }
            }
            powerUpIndex++;
        }
        startMessage.setAction(spawn);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(480);

        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(spawn);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(511);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) discardOneButton.getScene().getWindow();
        stage.close();
    }

    @FXML void secondDiscard(ActionEvent event) throws InterruptedException {

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(duplicated)) {
                if (powerUpData.color.equals(secondColor.getText())) {
                    break;
                }
            }
            powerUpIndex++;
        }
        startMessage.setAction(spawn);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(470);

        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(spawn);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(521);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) discardTwoButton.getScene().getWindow();
        stage.close();
    }

    private void loadElements(){
        int powerUpIndex = 0;

        for (PowerUpData powerUp : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            switch (powerUpIndex) {
                case 0:
                    if (powerUp.name.equals(duplicated)) {
                        firstColor.setText(powerUp.color);
                    }
                    break;
                case 1:
                    if (powerUp.name.equals(duplicated)){
                        secondColor.setText(powerUp.color);
                    }
                    break;
                case 2:
                    if (powerUp.name.equals(duplicated)){
                        System.out.println("3 powerUps of that type");
                    }
                    break;
            }
            powerUpIndex++;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}