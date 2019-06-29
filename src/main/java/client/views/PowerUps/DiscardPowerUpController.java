package client.views.PowerUps;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class DiscardPowerUpController implements Initializable {

    @FXML
    private Label powerUpName;
    @FXML
    private Text yellowPowerUps;
    @FXML
    private Text redPowerUps;
    @FXML
    private Text bluePowerUps;
    @FXML
    private Button discardYellowButton;
    @FXML
    private Button discardRedButton;
    @FXML
    private Button discardBlueButton;

    private String powerUpType;

    public void setPowerUpType(String powerUpType) {
        this.powerUpType = powerUpType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML void discardBlue(ActionEvent event) {

    }

    @FXML void discardRed(ActionEvent event) {

    }

    @FXML void discardYellow(ActionEvent event) {
    }
}
