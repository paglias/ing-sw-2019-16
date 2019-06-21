package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionListController implements Initializable {

    @FXML
    Button confirmButton;

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML public void move(){
    }

    @FXML public void moveGrab(){
    }

    @FXML public void shoot(){
    }

    @FXML public void adrenalineGrab(){
    }

    @FXML public void adrenalineShoot(){
    }

    @FXML public void beforeFrenzyShoot(){
    }

    @FXML public void beforeFrenzyGrab(){
    }

    @FXML public void beforeFrenzyMove(){
    }

    @FXML public void afterFrenzyGrab(){
    }

    @FXML public void afterFrenzyShoot(){
    }

    @FXML public void confirmAction(){
        Stage mainWindow;
        mainWindow = (Stage) confirmButton.getScene().getWindow();
        mainWindow.close();
    }
}
