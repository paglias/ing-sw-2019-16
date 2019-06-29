package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.ActionStartMessage;
import messages.client_data.ClientInput;
import messages.client_data.WeaponData;
import utils.Constants;
import utils.Logger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


//Controller for the window that displays the available weapons of the player.
public class AvailableWeaponsController implements Initializable {

    @FXML private Button confirmButton;
    @FXML private ImageView weaponOne;
    @FXML private ImageView weaponTwo;
    @FXML private ImageView weaponThree;

    private String imagePath = "/JPGs/Weapons/";
    private ActionStartMessage startMessage = new ActionStartMessage();
    private ActionMessage message = new ActionMessage();
    private ClientInput clientInput = new ClientInput();
    private ActionEndMessage endMessage = new ActionEndMessage();

    private String discard = "DISCARD";

    private String firstWeapon;
    private String secondWeapon;
    private String thirdWeapon;

    @FXML private Button discardButton1;
    @FXML private Button discardButton2;
    @FXML private Button discardButton3;


    @FXML
    void discardWeaponOne(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(505);

        clientInput.weaponName = firstWeapon;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(502);

        message.setActionItem(discard);
        Game.controller.sendMsg(endMessage);
    }

    @FXML
    void discardWeaponThree(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(501);

        clientInput.weaponName = thirdWeapon;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(504);

        message.setActionItem(discard);
        Game.controller.sendMsg(endMessage);
    }

    @FXML
    void discardWeaponTwo(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(503);

        clientInput.weaponName = secondWeapon;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(508);

        message.setActionItem(discard);
        Game.controller.sendMsg(endMessage);
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage mainWindow;
        mainWindow = (Stage) confirmButton.getScene().getWindow();
        mainWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<WeaponData> availableWeapons = Game.controller.getLastGameStateMessage().playerYouData.weapons;

        if (availableWeapons.isEmpty()) {
            discardButton1.setDisable(true);
            discardButton2.setDisable(true);
            discardButton3.setDisable(true);
        } else {
            int index = 0;
            for (WeaponData weapon : availableWeapons) {
                switch (index) {
                    case 0:
                        firstWeapon = weapon.name;
                        discardButton1.setDisable(false);
                        Image firstImage = new Image(imagePath+firstWeapon);
                        weaponOne.setImage(firstImage);
                        break;
                    case 1:
                        secondWeapon = weapon.name;
                        discardButton2.setDisable(false);
                        Image secondImage = new Image(imagePath+secondWeapon);
                        weaponTwo.setImage(secondImage);
                        break;
                    case 2:
                        thirdWeapon = weapon.name;
                        discardButton3.setDisable(false);
                        Image thirdImage = new Image(imagePath+thirdWeapon);
                        weaponThree.setImage(thirdImage);
                        break;
                }
                index++;
                if (Constants.DEBUG) {
                    Logger.info("Weapons have been successfully loaded");
                }
            }
        }
    }
}