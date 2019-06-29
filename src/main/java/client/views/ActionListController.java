package client.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messages.ActionStartMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionListController implements Initializable {

    GenericWindows newWindow = new GenericWindows();

    /**
     * The Confirm button.
     */
    @FXML Button confirmButton;
    private  String action = "ACTION";

    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Move.
     */
    @FXML public void move() {

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE");
        Game.controller.sendMsg(message);

        Stage moveStage = new Stage();
        moveStage.setTitle(action);
        moveStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveThree.fxml"));
            Scene scene = new Scene(root);
            moveStage.setScene(scene);
            moveStage.show();
            moveStage.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Move grab.
     */
    @FXML public void moveGrab(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_GRAB");
        Game.controller.sendMsg(message);

        Stage moveGrabStage = new Stage();
        moveGrabStage.setTitle(action);
        moveGrabStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveGrab.fxml"));
            Scene scene = new Scene(root);
            moveGrabStage.setScene(scene);
            moveGrabStage.show();
            moveGrabStage.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Shoot.
     */
    @FXML public void shoot(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("SHOOT");
        Game.controller.sendMsg(message);

        Stage shoot = new Stage();
        shoot.setTitle(action);
        shoot.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/GenericShoot.fxml"));
            Scene scene = new Scene(root);
            shoot.setScene(scene);
            shoot.show();
            shoot.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Adrenaline grab.
     */
    @FXML public void adrenalineGrab(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_MOVE_GRAB");
        Game.controller.sendMsg(message);

        Stage moveAndGrabStage = new Stage();
        moveAndGrabStage.setTitle(action);
        moveAndGrabStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveTwiceGrab.fxml"));
            Scene scene = new Scene(root);
            moveAndGrabStage.setScene(scene);
            moveAndGrabStage.show();
            moveAndGrabStage.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Adrenaline shoot.
     */
    @FXML public void adrenalineShoot(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_SHOOT");
        Game.controller.sendMsg(message);

        Stage moveShootStage = new Stage();
        moveShootStage.setTitle(action);
        moveShootStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveAndShoot.fxml"));
            Scene scene = new Scene(root);
            moveShootStage.setScene(scene);
            moveShootStage.show();
            moveShootStage.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Before frenzy shoot.
     */
    @FXML public void beforeFrenzyShoot(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_RELOAD_SHOOT");
        Game.controller.sendMsg(message);

        Stage frenzyStageOne = new Stage();
        frenzyStageOne.setTitle(action);
        frenzyStageOne.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveReloadShoot.fxml"));
            Scene scene = new Scene(root);
            frenzyStageOne.setScene(scene);
            frenzyStageOne.show();
            frenzyStageOne.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Before frenzy grab.
     */
    @FXML public void beforeFrenzyGrab(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_MOVE_GRAB");
        Game.controller.sendMsg(message);

        adrenalineGrab();
    }

    /**
     * Before frenzy move.
     */
    @FXML public void beforeFrenzyMove(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("FOUR_MOVE");
        Game.controller.sendMsg(message);

        Stage frenzyStageFour = new Stage();
        frenzyStageFour.setTitle(action);
        frenzyStageFour.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveFour.fxml"));
            Scene scene = new Scene(root);
            frenzyStageFour.setScene(scene);
            frenzyStageFour.show();
            frenzyStageFour.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * After frenzy grab.
     */
    @FXML public void afterFrenzyGrab(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("THREE_MOVE_GRAB");
        Game.controller.sendMsg(message);

        Stage frenzyStageFive = new Stage();
        frenzyStageFive.setTitle(action);
        frenzyStageFive.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveThreeGrab.fxml"));
            Scene scene = new Scene(root);
            frenzyStageFive.setScene(scene);
            frenzyStageFive.show();
            frenzyStageFive.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * After frenzy shoot.
     */
    @FXML public void afterFrenzyShoot(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_MOVE_RELOAD_SHOOT");
        Game.controller.sendMsg(message);

        Stage frenzyStageSix = new Stage();
        frenzyStageSix.setTitle(action);
        frenzyStageSix.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/MoveTwoReloadShoot.fxml"));
            Scene scene = new Scene(root);
            frenzyStageSix.setScene(scene);
            frenzyStageSix.show();
            frenzyStageSix.setResizable(false);
        } catch (IOException e) {
            newWindow.loadingFailure();
        }
    }

    /**
     * Confirm action.
     */
    @FXML public void confirmAction(){
        Stage mainWindow;
        mainWindow = (Stage) confirmButton.getScene().getWindow();
        mainWindow.close();
    }
}