package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MapController3 implements Initializable {
    GenericWindows genericWindows = new GenericWindows();

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


    /**
     * Quit game.
     */
    @FXML
    public void quitGame(){
        genericWindows.quitGame();
    }


    /**
     * Open action window.
     */
    @FXML
    public void openActionWindow(){
        genericWindows.actionWindow();
    }

    /**
     * Open power ups window.
     */
    @FXML
    public void openPowerUpsWindow(){
    }

    /**
     * Confirm action.
     */
    @FXML
    public void confirmAction(){
    }

    /**
     * Confirm power up.
     */
    @FXML
    public void confirmPowerUp(){
    }

    /**
     * Grab action.
     */
    @FXML
    public void grabAction() {
    }
}