package client.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionListController implements Initializable {

    GenericWindows newWindow = new GenericWindows();

    @FXML Button confirmButton;

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML public void move() {
        Stage moveStage = new Stage();
        moveStage.setTitle("ACTION");
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
    @FXML public void moveGrab(){
        Stage moveGrabStage = new Stage();
        moveGrabStage.setTitle("ACTION");
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

    @FXML public void shoot(){
        Stage shoot = new Stage();
        shoot.setTitle("ACTION");
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

    @FXML public void adrenalineGrab(){
        Stage moveAndGrabStage = new Stage();
        moveAndGrabStage.setTitle("ACTION");
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

    @FXML public void adrenalineShoot(){
        Stage moveShootStage = new Stage();
        moveShootStage.setTitle("ACTION");
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

    @FXML public void beforeFrenzyShoot(){
        Stage frenzyStageOne = new Stage();
        frenzyStageOne.setTitle("ACTION");
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

    @FXML public void beforeFrenzyGrab(){
        adrenalineGrab();
    }

    @FXML public void beforeFrenzyMove(){
        Stage frenzyStageFour = new Stage();
        frenzyStageFour.setTitle("ACTION");
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

    @FXML public void afterFrenzyGrab(){
        Stage frenzyStageFive = new Stage();
        frenzyStageFive.setTitle("ACTION");
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

    @FXML public void afterFrenzyShoot(){
        Stage frenzyStageSix = new Stage();
        frenzyStageSix.setTitle("ACTION");
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

    @FXML public void confirmAction(){
        Stage mainWindow;
        mainWindow = (Stage) confirmButton.getScene().getWindow();
        mainWindow.close();
    }
}
