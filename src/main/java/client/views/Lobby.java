package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import messages.GameStateMessage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Lobby extends AbstractView {

    public int Map = 0;

    public void setMap(int map) {
        this.Map = map;
    }

    @FXML
    Button startButton;

    @FXML
    Label connectedPlayer1;

    @FXML
    Label connectedPlayer2;

    @FXML
    Label connectedPlayer3;

    @FXML
    Label connectedPlayer4;

    @FXML
    Label connectedPlayer5;

    @FXML
    RadioButton fiveSkulls;

    @FXML
    RadioButton sixSkulls;

    @FXML
    RadioButton sevenSkulls;

    @FXML
    RadioButton eightSkulls;

    public void chooseMap1() {
        setMap(1);
    }

    public void chooseMap2() {
        setMap(2);
    }

    public void chooseMap3() {
        setMap(3);
    }

    public void chooseMap4() {
        setMap(4);
    }


    public void previewMap1() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map one");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap1.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            AlertBoxes alertBox = new AlertBoxes();
            alertBox.loadingFailure();
        }
    }

    public void previewMap2() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap2.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            AlertBoxes alertBox = new AlertBoxes();
            alertBox.loadingFailure();
        }
    }

    public void previewMap3() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map three");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap3.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            AlertBoxes alertBox = new AlertBoxes();
            alertBox.loadingFailure();
        }
    }

    public void previewMap4() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map four");
        Scene scene;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap4.fxml"));
            scene = new Scene(root, 600, 430);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            AlertBoxes alertBox = new AlertBoxes();
            alertBox.loadingFailure();
        }
    }

    //Called by Start Game button
    public void startGame(ActionEvent event) {
        Stage stage;
        Parent root;
        if (event.getSource() == startButton) {
            stage = (Stage) startButton.getScene().getWindow();
            stage.close();

            List<Window> openWindows = Stage.getWindows().filtered(window -> window.isShowing());
            stage = (Stage) openWindows.get(0);

            try {
                switch (Map) {
                    case 1:
                        root = FXMLLoader.load(getClass().getResource("/FXMLs/Map1.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        break;
                    case 2:
                        root = FXMLLoader.load(getClass().getResource("/FXMLs/Map2.fxml"));
                        Scene scene2 = new Scene(root, 1366, 768);
                        stage.setScene(scene2);
                        stage.centerOnScreen();
                        break;
                    case 3:
                        root = FXMLLoader.load(getClass().getResource("/FXMLs/Map3.fxml"));
                        Scene scene3 = new Scene(root, 1366, 768);
                        stage.setScene(scene3);
                        stage.centerOnScreen();
                        break;
                    case 4:
                        root = FXMLLoader.load(getClass().getResource("/FXMLs/Map4.fxml"));
                        Scene scene4 = new Scene(root, 1366, 768);
                        stage.setScene(scene4);
                        stage.centerOnScreen();
                        break;
                    default:
                        AlertBoxes alertBox = new AlertBoxes();
                        alertBox.noMapChosen();
                }
            } catch (IOException e) {
                AlertBoxes alertBox = new AlertBoxes();
                alertBox.loadingFailure();
            }
        }
    }

    public void updateWithData (GameStateMessage gameStateMessage) {
        // TODO update view with data here
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("setting up lobby");
        Game.controller.registerCurrentView(this);
    }
}