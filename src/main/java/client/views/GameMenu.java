package client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameMenu {

    public void chooseMap1(){
    }
    public void chooseMap2(){
    }
    public void chooseMap3(){
    }
    public void chooseMap4(){
    }
    public void setNickname(){
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

    public void previewMap2() throws Exception{
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap2.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }
    public void previewMap3() throws Exception{
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap3.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }
    public void previewMap4() throws Exception{
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Map two");
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/previewMap4.fxml"));

        scene = new Scene(root, 600, 430);

        window.setScene(scene);
        window.show();
    }
    public void nextWindow(){
        //username must be inserted and map must be chosen
    }
}
