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
//TODO SET THE STRINGS BASED ON GAMESTATEMESSAGE FROM MAPCONTROLLER

public class WeaponPreviewController implements Initializable {

    public String weaponOne;
    public String weaponTwo;
    public String weaponThree;
    public String weaponFour;
    public String weaponFive;
    public String weaponSix;
    public String weaponSeven;
    public String weaponEight;
    public String weaponNine;

    //Area that receives the image to be loaded
    @FXML ImageView weaponImage;
    @FXML Button closeButton;

    public void initialize(URL location, ResourceBundle resources) {
        GenericWindows weaponWindow = new GenericWindows();
        String imagePath = "/JPGs/Weapons/";
        int i = weaponWindow.getCurrentSlot();
        switch (i) {
            case 1:
                Image image = new Image(imagePath+weaponOne+".png");
                weaponImage.setImage(image);
                break;
            case 2:
                Image image2 = new Image(imagePath+weaponTwo+".png");
                weaponImage.setImage(image2);
                break;
            case 3:
                Image image3 = new Image(imagePath+weaponThree+".png");
                weaponImage.setImage(image3);
                break;
            case 4:
                Image image4 = new Image(imagePath+weaponFour+".png");
                weaponImage.setImage(image4);
                break;
            case 5:
                Image image5 = new Image(imagePath+weaponFive+".png");
                weaponImage.setImage(image5);
                break;
            case 6:
                Image image6 = new Image(imagePath+weaponSix+".png");
                weaponImage.setImage(image6);
                break;
            case 7:
                Image image7 = new Image(imagePath+weaponSeven+".png");
                weaponImage.setImage(image7);
                break;
            case 8:
                Image image8 = new Image(imagePath+weaponEight+".png");
                weaponImage.setImage(image8);
                break;
            case 9:
                Image image9 = new Image(imagePath+weaponNine+".png");
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
