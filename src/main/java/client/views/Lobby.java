package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.util.List;


public class Lobby {

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

    public void startGame(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == startButton) {
            stage = (Stage) startButton.getScene().getWindow();
            stage.close();
            List<Window> open = Stage.getWindows().filtered(window -> window.isShowing());
            stage = (Stage) open.get(0);
            root = FXMLLoader.load(getClass().getResource("/FXMLs/Game.fxml"));
            Scene scene = new Scene(root, 1000, 650);
            stage.setScene(scene);
        }
    }
}
