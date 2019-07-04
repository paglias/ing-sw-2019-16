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
import java.util.List;
import java.util.ResourceBundle;

public class ActionListController implements Initializable {

    private GenericWindows newWindow = new GenericWindows();

    @FXML Button confirmButton;
    @FXML private Button moveButton;
    @FXML private Button moveGrabButton;
    @FXML private Button shootButton;
    @FXML private Button adrenalineGrabButton;
    @FXML private Button adrenalineShootButton;
    @FXML private Button beforeFrenzyShootButton;
    @FXML private Button beforeFrenzyMoveButton;
    @FXML private Button beforeFrenzyGrabButton;
    @FXML private Button afterFrenzyGrabButton;
    @FXML private Button afterFrenzyShootButton;

    private  String action = "ACTION";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        moveButton.setDisable(true);
        moveGrabButton.setDisable(true);
        shootButton.setDisable(true);

        adrenalineGrabButton.setDisable(true);
        adrenalineShootButton.setDisable(true);

        beforeFrenzyShootButton.setDisable(true);
        beforeFrenzyMoveButton.setDisable(true);
        beforeFrenzyGrabButton.setDisable(true);

        afterFrenzyGrabButton.setDisable(true);
        afterFrenzyShootButton.setDisable(true);

        List<String> availableActions =  Game.controller.getLastGameStateMessage().playerYouData.possibleActions;
        if (availableActions.contains("MOVE")){
            moveButton.setDisable(false);
        }
        if (availableActions.contains("MOVE_GRAB")){
            moveGrabButton.setDisable(false);
        }
        if (availableActions.contains("SHOOT")){
            shootButton.setDisable(false);
        }
        if (availableActions.contains("MOVE_MOVE_GRAB")){
            adrenalineGrabButton.setDisable(false);
        }
        if (availableActions.contains("MOVE_SHOOT")){
            adrenalineShootButton.setDisable(false);
        }
        if (availableActions.contains("MOVE_RELOAD_SHOOT")){
            beforeFrenzyShootButton.setDisable(false);
        }
        if (availableActions.contains("FOUR_MOVE")){
            beforeFrenzyMoveButton.setDisable(false);
        }
        if (availableActions.contains("MOVE_MOVE_GRAB") &&
                Game.controller.getLastGameStateMessage().gameBoardData.isFinalFrenzy){
            beforeFrenzyGrabButton.setDisable(false);
        }
        if (availableActions.contains("MOVE_MOVE_RELOAD_SHOOT")){
            afterFrenzyShootButton.setDisable(false);
        }
        if (availableActions.contains("THREE_MOVE_GRAB")){
            afterFrenzyGrabButton.setDisable(false);
        }
    }

    /**
     * Move action for available actions.
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
        moveGrabButton.setDisable(true);
        shootButton.setDisable(true);
    }

    /**
     * Add window for move/grab action.
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
        moveButton.setDisable(true);
        shootButton.setDisable(true);
    }

    /**
     * Window for shoot action.
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
        moveButton.setDisable(true);
        moveGrabButton.setDisable(true);
    }

    /**
     * When in adrenaline mode, show window for adrenaline grab action.
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
        adrenalineShootButton.setDisable(true);
    }

    /**
     * When in adrenaline mode, show window for adrenaline grab action..
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
        adrenalineGrabButton.setDisable(true);
    }

    /**
     * Final frenzy mode, add window for move/reload/shoot in final frenzy if before first player.
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
        beforeFrenzyMoveButton.setDisable(true);
        beforeFrenzyGrabButton.setDisable(true);
    }

    /**
     *  Final frenzy mode, add window for move/move/grab in final frenzy if before first player..
     */
    @FXML public void beforeFrenzyGrab(){

        ActionStartMessage message = new ActionStartMessage();
        message.setAction("MOVE_MOVE_GRAB");
        Game.controller.sendMsg(message);

        adrenalineGrab();
        beforeFrenzyMoveButton.setDisable(true);
        beforeFrenzyShootButton.setDisable(true);
    }

    /**
     *  Final frenzy mode, add window for four moves in final frenzy if before first player.
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
        beforeFrenzyShootButton.setDisable(true);
        beforeFrenzyGrabButton.setDisable(true);
    }

    /**
     *  Final frenzy mode, add window for three moves grab in final frenzy if after first player..
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
        afterFrenzyShootButton.setDisable(true);
    }

    /**
     *  Final frenzy mode, add window for move/move/reload/shoot in final frenzy if after first player.
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
        afterFrenzyGrabButton.setDisable(true);
    }

    /**
     * Confirm action window.
     */
    @FXML public void confirmAction(){
        Stage mainWindow;
        mainWindow = (Stage) confirmButton.getScene().getWindow();
        mainWindow.close();
    }
}