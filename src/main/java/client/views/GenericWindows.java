package client.views;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Optional;

public class GenericWindows {

    private static int currentPlayer;
    private static int currentSlot;

    public int getCurrentSlot(){
        return currentSlot;
    }
    public void setCurrentSlot(int n){
        this.currentSlot = n;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int Player) {
        this.currentPlayer = Player;
    }

    public void quitGame() {
        Alert quitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        quitAlert.setTitle("Quit Game");
        quitAlert.setHeaderText("Quitting Adrenaline.");
        quitAlert.setContentText("Are you sure you want to quit the game?");

        Optional<ButtonType> result = quitAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            quitAlert.close();
        }
    }

    public void noMapChosen(){
        Alert mapAlert = new Alert(Alert.AlertType.WARNING);
        mapAlert.setTitle("Warning Dialog");
        mapAlert.setHeaderText("There was an error choosing the map.");
        mapAlert.setContentText("Please choose a map before continuing.");
        mapAlert.showAndWait();
    }

    public void actionWindow() {
        Stage actionWindow = new Stage();
        actionWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionWindow.fxml"));
            Scene actionListView = new Scene(root);
            actionWindow.setScene(actionListView);
            actionWindow.show();
            actionWindow.setResizable(false);
        } catch (IOException e) {
            loadingFailure();
        }
    }

    public void loadingFailure() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loading error");
        alert.setHeaderText("Fatal error while loading the FXML file");
        alert.setContentText("FXML loading error, check the presence of the FXML");
        alert.showAndWait();
    }

    //Shows a window with the current marks
    public void showMarks(int playerNumber){
        Stage marksWindow = new Stage();
        marksWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            setCurrentPlayer(playerNumber);
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/Marks.fxml"));
            Scene actionListView = new Scene(root);
            marksWindow.setScene(actionListView);
            marksWindow.show();
            marksWindow.setResizable(false);
        } catch (IOException e) {
            loadingFailure();
        }
    }

    //Shows a window with the weapon contained in the weaponSlot.
    //The weapon is loaded in WeaponController
    public void showWeapon(int currentSlot){
        setCurrentSlot(currentSlot);
        Stage weaponWindow = new Stage(StageStyle.UNDECORATED);
        weaponWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/WeaponWindow.fxml"));
            Scene scene = new Scene(root);
            weaponWindow.setScene(scene);
            weaponWindow.showAndWait();
        }
        catch (IOException e){
            loadingFailure();
        }
    }
}