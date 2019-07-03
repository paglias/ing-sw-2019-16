package client.views;

import client.Client;
import client.views.ActionsControllers.GenericMoveController;
import client.views.PowerUps.DiscardPowerUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Logger;
import java.io.IOException;
import java.util.Optional;

public class GenericWindows {

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
            Client.quit();
        } else {
            quitAlert.close();
        }
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
     * Launch loading failure popup.
     */
    public void loadingFailure() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loading error");
        alert.setHeaderText("Fatal error while loading the FXML file");
        alert.setContentText("FXML loading error, check the presence of the FXML");
        alert.showAndWait();
    }

    /**
     * Launch error message popup.
     */
    public void errorMessage (String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong with the last command!");
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }

    /**
     * Launch winner message popup.
     */
    public void winnerMessage (String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game ended!");
        alert.setHeaderText("The game ended!");
        alert.setContentText("The winner is " + winner);
        alert.showAndWait();

        Client.quit();
    }

    /**
     * Death window.
     */
    public void deathWindow() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("YOU SUCK");
        alert.setHeaderText("You have DIED! You are terrible at this game");
        alert.setContentText("You will have to spawn again next turn. Go cry in a corner now");
        alert.showAndWait();
    }

    /**
     * Show marks.
     *
     * @param playerNumber the player number
     */
    public void showMarks(int playerNumber) {
        Stage marksWindow = new Stage();
        marksWindow.initStyle(StageStyle.UNDECORATED);
        marksWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/Marks.fxml"));
        try {
            loader.load();
        } catch (Throwable e) {
            Logger.err(e, "Error showing marks");
            loadingFailure();
        }
        MarksController controller = loader.getController();
        controller.setCurrentPlayer(playerNumber);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        marksWindow.setScene(scene);
        marksWindow.show();
        marksWindow.setResizable(false);
    }

//Shows a window with the weapon contained in the weaponSlot.
    //The weapon is loaded in WeaponPreviewController
    public void showWeapon(String weaponName) {

        Stage weaponWindow = new Stage(StageStyle.UNDECORATED);
        weaponWindow.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/WeaponWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            loadingFailure();
        }
        WeaponPreviewController controller = loader.getController();
        controller.setWeapon(weaponName);
        Parent root = loader.getRoot();
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

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/GenericMove.fxml"));
            try {
                loader.load();
            } catch (
                    IOException e) {
                loadingFailure();
            }
            GenericMoveController ctrl = loader.getController();
            ctrl.loadCanAccess();

            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            moveStage.setScene(scene);
            moveStage.show();
            moveStage.setResizable(false);
    }

    /**
     * Shoot window.
     */
    public void shootWindow() {
        Stage shootWindow = new Stage();
        shootWindow.setTitle("Shoot action");
        shootWindow.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/GenericShoot.fxml"));
            Scene scene = new Scene(root);
            shootWindow.setScene(scene);
            shootWindow.show();
            shootWindow.setResizable(false);
        } catch (
                IOException e) {
            loadingFailure();
        }
    }

    /**
     * Reload window.
     */
    public void reloadWindow() {
        Stage reloadStage = new Stage();
        reloadStage.setTitle("Reload action");
        reloadStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/GenericReload.fxml"));
            Scene scene = new Scene(root);
            reloadStage.setScene(scene);
            reloadStage.show();
            reloadStage.setResizable(false);
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/Weapon.fxml"));
        try {
            loader.load();
        } catch (Throwable e) {
            Logger.err(e, "Error loading weapon window");
            loadingFailure();
        }
        WeaponController weapon = loader.getController();
        weapon.setWeaponChosen(weaponName);
        Parent root = loader.getRoot();
        Stage weaponStage = new Stage();
        weaponStage.setTitle(weaponName);
        weaponStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        weaponStage.setScene(scene);
        weaponStage.show();
        weaponStage.setResizable(false);
    }

    //Opens user available weapons on button click
    public void availableWeapons(){
        Stage weaponStage = new Stage();
        weaponStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/AvailableWeapons.fxml"));
            Scene weaponScene = new Scene(root);
            weaponStage.setScene(weaponScene);
            weaponStage.show();
            weaponStage.setResizable(false);
        } catch (Throwable e) {
            Logger.err(e, "Error loading weapons list");
            loadingFailure();
        }
    }

    public void discardPowerUp(String powerUpType){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/PowerUps/DiscardPowerUp.fxml"));
        try {
            loader.load();
        } catch (Throwable e) {
            loadingFailure();
            Logger.err(e, "Error loading discard powerup controller");
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

    public void endTurn(){
        Stage endTurnStage = new Stage();
        endTurnStage.setTitle("END TURN");
        endTurnStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/EndTurn.fxml"));
            Scene scene = new Scene(root);
            endTurnStage.setScene(scene);
            endTurnStage.show();
            endTurnStage.setResizable(false);
        } catch (IOException e) {
            loadingFailure();
        }
    }

    public void spawn(){
        Stage spawnStage = new Stage();
        spawnStage.setTitle("SPAWNING");
        spawnStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/ActionFXMLs/SpawnAction.fxml"));
            Scene scene = new Scene(root);
            spawnStage.setScene(scene);
            spawnStage.show();
            spawnStage.setResizable(false);
        } catch (IOException e) {
            loadingFailure();
        }
    }

    public void weaponGrab(){
        //Create new window, let user choose which weapon
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLs/ActionFXMLs/WeaponGrab.fxml"));
        try {
            loader.load();
        } catch (Throwable e) {
            Logger.err(e, null);
            loadingFailure();
        }

        Parent root = loader.getRoot();
        Stage weaponChooserStage = new Stage();
        weaponChooserStage.setTitle("CHOOSE A WEAPON");
        weaponChooserStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        weaponChooserStage.setScene(scene);
        weaponChooserStage.show();
    }

    //Preview of the map during move action
    public void mapPreview(int mapNumber){
        String map = Integer.toString(mapNumber);
        Stage previewStage = new Stage();
        previewStage.setTitle("MAP PREVIEW");
        previewStage.initModality(Modality.APPLICATION_MODAL);
        previewStage.initStyle(StageStyle.UNDECORATED);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/PreviewMap" +map+ ".fxml"));
            Scene scene = new Scene(root);
            previewStage.setScene(scene);
            previewStage.show();
            previewStage.setResizable(false);
        } catch (IOException e) {
            loadingFailure();
        }
    }
}