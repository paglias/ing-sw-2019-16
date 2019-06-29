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


    @FXML void discardWeaponOne(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        clientInput.weaponName=firstWeapon;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(500);

        message.setActionItem(discard);
        Game.controller.sendMsg(endMessage);
    }

    @FXML void discardWeaponThree(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        clientInput.weaponName=thirdWeapon;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(500);

        message.setActionItem(discard);
        Game.controller.sendMsg(endMessage);
    }

    @FXML void discardWeaponTwo(ActionEvent event) throws InterruptedException {

        startMessage.setAction(discard);
        Game.controller.sendMsg(startMessage);
        Thread.sleep(500);

        clientInput.weaponName=secondWeapon;
        message.setClientInput(clientInput);
        Game.controller.sendMsg(message);
        Thread.sleep(500);

        message.setActionItem(discard);
        Game.controller.sendMsg(endMessage);
    }

    @FXML void closeWindow(ActionEvent event) {
            Stage mainWindow;
            mainWindow = (Stage) confirmButton.getScene().getWindow();
            mainWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<WeaponData> availableWeapons = Game.controller.getLastGameStateMessage().playerYouData.weapons;

        firstWeapon = availableWeapons.get(0).name;
        secondWeapon = availableWeapons.get(1).name;
        thirdWeapon = availableWeapons.get(2).name;

        Image firstImage = new Image(imagePath+firstWeapon);
        Image secondImage = new Image(imagePath+secondWeapon);
        Image thirdImage = new Image(imagePath+thirdWeapon);

        weaponOne.setImage(firstImage);
        weaponTwo.setImage(secondImage);
        weaponThree.setImage(thirdImage);

        if (Constants.DEBUG){
            Logger.info("Weapons have successfully loaded");
        }
    }
}
