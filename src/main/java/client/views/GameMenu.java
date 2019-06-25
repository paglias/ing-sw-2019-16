package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messages.ChooseNicknameMessage;

import java.io.IOException;


public class GameMenu {
    @FXML Button confirmButton;
    @FXML TextField username;

    /**
     * Next window.
     *
     * @param event the event
     */
//When Confirm is pressed, loads a new scene with Lobby view
    public void nextWindow(ActionEvent event) {
        setNickname();

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
                GenericWindows genericWindows = new GenericWindows();
                genericWindows.loadingFailure();
            }
        }
    }

    /**
     * Set nickname.
     */
//sends nickname entered by user
    void setNickname (){
        ChooseNicknameMessage chooseNicknameMessage = new ChooseNicknameMessage();
        chooseNicknameMessage.setNickname(username.getText());
        Game.controller.sendMsg(chooseNicknameMessage);
    }
}
