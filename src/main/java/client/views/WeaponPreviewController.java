package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.client_data.PlayerYouData;
import messages.client_data.WeaponData;
import messages.client_data.WeaponsSlotData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

//WeaponSlot controller, loads weapons on window, when the weaponSlot is clicked

public class WeaponPreviewController  {

    public String weaponName;

    public void setWeapon(String weapon) {
        this.weaponName = weapon;
        Image image = new Image("/JPGs/Weapons/" + weaponName + ".png");
        weaponImage.setImage(image);
    }

    //Area that receives the image to be loaded
    @FXML ImageView weaponImage;
    @FXML Button closeButton;

    //Closes the weaponWindow
    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
