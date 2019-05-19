package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class mapController2 implements Initializable {
    AlertBoxes alertBoxes = new AlertBoxes();

    @FXML
    ImageView image1;
    @FXML
    AnchorPane imageAnchorPane;
    @FXML
    Button quitButton;

    //Sets map size correctly
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image1.fitWidthProperty().bind(imageAnchorPane.widthProperty());
        image1.fitHeightProperty().bind(imageAnchorPane.heightProperty());
    }

    //Show possible settings? //TODO
    @FXML
    public void showSettings(){
        alertBoxes.settings();
    }

    //Alert box for quitting the game
    @FXML
    public void quitGame(){
        alertBoxes.quitGame();
    }

    //Opens a window with the actions available. Many buttons will be disabled.
    @FXML
    public void openActionWindow(){
        alertBoxes.actionWindow();
    }

    @FXML
    public void timerPrint(){
        alertBoxes.timerPrint();
    }

    @FXML
    public void openPowerUpsWindow(){
    }

    @FXML
    public void confirmAction(){
    }

    @FXML
    public void confirmPowerUp(){
    }

    @FXML
    public void grabAction() {
    }
}