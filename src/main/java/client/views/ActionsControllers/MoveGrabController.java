package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import client.views.WeaponController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messages.ActionMessage;
import messages.GameStateMessage;
import messages.client_data.ClientInput;
import messages.client_data.SquareData;
import models.Square;
import utils.Logger;

import java.util.ArrayList;

public class MoveGrabController {

    GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;
    @FXML private Button grab;

    @FXML void openGrab (ActionEvent event) {
        GameStateMessage gameStateMessage = Game.controller.getLastGameStateMessage();

        int playerPosition = gameStateMessage.playerYouData.position;
        ArrayList<SquareData> squares = gameStateMessage.gameBoardData.squares;

        if (!squares.get(playerPosition).isSpawnPoint) {
            //Send message to server, without a weapon. Ammo will be grabbed
            ActionMessage actionMessage = new ActionMessage();
            actionMessage.setActionItem("GRAB");
            ClientInput clientInput = new ClientInput();
            actionMessage.setClientInput(clientInput);
            Game.controller.sendMsg(actionMessage);
        } else {
            //Create new window, let user choose which weapon
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/WeaponGrab.fxml"));
            try {
                Loader.load();
            } catch (Throwable e) {
                Logger.err(e, null);
                genericWindow.loadingFailure();
            }

            Parent root = Loader.getRoot();
            Stage weaponChooserStage = new Stage();
            weaponChooserStage.setTitle("CHOOSE A WEAPON");
            weaponChooserStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            weaponChooserStage.setScene(scene);
            weaponChooserStage.show();
        }
    }

    @FXML void openMove(ActionEvent event) {
        genericWindow.moveWindow();
    }
}