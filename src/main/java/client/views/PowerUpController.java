package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class PowerUpController implements Initializable {

    @FXML private Text newtonAvailable;
    @FXML private Text grenadeAvailable;
    @FXML private Text teleporterAvailable;
    @FXML private Text scopeAvailable;
    @FXML private Button confirmNewton;
    @FXML private Button confirmGrenade;
    @FXML private Button confirmTeleporter;
    @FXML private Button confirmScope;

    @FXML void useGrenade(ActionEvent event) {
        //TODO IMPLEMENT
        Stage mainWindow;
        mainWindow = (Stage) confirmGrenade.getScene().getWindow();
        mainWindow.close();
    }

    @FXML void useNewton(ActionEvent event) {
        //TODO IMPLEMENT
        Stage mainWindow;
        mainWindow = (Stage) confirmNewton.getScene().getWindow();
        mainWindow.close();
    }

    @FXML void useScope(ActionEvent event) {
        //TODO IMPLEMENT
        Stage mainWindow;
        mainWindow = (Stage) confirmScope.getScene().getWindow();
        mainWindow.close();
    }

    @FXML void useTeleporter(ActionEvent event) {
        //TODO IMPLEMENT
        Stage mainWindow;
        mainWindow = (Stage) confirmTeleporter.getScene().getWindow();
        mainWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        assert newtonAvailable != null : "fx:id=\"newtonAvailable\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert grenadeAvailable != null : "fx:id=\"grenadeAvailable\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert teleporterAvailable != null : "fx:id=\"teleporterAvailable\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert scopeAvailable != null : "fx:id=\"scopeAvailable\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert confirmNewton != null : "fx:id=\"confirmNewton\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert confirmGrenade != null : "fx:id=\"confirmGrenade\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert confirmTeleporter != null : "fx:id=\"confirmTeleporter\" was not injected: check your FXML file 'PowerUps.fxml'.";
        assert confirmScope != null : "fx:id=\"confirmScope\" was not injected: check your FXML file 'PowerUps.fxml'.";
    }
}
