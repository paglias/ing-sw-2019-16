package client.views.ActionsControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

//Main shoot window. Lets player select a weapon and opens a clickable window with the weapon selected.
//The available weapons are stored in 3 string attributes.
// The attributes must be updated using the available setter methods.

public class GenericShootController {

    String weapon1;
    String weapon2;
    String weapon3;

    @FXML private Button confirm;

    @FXML private ChoiceBox<String> weaponSelected;

    @FXML
    void confirmShoot(ActionEvent event) {
        //TODO OPEN CLICKABLE WINDOW IMAGE
    }

    public void initialize(URL location, ResourceBundle resources) {
        weaponSelected.getItems().add(weapon1);
        weaponSelected.getItems().add(weapon2);
        weaponSelected.getItems().add(weapon3);
    }

    public void setWeapon1(String weapon1) {
        this.weapon1 = weapon1;
    }

    public void setWeapon2(String weapon2) {
        this.weapon2 = weapon2;
    }

    public void setWeapon3(String weapon3) {
        this.weapon3 = weapon3;
    }
}
