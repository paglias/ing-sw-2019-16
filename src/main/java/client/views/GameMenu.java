package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class GameMenu {

    public void chooseMap1() {
    }

    public void chooseMap2() {
    }

    public void chooseMap3() {
    }

    public void chooseMap4() {
    }

    public void setNickname() {
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

    @FXML
    public Button confirmButton;


    public void nextWindow(ActionEvent event) throws Exception {
        Parent root;
        Stage window;

        if (event.getSource() == confirmButton) {
            window = (Stage) confirmButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXMLs/Lobby.fxml"));
            Scene scene = new Scene(root, 600, 400);
            window.setScene(scene);
            window.show();
        }
    }
}
