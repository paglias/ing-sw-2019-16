package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class GameMenu {

    @FXML
    public Button confirmButton;

    //When Confirm is pressed, loads a new scene with Lobby view
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
    public void setNickname(){
    }
}
