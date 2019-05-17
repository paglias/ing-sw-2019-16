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


import java.util.List;


public class Lobby {

    public int Map=0;

    public void setMap(int map) {
        Map = map;
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

    public void previewMap1() throws Exception {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map one");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap1.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }

    public void previewMap2() throws Exception {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap2.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }

    public void previewMap3() throws Exception {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap3.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }

    public void previewMap4() throws Exception {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap4.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }
    //Called by Start Game button
    public void startGame(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == startButton) {
            stage = (Stage) startButton.getScene().getWindow();
            stage.close();

            List<Window> openWindows = Stage.getWindows().filtered(window -> window.isShowing());
            stage = (Stage) openWindows.get(0);

            switch (Map) {
                case 1:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Game1.fxml"));
                    Scene scene = new Scene(root, 1300, 700);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    break;
                case 2:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Game2.fxml"));
                    Scene scene2 = new Scene(root, 1300, 700);
                    stage.setScene(scene2);
                    stage.centerOnScreen();
                    break;
                case 3:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Game3.fxml"));
                    Scene scene3 = new Scene(root, 1300, 700);
                    stage.setScene(scene3);
                    stage.centerOnScreen();
                    break;
                case 4:
                    root = FXMLLoader.load(getClass().getResource("/FXMLs/Game4.fxml"));
                    Scene scene4 = new Scene(root, 1300, 700);
                    stage.setScene(scene4);
                    stage.centerOnScreen();
                    break;
                default:
                    System.out.println("No map was chosen");//TODO CREATE ALERT BOX
            }
        }
    }
}
