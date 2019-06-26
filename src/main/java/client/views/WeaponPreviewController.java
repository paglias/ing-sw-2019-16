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

public class WeaponPreviewController implements Initializable {

    private Integer mapSlot;

    public void setSlot(Integer currentSlot) {
        this.mapSlot = currentSlot;
    }

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
    @FXML
    ImageView weaponImage;
    @FXML
    Button closeButton;

    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<WeaponsSlotData> weaponsSlots = new ArrayList<>(
                Game.controller.getLastGameStateMessage().gameBoardData.squares.stream()
                        .filter(s -> s.isSpawnPoint)
                        .map(s -> s.weaponsSlot)
                        .collect(Collectors.toList()));

        for (WeaponsSlotData slot : weaponsSlots) {
            ArrayList<WeaponData> weapons = slot.weapons;

            if (slot.color.equals("RED")) {
                if (!weapons.isEmpty() && mapSlot == 1) {
                    weaponOne = weapons.get(0).name;
                }
                if (weapons.size() > 1 && mapSlot == 2) {
                    weaponTwo = weapons.get(1).name;
                }
                if (weapons.size() > 2 && mapSlot == 3) {
                    weaponThree = weapons.get(2).name;
                }

                else if (slot.color.equals("YELLOW")) {
                    if (!weapons.isEmpty() && mapSlot == 4) {
                        weaponFour = weapons.get(0).name;
                    }
                    if (weapons.size() > 1 && mapSlot == 5) {
                        weaponFive = weapons.get(1).name;
                    }
                    if (weapons.size() > 2 && mapSlot == 6) {
                        weaponSix = weapons.get(2).name;
                    }
                }
                else
                    { // BLUE
                    if (!weapons.isEmpty() && mapSlot == 7) {
                        weaponSeven = weapons.get(0).name;
                    }
                    if (weapons.size() > 1 && mapSlot == 8) {
                        weaponEight = weapons.get(1).name;
                    }
                    if (weapons.size() > 2 && mapSlot == 9) {
                        weaponNine = weapons.get(2).name;
                    }
                }
            }
        }

        String imagePath = "/JPGs/Weapons/";
        int i = mapSlot;
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
