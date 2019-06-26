package client.views.ActionsControllers;

import client.views.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import messages.ActionMessage;
import messages.client_data.ClientInput;
import java.net.URL;
import java.util.ResourceBundle;

public class WeaponGrabController implements Initializable {

    public String weaponChosen;

    @FXML private Button confirm;
    @FXML private RadioButton weaponOne;
    @FXML private ToggleGroup weaponSelected;
    @FXML private RadioButton weaponTwo;
    @FXML private RadioButton weaponThree;

    //Sets the selected weapon and sends it to server
    @FXML void confirmGrab(ActionEvent event) {

        if (weaponOne.isSelected()){
            weaponChosen =  weaponOne.getText();
        }
        else if (weaponTwo.isSelected()){
            weaponChosen = weaponTwo.getText();
        }
        else if (weaponThree.isSelected()){
            weaponChosen = weaponThree.getText();
        }

        //Send message to server
        ActionMessage actionMessage = new ActionMessage();
        actionMessage.setActionItem("GRAB");
        ClientInput clientInput = new ClientInput();
        clientInput.weaponName = weaponChosen;
        actionMessage.setClientInput(clientInput);
        Game.controller.sendMsg(actionMessage);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO SETTEXT TO weaponOne,Two,Three FROM GAMESTATEMESSAGE. THE WEAPONS ARE THE CURRENT WEAPONSSLOT WEAPONS
        //TODO get weapons from current weaponsslot?
    }
}
