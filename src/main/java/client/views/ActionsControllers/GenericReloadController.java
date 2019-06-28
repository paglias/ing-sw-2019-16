package client.views.ActionsControllers;

import client.views.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import messages.ActionMessage;
import messages.client_data.ClientInput;
import messages.client_data.WeaponData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Reload window. Lets a player select a weapon to reload.
//The available weapons are stored in 3 string attributes that populate the choicebox.
// The attributes must be updated using the available setter methods.

public class GenericReloadController {

    String weapon1;
    String weapon2;
    String weapon3;

    @FXML private Button confirm;

    @FXML private ChoiceBox<String> weaponSelected;


    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<WeaponData> weapons = Game.controller.getLastGameStateMessage().playerYouData.weapons;
        int weaponIndex = 0;
        if (!weapons.isEmpty()) {
            for (WeaponData weapon : weapons) {
                switch (weaponIndex) {
                    case 0:
                        weapon1 = weapon.name;
                        break;
                    case 1:
                        weapon2 = weapon.name;
                        break;
                    case 2:
                        weapon3 = weapon.name;
                        break;
                }
                weaponIndex++;
            }
        }

        weaponSelected.getItems().add(weapon1);
        weaponSelected.getItems().add(weapon2);
        weaponSelected.getItems().add(weapon3);
    }

    @FXML void confirmReload(){
        ActionMessage actionMessage = new ActionMessage();
        actionMessage.setActionItem("RELOAD");

        String selection = weaponSelected.getValue();

        ClientInput clientInput = new ClientInput();

        clientInput.weaponName = selection;

        actionMessage.setClientInput(clientInput);
        Game.controller.sendMsg(actionMessage);
    }
}
