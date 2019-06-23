package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

//WeaponSlot controller, loads weapons on window, when the weaponSlot is clicked

public class WeaponPreviewController implements Initializable {

    //TODO LOAD WEAPONS IN WEAPONSLOT
    //TODO to be used in image name to show the correct weapon.
    //TODO temporary code, the correct weapons will be received by a message.

    String weaponName;

    //Area that receives the image to be loaded
    @FXML ImageView weaponImage;
    @FXML Button closeButton;

    public void initialize(URL location, ResourceBundle resources) {
        GenericWindows weaponWindow = new GenericWindows();
        int i = weaponWindow.getCurrentSlot();
        switch (i) {
            case 1:
                Image image = new Image("/JPGs/Weapons/Railgun.png");
                weaponImage.setImage(image);
                break;
            case 2:
                Image image2 = new Image("/JPGs/Weapons/Flamethrower.png");
                weaponImage.setImage(image2);
                break;
            case 3:
                Image image3 = new Image("/JPGs/Weapons/Thor.png");
                weaponImage.setImage(image3);
                break;
            case 4:
                Image image4 = new Image("/JPGs/Weapons/RocketLauncher.png");
                weaponImage.setImage(image4);
                break;
            case 5:
                Image image5 = new Image("/JPGs/Weapons/Shotgun.png");
                weaponImage.setImage(image5);
                break;
            case 6:
                Image image6 = new Image("/JPGs/Weapons/Whisper.png");
                weaponImage.setImage(image6);
                break;
            case 7:
                Image image7 = new Image("/JPGs/Weapons/Shockwave.png");
                weaponImage.setImage(image7);
                break;
            case 8:
                Image image8 = new Image("/JPGs/Weapons/PowerGlove.png");
                weaponImage.setImage(image8);
                break;
            case 9:
                Image image9 = new Image("/JPGs/Weapons/VortexCannon.png");
                weaponImage.setImage(image9);
                break;
        }
    }
    //Closes the weaponWindow
    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
