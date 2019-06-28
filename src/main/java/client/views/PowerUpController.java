package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import messages.client_data.PowerUpData;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initializes the values to zero
        scopeAvailability.setText("0");
        teleporterAvailability.setText("0");
        grenadeAvailability.setText("0");
        netwonAvailability.setText("0");

        //Gets the powerUps in playeryoudata
        //increments availability of each powerup based on the powerUps received.
        ArrayList<PowerUpData> powerUps = Game.controller.getLastGameStateMessage().playerYouData.powerUps;
        for (PowerUpData powerUp : powerUps){
            switch (powerUp.name){
                case "Tagback":
                    int availableGrenades = Integer.parseInt(grenadeAvailability.getText());
                    availableGrenades++;
                    grenadeAvailability.setText(Integer.toString(availableGrenades));
                    break;

                case "Newton":
                    int availableNewton = Integer.parseInt(netwonAvailability.getText());
                    availableNewton++;
                    netwonAvailability.setText(Integer.toString(availableNewton));
                    break;

                case "Targeting":
                    int availableScopes = Integer.parseInt(scopeAvailability.getText());
                    availableScopes++;
                    scopeAvailability.setText(Integer.toString(availableScopes));
                    break;

                case "Teleporter":
                    int availableTeleporters = Integer.parseInt(teleporterAvailability.getText());
                    availableTeleporters++;
                    teleporterAvailability.setText(Integer.toString(availableTeleporters));
                    break;
            }
        }
    }

    @FXML void discardGrenade(ActionEvent event) {

    }

    @FXML void discardNewton(ActionEvent event) {

    }

    @FXML
    void discardScope(ActionEvent event) {

    }

    @FXML
    void discardTeleporter(ActionEvent event) {

    }

    @FXML
    void useGrenade(ActionEvent event) {

    }

    @FXML
    void useNewton(ActionEvent event) {

    }

    @FXML
    void useScope(ActionEvent event) {

    }

    @FXML
    void useTeleporter(ActionEvent event) {

    }

}
