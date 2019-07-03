package client.views.ActionsControllers;

import client.views.Game;
import client.views.GenericWindows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
import messages.GameStateMessage;
import messages.client_data.ClientInput;
import messages.client_data.SquareData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoveGrabController implements Initializable {

    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button move;
    @FXML private Button grab;
    @FXML private Button confirmButton;

    /**
     * Open grab action window.
     * If there's not a weapon, pick a ammo.
     *
     * @param event the event
     * @throws InterruptedException the interrupted exception
     */
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
            Thread.sleep(501);

            ActionEndMessage endMessage = new ActionEndMessage();
            Game.controller.sendMsg(endMessage);

            Stage stage = (Stage) grab.getScene().getWindow();
            stage.close();
        }
        else if (squares.get(playerPosition).isSpawnPoint)
        {
            genericWindow.weaponGrab();

            Stage stage = (Stage) grab.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Open move action window.
     *
     * @param event the event
     */
    @FXML
    void openMove(ActionEvent event) {
        genericWindow.moveWindow();
        confirmButton.setDisable(false);
    }

    /**
     * Ensures that the player has ended the action he wanted to do.
     */
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