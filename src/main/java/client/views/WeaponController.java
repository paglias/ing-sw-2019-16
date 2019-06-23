package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WeaponController implements Initializable {

    String weaponChosen;

    public void setWeaponChosen(String weaponChosen) {
        this.weaponChosen = weaponChosen;
    }

    @FXML
    private AnchorPane leftPane;

    @FXML
    private ImageView weaponView;

    @FXML
    private AnchorPane rightPane;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        //TODO Load images dynamically on the left side
    }
}
