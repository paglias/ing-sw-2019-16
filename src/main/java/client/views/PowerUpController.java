package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messages.client_data.PowerUpData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PowerUpController implements Initializable {

    @FXML private Button confirmNewton;
    @FXML private Button confirmGrenade;
    @FXML private Button confirmTeleporter;
    @FXML private Button confirmScope;
    @FXML private Text netwonAvailability;
    @FXML private Text grenadeAvailability;
    @FXML private Text teleporterAvailability;
    @FXML private Text scopeAvailability;
    @FXML private Button discardNewtonButton;
    @FXML private Button discardGrenadeButton;
    @FXML private Button discardTeleporterButton;
    @FXML private Button discardScopeButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initializes the values to zero, disables buttons
        scopeAvailability.setText("0");
        teleporterAvailability.setText("0");
        grenadeAvailability.setText("0");
        netwonAvailability.setText("0");

        confirmTeleporter.setDisable(true);
        confirmScope.setDisable(true);
        confirmGrenade.setDisable(true);
        confirmNewton.setDisable(true);

        discardGrenadeButton.setDisable(true);
        discardNewtonButton.setDisable(true);
        discardScopeButton.setDisable(true);
        discardTeleporterButton.setDisable(true);

        //Gets the powerUps in playeryoudata
        //increments availability of each powerup based on the powerUps received.
        ArrayList<PowerUpData> powerUps = Game.controller.getLastGameStateMessage().playerYouData.powerUps;
        for (PowerUpData powerUp : powerUps){
            switch (powerUp.name){
                case "Tagback":
                    int availableGrenades = Integer.parseInt(grenadeAvailability.getText());
                    availableGrenades++;
                    grenadeAvailability.setText(Integer.toString(availableGrenades));
                    confirmGrenade.setDisable(false);
                    discardGrenadeButton.setDisable(false);
                    break;

                case "PowerUps":
                    int availableNewton = Integer.parseInt(netwonAvailability.getText());
                    availableNewton++;
                    netwonAvailability.setText(Integer.toString(availableNewton));
                    confirmNewton.setDisable(false);
                    discardNewtonButton.setDisable(false);
                    break;

                case "Targeting":
                    int availableScopes = Integer.parseInt(scopeAvailability.getText());
                    availableScopes++;
                    scopeAvailability.setText(Integer.toString(availableScopes));
                    confirmScope.setDisable(false);
                    discardScopeButton.setDisable(false);
                    break;

                case "Teleporter":
                    int availableTeleporters = Integer.parseInt(teleporterAvailability.getText());
                    availableTeleporters++;
                    teleporterAvailability.setText(Integer.toString(availableTeleporters));
                    confirmTeleporter.setDisable(false);
                    discardTeleporterButton.setDisable(false);
                    break;
            }
        }
    }
    Stage powerUpWindow = new Stage();
    GenericWindows window = new GenericWindows();


    @FXML void discardGrenade(ActionEvent event) {
        String s = "Tagback";
    }

    @FXML void discardNewton(ActionEvent event) {
        String s = "PowerUps";

    }

    @FXML void discardScope(ActionEvent event) {
        String s = "Targeting";
    }

    @FXML void discardTeleporter(ActionEvent event) {
        String s = "Teleporter";
    }

    @FXML void useGrenade(ActionEvent event) {
        powerUpWindow.setTitle("GRENADE POWERUP");
        powerUpWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PowerUps/TagbackGrenade.fxml"));
            Scene scene = new Scene(root);
            powerUpWindow.setScene(scene);
            powerUpWindow.show();
            powerUpWindow.setResizable(false);
        } catch (IOException e) {
            window.loadingFailure();
        }
    }

    @FXML void useNewton(ActionEvent event) {
        powerUpWindow.setTitle("NEWTON POWERUP");
        powerUpWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PowerUps/Newton.fxml"));
            Scene scene = new Scene(root);
            powerUpWindow.setScene(scene);
            powerUpWindow.show();
            powerUpWindow.setResizable(false);
        } catch (IOException e) {
            window.loadingFailure();
        }
    }

    @FXML void useScope(ActionEvent event) {
            powerUpWindow.setTitle("TARGETING SCOPE POWERUP");
            powerUpWindow.initModality(Modality.APPLICATION_MODAL);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PowerUps/TargetingScope.fxml"));
                Scene scene = new Scene(root);
                powerUpWindow.setScene(scene);
                powerUpWindow.show();
                powerUpWindow.setResizable(false);
            } catch (IOException e) {
                window.loadingFailure();
        }
    }

    @FXML void useTeleporter(ActionEvent event) {
            powerUpWindow.setTitle("TELEPORTER POWERUP");
            powerUpWindow.initModality(Modality.APPLICATION_MODAL);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PowerUps/Teleporter.fxml"));
                Scene scene = new Scene(root);
                powerUpWindow.setScene(scene);
                powerUpWindow.show();
                powerUpWindow.setResizable(false);
            } catch (IOException e) {
                window.loadingFailure();
        }
    }
}
