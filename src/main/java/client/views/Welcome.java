package client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class  Welcome {
    public void clickAnywhere(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Welcome");
        Scene scene1;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/GameMenu.fxml"));
            scene1 = new Scene(root, 600, 400);
            window.setScene(scene1);
            window.show();
        }
        catch (IOException e){
            GenericWindows alertBox = new GenericWindows();
            alertBox.loadingFailure();
        }
    }
}