package client.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//Creates game stage, loads Adrenaline.jpg. Later it will be changed to game overview.
public class Game extends Application {
    Stage window;

    public static void startGame(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Adrenaline");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/Welcome.fxml"));
            window.setScene(new Scene(root));
            window.show();
            window.setResizable(false);
            window.centerOnScreen();
        } catch (IOException e) {
            GenericWindows genericWindows = new GenericWindows();
            genericWindows.loadingFailure();
        }
    }
}