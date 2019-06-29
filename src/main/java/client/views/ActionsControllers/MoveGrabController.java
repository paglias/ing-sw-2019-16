package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.GameStateMessage;
import messages.client_data.ClientInput;
import messages.client_data.SquareData;
import utils.Logger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoveGrabController implements Initializable {

    private GenericWindows genericWindow = new GenericWindows();

    @FXML
    private Button move;
    @FXML
    private Button grab;
    @FXML
    private Button confirmButton;

    @FXML void openGrab(ActionEvent event) throws InterruptedException {
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
            Thread.sleep(500);

            ActionEndMessage endMessage = new ActionEndMessage();
            Game.controller.sendMsg(endMessage);

            Stage stage = (Stage) grab.getScene().getWindow();
            stage.close();
        } else {
            //Create new window, let user choose which weapon
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/WeaponGrab.fxml"));
            try {
                loader.load();
            } catch (Throwable e) {
                Logger.err(e, null);
                genericWindow.loadingFailure();
            }

            Parent root = loader.getRoot();
            Stage weaponChooserStage = new Stage();
            weaponChooserStage.setTitle("CHOOSE A WEAPON");
            weaponChooserStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            weaponChooserStage.setScene(scene);
            weaponChooserStage.show();

            Stage stage = (Stage) grab.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void openMove(ActionEvent event) {
        genericWindow.moveWindow();
        confirmButton.setDisable(false);
    }

    @FXML
    void confirmChoice() {
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) move.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmButton.setDisable(true);
    }
}