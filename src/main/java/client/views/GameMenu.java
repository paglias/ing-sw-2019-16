package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class GameMenu {

    @FXML Button confirmButton;
    @FXML TextField address;
    @FXML TextField username;

    //When Confirm is pressed, loads a new scene with Lobby view
    public void nextWindow(ActionEvent event) {
        Parent root;
        Stage window;

        if (event.getSource() == confirmButton) {
            window = (Stage) confirmButton.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("/FXMLs/Lobby.fxml"));
                Scene scene = new Scene(root, 600, 400);
                window.setScene(scene);
                window.show();
            } catch (IOException e) {
                AlertBoxes alertBoxes = new AlertBoxes();
                alertBoxes.loadingFailure();
            }
        }
    }

    //sends nickname entered by user
    public void setNickname(){
    }

    //sends address enteres by user
    public void setAddress(){
    }
}
