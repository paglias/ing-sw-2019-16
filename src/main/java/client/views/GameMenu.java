package client.views;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messages.ChooseNicknameMessage;
import utils.Logger;

import java.io.IOException;


public class GameMenu {
    @FXML Button confirmButton;
    @FXML TextField username;
    @FXML TextField address;

    /**
     * Connect and open next window.
     *
     *
     * @param event the event
     * @throws InterruptedException if sleep doesn't work.
     */
    public void nextWindow(ActionEvent event) throws InterruptedException {
        connectToServer();

        Thread.sleep(1000); // wait for connetion to setup

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
     * Connect to the server.
     */
    private void connectToServer () {
        String[] tokens = address.getText().split(":");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("Invalid address and port");
        }

        String host = tokens[0];
        int port = Integer.parseInt(tokens[1]);

        Client.connect(host, port);
    }

    /**
     * Set nickname on the server.
     */
    private void setNickname (){
        ChooseNicknameMessage chooseNicknameMessage = new ChooseNicknameMessage();
        chooseNicknameMessage.setNickname(username.getText());
        Game.controller.sendMsg(chooseNicknameMessage);
    }
}
