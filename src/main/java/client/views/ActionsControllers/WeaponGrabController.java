package client.views.ActionsControllers;

import client.views.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.GameStateMessage;
import messages.client_data.ClientInput;
import messages.client_data.SquareData;
import messages.client_data.WeaponData;
import messages.client_data.WeaponsSlotData;
import utils.Constants;
import utils.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WeaponGrabController implements Initializable {

    private String weaponChosen;

    @FXML private Button confirm;
    @FXML private RadioButton weaponOne;
    @FXML private RadioButton weaponTwo;
    @FXML private RadioButton weaponThree;
    @FXML private ToggleGroup weaponSelected;

    //Sets the selected weapon and sends it to server
    @FXML void confirmGrab(ActionEvent event) throws InterruptedException {

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
        Thread.sleep(500);

        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);

        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GameStateMessage gameStateMessage = Game.controller.getLastGameStateMessage();

        int playerPosition = gameStateMessage.playerYouData.position;
        SquareData square = gameStateMessage.gameBoardData.squares.get(playerPosition);
        if (square.isSpawnPoint) {
            WeaponsSlotData slot = square.weaponsSlot;
            ArrayList<WeaponData> weapons = slot.weapons;

            int wIndex = 0;

            for (WeaponData weapon : weapons) {
                switch (wIndex) {
                    case 0:
                        weaponOne.setText(weapon.name);
                        break;
                    case 1:
                        weaponTwo.setText(weapon.name);
                        break;
                    case 2:
                        weaponThree.setText(weapon.name);
                        break;
                }
                wIndex++;
            }
        }
        else{
            if(Constants.DEBUG)
                Logger.info("it's not a spawnpoint");
        }
    }
}