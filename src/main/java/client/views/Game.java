package client.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;


public class Game extends Application {
    Stage window;

    public static void startGame(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Adrenaline");
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/Welcome.fxml"));
        window.setScene(new Scene(root, 1000, 650));
        window.show();
    }
}
