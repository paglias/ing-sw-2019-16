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

public class MoveThreeGrabController implements Initializable {
    private GenericWindows genericWindow = new GenericWindows();

    @FXML private Button moveOne;
    @FXML private Button moveThree;
    @FXML private Button grab;
    @FXML private Button moveTwo;
    @FXML private Button continueButton;

    private GameStateMessage gameStateMessage = Game.controller.getLastGameStateMessage();
    private ClientInput clientInput = new ClientInput();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setDisable(true);
        moveTwo.setDisable(true);
        moveThree.setDisable(true);
    }


    @FXML void grab(ActionEvent event) throws InterruptedException {

        int playerPosition = gameStateMessage.playerYouData.position;
        ArrayList<SquareData> squares = gameStateMessage.gameBoardData.squares;

        if (!squares.get(playerPosition).isSpawnPoint) {
            //Send message to server, without a weapon. Ammo will be grabbed
            ActionMessage actionMessage = new ActionMessage();
            actionMessage.setActionItem("GRAB");
            actionMessage.setClientInput(clientInput);
            Game.controller.sendMsg(actionMessage);
            Thread.sleep(499);

            ActionEndMessage endMessage = new ActionEndMessage();
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
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    @FXML void openMoveOne(ActionEvent event) {
        genericWindow.moveWindow();
        moveTwo.setDisable(false);
        moveOne.setDisable(true);
        continueButton.setDisable(false);
    }

    @FXML void openMoveTwo(ActionEvent event) {
        genericWindow.moveWindow();
        moveTwo.setDisable(true);
        moveThree.setDisable(false);
    }

    @FXML void openMoveThree(ActionEvent event) {
        genericWindow.moveWindow();
        moveThree.setDisable(true);
    }
}