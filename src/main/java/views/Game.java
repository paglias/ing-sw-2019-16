package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Game extends Application {

    Stage window;
    Button button1;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("This shit finally works");
        button1 = new Button("Click me to break everything");
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        Scene scene = new Scene(layout, 800, 600);

            // Add the scene to the Stage
            primaryStage.setScene(scene);
            // Display the Stage
            primaryStage.show();
        }
    }
