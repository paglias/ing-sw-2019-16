package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import messages.client_data.WeaponData;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Main shoot window. Lets a player select a weapon and opens a clickable window with the weapon selected.
//The available weapons are stored in 3 string attributes.
// The attributes must be updated using the available setter methods.

public class GenericShootController implements Initializable {

    private GenericWindows newWindow = new GenericWindows();

    @FXML private Button confirm;
    @FXML private ChoiceBox<String> weaponSelected;

    @FXML void confirmShoot(ActionEvent event) {
        String weapon = weaponSelected.getSelectionModel().getSelectedItem();
        newWindow.weaponWindow(weapon);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        confirm.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> weaponSelected.getValue() != null, weaponSelected.valueProperty()));

        //Populate choicebox with available weapons
        ArrayList<WeaponData> weapons = Game.controller.getLastGameStateMessage().playerYouData.weapons;
        ArrayList<String> availableWeapons = new ArrayList<>();
        for (WeaponData weapon : weapons){
            availableWeapons.add(weapon.name);
        }

        ObservableList<String> availableChoices = FXCollections.observableArrayList(availableWeapons);
        weaponSelected.setItems(availableChoices);

        confirm.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> weaponSelected.getValue() != null, weaponSelected.valueProperty()));
    }
}