package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import client.views.MarksController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.ActionStartMessage;
import messages.client_data.ClientInput;
import messages.client_data.PowerUpData;
import utils.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SpawnActionController implements Initializable {

    @FXML private Text grenadeColor;
    @FXML private Text scopeColor;
    @FXML private Text newtonColor;
    @FXML private Text teleporterColor;
    @FXML private Button grenade;
    @FXML private Button scope;
    @FXML private Button newton;
    @FXML private Button teleporter;
    @FXML private Label newtonTitle;
    @FXML private Label teleporterTitle;
    @FXML private Label grenadeTitle;
    @FXML private Label scopeTitle;
    @FXML private Button duplicateButton;


    private String grenadePowerup = "TagbackGrenade";
    private String newtonPowerup = "Newton";
    private String teleporterPowerup = "Teleporter";
    private String targetingScope = "TargetingScope";
    private String blueColor = "BLUE";
    private String redColor = "RED";
    private String yellowColor = "YELLOW";
    private String spawn = "DISCARD_AND_SPAWN";
    private String notUsed = "Not Available";
    private String duplicated=null;

    private ActionStartMessage startMessage = new ActionStartMessage();
    private ActionMessage message = new ActionMessage();
    private ActionEndMessage endMessage = new ActionEndMessage();
    private ClientInput clientInput = new ClientInput();
    private GenericWindows window = new GenericWindows();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        grenadeColor.setText(notUsed);
        newtonColor.setText(notUsed);
        teleporterColor.setText(notUsed);
        scopeColor.setText(notUsed);

        grenade.setDisable(true);
        scope.setDisable(true);
        newton.setDisable(true);
        teleporter.setDisable(true);

        int scopeCounter = 0;
        int teleporterCounter = 0;
        int newtonCounter = 0;
        int grenadeCounter = 0;

        //INITIALIZES TEXT COLORS BASED ON POWERUPS
        //Enables buttons only if there is a powerup of that type
        ArrayList<PowerUpData> powerUps = Game.controller.getLastGameStateMessage().playerYouData.powerUps;
        for (PowerUpData powerUp : powerUps){

            if (powerUp.name.equals(grenadePowerup)) {
                grenadeCounter++;
                grenade.setDisable(false);
                if (powerUp.color.equals(blueColor))
                {
                    grenadeColor.setText(blueColor);
                }
                if (powerUp.color.equals(redColor))
                {
                    grenadeColor.setText(redColor);
                }
                if (powerUp.color.equals(yellowColor))
                {
                    grenadeColor.setText(yellowColor);
                }
            }
            if (powerUp.name.equals(newtonPowerup)){
                newtonCounter++;
                newton.setDisable(false);
                if (powerUp.color.equals(blueColor))
                {
                    newtonColor.setText(blueColor);
                }
                if (powerUp.color.equals(redColor))
                {
                    newtonColor.setText(redColor);
                }
                if (powerUp.color.equals(yellowColor))
                {
                    newtonColor.setText(yellowColor);
                }
            }
            if (powerUp.name.equals(teleporterPowerup)){
                teleporterCounter++;
                teleporter.setDisable(false);
                if (powerUp.color.equals(blueColor))
                {
                    teleporterColor.setText(blueColor);
                }
                if (powerUp.color.equals(redColor))
                {
                    teleporterColor.setText(redColor);
                }
                if (powerUp.color.equals(yellowColor))
                {
                    teleporterColor.setText(yellowColor);
                }
            }
            if (powerUp.name.equals(targetingScope)){
                scopeCounter++;
                scope.setDisable(false);
                if (powerUp.color.equals(blueColor))
                {
                    scopeColor.setText(blueColor);
                }
                if (powerUp.color.equals(redColor))
                {
                    scopeColor.setText(redColor);
                }
                if (powerUp.color.equals(yellowColor))
                {
                    scopeColor.setText(yellowColor);
                }
            }
        }
        if (scopeCounter==2||grenadeCounter==2||newtonCounter==2||teleporterCounter==2){
            grenade.setDisable(true);
            newton.setDisable(true);
            scope.setDisable(true);
            teleporter.setDisable(true);
        }
        if (scopeCounter==2){
            duplicated=targetingScope;
        }
        if (grenadeCounter==2){
            duplicated=grenadePowerup;
        }
        if (teleporterCounter==2){
            duplicated=teleporterPowerup;
        }
        if (newtonCounter==2){
            duplicated=newtonPowerup;
        }
    }

    @FXML void openDuplicate(){
        Stage duplicateWindow = new Stage();
        duplicateWindow.initStyle(StageStyle.UNDECORATED);
        duplicateWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/DuplicateSpawn.fxml"));
        try {
            loader.load();
        } catch (Throwable e) {
            Logger.err(e, "Error showing marks");
            window.loadingFailure();
        }
        Parent root = loader.getRoot();
        DuplicateSpawnController controller = loader.getController();
        controller.setDuplicatedPowerUp(duplicated);
        Scene scene = new Scene(root);
        duplicateWindow.setScene(scene);
        duplicateWindow.setResizable(false);
        duplicateWindow.show();
    }

    @FXML void grenadeDiscard(ActionEvent event) throws InterruptedException {

        startMessage.setAction(spawn);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(grenadePowerup)) {
                if (powerUpData.color.equals(grenadeColor.getText())) {
                    break;
                }
            }
            powerUpIndex++;
        }
        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(spawn);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(501);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) grenade.getScene().getWindow();
        stage.close();
    }

    @FXML void newtonDiscard(ActionEvent event) throws InterruptedException {
        startMessage.setAction(spawn);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(490);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(newtonPowerup)) {
                if (powerUpData.color.equals(newtonColor.getText())) {
                    break;
                }
            }
            powerUpIndex++;
        }
        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(spawn);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(499);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) newton.getScene().getWindow();
        stage.close();
    }

    @FXML void scopeDiscard(ActionEvent event) throws InterruptedException {

        startMessage.setAction(spawn);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(510);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(targetingScope)) {
                if (powerUpData.color.equals(scopeColor.getText())) {
                    break;
                }
            }
            powerUpIndex++;
        }
        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(spawn);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(498);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) newton.getScene().getWindow();
        stage.close();
    }

    @FXML void teleporterDiscard(ActionEvent event) throws InterruptedException {

        startMessage.setAction(spawn);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(512);

        int powerUpIndex = 0;

        for (PowerUpData powerUpData : Game.controller.getLastGameStateMessage()
                .playerYouData.powerUps) {
            if (powerUpData.name.equals(teleporterPowerup)) {
                if (powerUpData.color.equals(teleporterColor.getText())) {
                    break;
                }
            }
            powerUpIndex++;
        }
        clientInput.powerUpIndex = powerUpIndex;
        message.setActionItem(spawn);
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(497);

        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) newton.getScene().getWindow();
        stage.close();
    }
}