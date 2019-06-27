package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.client_data.WeaponData;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AvailableWeaponsController implements Initializable {

    @FXML private Button confirmButton;

    @FXML private ImageView weaponOne;

    @FXML private ImageView weaponTwo;

    @FXML private ImageView weaponThree;

    private String imagePath = "/JPGs/Weapons/";

    @FXML void closeWindow(ActionEvent event) {
            Stage mainWindow;
            mainWindow = (Stage) confirmButton.getScene().getWindow();
            mainWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<WeaponData> availableWeapons = Game.controller.getLastGameStateMessage().playerYouData.weapons;

        String firstWeapon = availableWeapons.get(0).name;
        String secondWeapon = availableWeapons.get(1).name;
        String thirdWeapon = availableWeapons.get(2).name;

        Image firstImage = new Image(imagePath+firstWeapon);
        Image secondImage = new Image(imagePath+secondWeapon);
        Image thirdImage = new Image(imagePath+thirdWeapon);

        weaponOne.setImage(firstImage);
        weaponTwo.setImage(secondImage);
        weaponThree.setImage(thirdImage);

        System.out.println("Weapons loaded correctly");
    }
}
