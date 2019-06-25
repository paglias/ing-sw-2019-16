package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import messages.client_data.ClientInput;
import java.net.URL;
import java.util.ResourceBundle;


public class WeaponController implements Initializable {

    public String weaponChosen = "Railgun";


    public void setWeaponChosen(String weaponChosen) {
        this.weaponChosen = weaponChosen;
    }

    @FXML private AnchorPane leftPane;

    @FXML private ImageView weaponView;

    @FXML private AnchorPane rightPane;

    @FXML private RadioButton firstPrimary;

    @FXML private ToggleGroup primaryEffect;

    @FXML private RadioButton secondPrimary;

    @FXML private CheckBox secondary;

    @FXML private CheckBox tertiary;

    @FXML private VBox VB1;

    @FXML private CheckBox player1primary1;

    @FXML private CheckBox player2primary1;

    @FXML private CheckBox player3primary3;

    @FXML private CheckBox player4primary1;

    @FXML private CheckBox player5primary1;

    @FXML private VBox VB2;

    @FXML private CheckBox player1primary2;

    @FXML private CheckBox player2primary2;

    @FXML private CheckBox player3primary2;

    @FXML private CheckBox player4primary2;

    @FXML private CheckBox player5primary2;

    @FXML private VBox VB3;

    @FXML private CheckBox player1secondary;

    @FXML private CheckBox player2secondary;

    @FXML private CheckBox player3secondary;

    @FXML private CheckBox player4secondary;

    @FXML private CheckBox player5secondary;

    @FXML private VBox VB4;

    @FXML private CheckBox player1tertiary;

    @FXML private CheckBox player2tertiary;

    @FXML private CheckBox player3tertiary;

    @FXML private CheckBox player4tertiary;

    @FXML private CheckBox player5tertiary;

    @FXML private VBox VB5;

    @FXML private ChoiceBox<?> FPPosition;

    @FXML private VBox VB6;

    @FXML private ChoiceBox<?> SPPosition;

    @FXML private VBox VB7;

    @FXML private ChoiceBox<?> SecondaryPosition;

    @FXML private VBox VB8;

    @FXML private ChoiceBox<?> tertiaryPosition;

    @FXML private VBox VB9;

    @FXML private ChoiceBox<?> FPDirection;

    @FXML private VBox VB10;

    @FXML private ChoiceBox<?> SPDirection;

    @FXML private VBox VB11;

    @FXML private ChoiceBox<?> secondaryDirection;

    @FXML private VBox VB12;

    @FXML private ChoiceBox<?> tertiaryDirection;

    @FXML private Button confirmButton;


    @FXML void confirmShoot (ActionEvent event) {
        ClientInput input = new ClientInput();
        if (firstPrimary.isSelected()){
            //TODO SEND EFFECT
        }
        else if (secondPrimary.isSelected()){
            //TODO SEND EFFECT
        }
        if (secondary.isSelected()){
            //TODO SEND EFFECT
        }
        if (tertiary.isSelected()){
            //TODO SEND EFFECT
        }

    }






    @Override
    public void initialize(URL location, ResourceBundle resources){
        String imagePath = "/JPGs/Weapons/";
        Image image = new Image(imagePath+weaponChosen+".png");
        weaponView.setImage(image);
    }

}

