package client.views.PowerUps;

import client.views.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.ActionStartMessage;
import messages.client_data.ClientInput;
import messages.client_data.PowerUpData;
import java.net.URL;
import java.util.ResourceBundle;

public class DiscardPowerUpController implements Initializable {

    @FXML private Label powerUpName;
    @FXML private Text yellowPowerUps;
    @FXML private Text redPowerUps;
    @FXML private Text bluePowerUps;
    @FXML private Button discardYellowButton;
    @FXML private Button discardRedButton;
    @FXML private Button discardBlueButton;

    private String powerUpType;
    private  String discard = "DISCARD";

    private ActionStartMessage startMessage = new ActionStartMessage();
    private ActionMessage message = new ActionMessage();
    private ClientInput clientInput = new ClientInput();
    ActionEndMessage endMessage = new ActionEndMessage();

    //This window is only for this type of powerUp, out of 4.
    //This parameter is received from the previous window.
    public void setPowerUpType(String powerUpType) {
        this.powerUpType = powerUpType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Names the window correctly
        switch (powerUpType) {
            case "TagbackGrenade":
                powerUpName.setText("TAGBACK GRENADE");
                break;
            case "Newton":
                powerUpName.setText("NEWTON");
                break;
            case "Teleporter":
                powerUpName.setText("TELEPORTER");
                break;
            case "TargetingScope":
                powerUpName.setText("TARGETING SCOPE");
                break;
        }

        //Initializes powerups to zero
        redPowerUps.setText("0");
        bluePowerUps.setText("0");
        yellowPowerUps.setText("0");

        //Changes the number of available powerups based on gamestate
        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.color.equals("BLUE")) {
                int availableBlue = Integer.parseInt(bluePowerUps.getText());
                availableBlue++;
                bluePowerUps.setText(Integer.toString(availableBlue));
            }
            if (powerUpData.color.equals("RED")) {
                int availableRed = Integer.parseInt(redPowerUps.getText());
                availableRed++;
                redPowerUps.setText(Integer.toString(availableRed));
            }
            if (powerUpData.color.equals("YELLOW")) {
                int availableYellow = Integer.parseInt(yellowPowerUps.getText());
                availableYellow++;
                yellowPowerUps.setText(Integer.toString(availableYellow));
            }
        }
    }

    //Discards the blue powerup. by its index
    @FXML void discardBlue(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(powerUpType)) {
                if (powerUpData.color.equals("BLUE")) {
                    break;
                }
            }
                powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(discard);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(500);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) discardRedButton.getScene().getWindow();
        stage.close();
    }

    @FXML void discardRed(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(powerUpType)) {
                if (powerUpData.color.equals("RED")) {
                    break;
                }
            }
            powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(discard);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(500);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) discardBlueButton.getScene().getWindow();
        stage.close();
    }

    @FXML void discardYellow(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(powerUpType)) {
                if (powerUpData.color.equals("YELLOW")) {
                    break;
                }
            }
            powerUpIndex++;
        }

        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(discard);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(500);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) discardYellowButton.getScene().getWindow();
        stage.close();
    }
}