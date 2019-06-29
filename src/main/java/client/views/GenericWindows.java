package client.views;

import client.views.PowerUps.DiscardPowerUpController;
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

    public int getCurrentSlot() {
        return currentSlot;
    }

    public void setCurrentSlot(int n) {
        this.currentSlot = n;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int Player) {
        this.currentPlayer = Player;
    }

    /**
     * Quit game.
     */
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

    /**
     * No map chosen.
     */
    public void noMapChosen() {
        Alert mapAlert = new Alert(Alert.AlertType.WARNING);
        mapAlert.setTitle("Warning Dialog");
        mapAlert.setHeaderText("There was an error choosing the map.");
        mapAlert.setContentText("Please choose a map before continuing.");
        mapAlert.showAndWait();
    }

    /**
     * Action window.
     */
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

    /**
     * Loading failure.
     */
    public void loadingFailure() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loading error");
        alert.setHeaderText("Fatal error while loading the FXML file");
        alert.setContentText("FXML loading error, check the presence of the FXML");
        alert.showAndWait();
    }

    /**
     * Show marks.
     *
     * @param playerNumber the player number
     */
//Shows a window with the current marks
    public void showMarks(int playerNumber) {
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

    /**
     * Show weapon.
     *
     * @param currentSlot the current slot
     */
//Shows a window with the weapon contained in the weaponSlot.
    //The weapon is loaded in WeaponPreviewController
    public void showWeapon(int currentSlot) {
        Stage weaponWindow = new Stage(StageStyle.UNDECORATED);
        weaponWindow.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/FXMLs//WeaponWindow.fxml"));
        try {
            Loader.load();
        } catch (
                IOException e) {
            loadingFailure();
        }
        WeaponPreviewController controller = Loader.getController();
        controller.setSlot(currentSlot);
        Parent root = Loader.getRoot();
        Scene scene = new Scene(root);
        weaponWindow.setScene(scene);
        weaponWindow.showAndWait();
        weaponWindow.setResizable(false);
    }

    /**
     * Power ups.
     */
    public void powerUps() {
        Stage powerUpWindow = new Stage();
        powerUpWindow.setTitle("Available PowerUps");
        powerUpWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PowerUps.fxml"));
            Scene scene = new Scene(root);
            powerUpWindow.setScene(scene);
            powerUpWindow.show();
            powerUpWindow.setResizable(false);
        } catch (IOException e) {
            loadingFailure();
        }
    }

    /**
     * Move window.
     */
    public void moveWindow() {
        Stage moveStage = new Stage();
        moveStage.setTitle("Move action");
        moveStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/GenericMove.fxml"));
            Scene scene = new Scene(root);
            moveStage.setScene(scene);
            moveStage.show();
            moveStage.setResizable(false);
        } catch (
                IOException e) {
            loadingFailure();
        }
    }

    /**
     * Shoot window.
     */
    public void shootWindow() {
        Stage moveStage = new Stage();
        moveStage.setTitle("Shoot action");
        moveStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/GenericShoot.fxml"));
            Scene scene = new Scene(root);
            moveStage.setScene(scene);
            moveStage.show();
            moveStage.setResizable(false);
        } catch (
                IOException e) {
            loadingFailure();
        }
    }

    /**
     * Reload window.
     */
    public void reloadWindow() {
        Stage moveStage = new Stage();
        moveStage.setTitle("Reload action");
        moveStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/GenericReload.fxml"));
            Scene scene = new Scene(root);
            moveStage.setScene(scene);
            moveStage.show();
            moveStage.setResizable(false);
        } catch (
                IOException e) {
            loadingFailure();
        }
    }

    /**
     * Weapon window.
     *
     * @param weaponName the weapon name
     */
//Receives a weapon selected from the previous window, creates a new window and passes the value
    //to the controller of that window (WeaponController)
    public void weaponWindow(String weaponName) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/Weapon.fxml"));
        try {
            Loader.load();
        } catch (
                IOException e) {
            loadingFailure();
        }
        WeaponController weapon = Loader.getController();
        weapon.setWeaponChosen(weaponName);
        Parent root = Loader.getRoot();
        Stage moveStage = new Stage();
        moveStage.setTitle(weaponName);
        moveStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        moveStage.setScene(scene);
        moveStage.show();
        moveStage.setResizable(false);
    }

    //Opens user available weapons on button click
    public void availableWeapons(){
        Stage availableWeapons = new Stage();
        availableWeapons.setTitle("WEAPONS");
        availableWeapons.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/AvailableWeapons.fxml"));
            Scene scene = new Scene(root);
            availableWeapons.setScene(scene);
            availableWeapons.show();
            availableWeapons.setResizable(false);
        } catch (
                IOException e) {
            loadingFailure();
        }
    }

    public void discardPowerUp(String powerUpType){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/PowerUps/DiscardPowerUp.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            loadingFailure();
        }
        DiscardPowerUpController discard = loader.getController();
        discard.setPowerUpType(powerUpType);

        Parent root = loader.getRoot();
        Stage discardWindow = new Stage();
        discardWindow.setTitle("DISCARD A POWERUP");
        discardWindow.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        discardWindow.setScene(scene);
        discardWindow.show();
    }
}