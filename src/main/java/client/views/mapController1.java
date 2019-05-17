package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class mapController1 implements Initializable {
    @FXML
    ImageView gameImage1;
    @FXML
    AnchorPane gamePane1;
    @FXML
    VBox gameBox1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameImage1.fitWidthProperty().bind(gameBox1.widthProperty());
        gameImage1.fitHeightProperty().bind(gameBox1.heightProperty());
    }
}
