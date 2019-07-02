package client.views.ActionsControllers;

import client.views.Game;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.ActionStartMessage;
import messages.client_data.ClientInput;
import messages.client_data.WeaponData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Reload window. Lets a player select a weapon to reload.
//The available weapons are stored in 3 string attributes that populate the choicebox.
// The attributes must be updated using the available setter methods.

public class GenericReloadController implements Initializable {

    private String weapon1;
    private String weapon2;
    private String weapon3;

    @FXML private Button confirm;
    @FXML private ChoiceBox<String> weaponSelected;


    /**
     * Add weapons to usable weapons.
     *
     * @param location  the location
     * @param resources the resources
     */
    @Override
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

        confirm.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> weaponSelected.getValue() != null, weaponSelected.valueProperty()));
    }

    /**
     * Confirm that weapon is reloaded.
     */
    @FXML void confirmReload () throws InterruptedException{
        ActionStartMessage actionStartMessage = new ActionStartMessage();
        actionStartMessage.setAction("RELOAD");
        Game.controller.sendMsg(actionStartMessage);
        Thread.sleep(563);


        ActionMessage actionMessage = new ActionMessage();
        actionMessage.setActionItem("RELOAD");

        String selection = weaponSelected.getValue();

        ClientInput clientInput = new ClientInput();

        clientInput.weaponName = selection;

        actionMessage.setClientInput(clientInput);
        Game.controller.sendMsg(actionMessage);
        Thread.sleep(567);

        ActionEndMessage actionEndMessage = new ActionEndMessage();
        Game.controller.sendMsg(actionEndMessage);
        Thread.sleep(563);


        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }
}