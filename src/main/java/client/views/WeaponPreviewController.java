package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;




public class WeaponPreviewController  {

    public String weaponName;

    /**
     * Sets weapon images.
     *
     * @param weapon the weapon
     */
    public void setWeapon(String weapon) {
        this.weaponName = weapon;
        Image image = new Image("/JPGs/Weapons/" + weaponName + ".png");
        weaponImage.setImage(image);
    }

    //Area that receives the image to be loaded
    @FXML ImageView weaponImage;
    @FXML Button closeButton;

    /**
     * Close weapon window.
     */
    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
