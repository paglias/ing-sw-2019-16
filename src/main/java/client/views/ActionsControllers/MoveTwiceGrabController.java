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

public class MoveTwiceGrabController implements Initializable {
    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;
    @FXML private Button moveTwo;
    @FXML private Button grab;
    @FXML private Button continueButton;

    private ActionMessage message = new ActionMessage();
    private ClientInput clientInput = new ClientInput();
    private ActionEndMessage endMessage = new ActionEndMessage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setDisable(true);
        moveTwo.setDisable(true);
    }

    @FXML void grab(ActionEvent event) throws InterruptedException {

        GameStateMessage gameStateMessage = Game.controller.getLastGameStateMessage();

        int playerPosition = gameStateMessage.playerYouData.position;
        ArrayList<SquareData> squares = gameStateMessage.gameBoardData.squares;

        if (!squares.get(playerPosition).isSpawnPoint) {

            //Send message to server, without a weapon. Ammo will be grabbed
            message.setActionItem("GRAB");
            message.setClientInput(clientInput);
            Game.controller.sendMsg(message);
            Thread.sleep(500);

            Game.controller.sendMsg(endMessage);

            Stage stage = (Stage) grab.getScene().getWindow();
            stage.close();

        } else {
            genericWindow.weaponGrab();

            Stage stage = (Stage) grab.getScene().getWindow();
            stage.close();
        }
    }

    @FXML void confirmAction(ActionEvent event) {
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();

    }

    @FXML void openMoveOne(ActionEvent event) {
        genericWindow.moveWindow();
        moveOne.setDisable(true);
        moveTwo.setDisable(false);
        continueButton.setDisable(false);
    }

    @FXML void openMoveTwo(ActionEvent event) {
        genericWindow.moveWindow();
        moveTwo.setDisable(true);
    }
}
