package client.views.ActionsControllers;

import client.views.GenericWindows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Main shoot window. Lets a player select a weapon and opens a clickable window with the weapon selected.
//The available weapons are stored in 3 string attributes.
// The attributes must be updated using the available setter methods.

public class GenericShootController {

    String weapon1;
    String weapon2;
    String weapon3;

    GenericWindows newWindow = new GenericWindows();

    @FXML private Button confirm;

    @FXML private ChoiceBox<String> weaponSelected;

    @FXML
    void confirmShoot(ActionEvent event) {
        String weapon = weaponSelected.getSelectionModel().getSelectedItem();
        newWindow.weaponWindow(weapon);
    }

    public void initialize(URL location, ResourceBundle resources) {

        //Populate choicebox with available weapons
        ArrayList<String> weapons= new ArrayList();
        weapons.add(weapon1);
        weapons.add(weapon2);
        weapons.add(weapon3);
        ObservableList<String> availableChoices = FXCollections.observableArrayList(
                weapons.get(0),
                weapons.get(1),
                weapons.get(2));
        weaponSelected.setItems(availableChoices);
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
