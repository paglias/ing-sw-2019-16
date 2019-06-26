package client.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import messages.client_data.ClientInput;
import messages.client_data.PlayerOtherData;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//SHOOT WINDOW CONTROLLER. HAS A WEAPON IMAGE ON THE LEFT, TARGETS AND EFFECTS ON THE RIGHT.
//EFFECTS CAN BE FIRST PRIMARY, SECOND PRIMARY, SECONDARY, TERTIARY
//EACH EFFECT CAN HAVE A MULTIPLE PLAYER TARGET, A POSITION TARGET AND A DIRECTION TARGET
public class WeaponController implements Initializable {

    public String weaponChosen;
    ClientInput clientInput = new ClientInput();


    public void setWeaponChosen(String weaponChosen) {
        this.weaponChosen = weaponChosen;
    }

    @FXML private ImageView weaponView;
    @FXML private RadioButton firstPrimary;
    @FXML private RadioButton secondPrimary;
    @FXML private RadioButton secondary;
    @FXML private RadioButton tertiary;
    @FXML private CheckBox player1primary1;
    @FXML private CheckBox player2primary1;
    @FXML private CheckBox player3primary1;
    @FXML private CheckBox player4primary1;
    @FXML private CheckBox player5primary1;
    @FXML private CheckBox player1primary2;
    @FXML private CheckBox player2primary2;
    @FXML private CheckBox player3primary2;
    @FXML private CheckBox player4primary2;
    @FXML private CheckBox player5primary2;
    @FXML private CheckBox player1secondary;
    @FXML private CheckBox player2secondary;
    @FXML private CheckBox player3secondary;
    @FXML private CheckBox player4secondary;
    @FXML private CheckBox player5secondary;
    @FXML private CheckBox player1tertiary;
    @FXML private CheckBox player2tertiary;
    @FXML private CheckBox player3tertiary;
    @FXML private CheckBox player4tertiary;
    @FXML private CheckBox player5tertiary;
    @FXML private ChoiceBox<String> FPPosition;
    @FXML private ChoiceBox<String> SPPosition;
    @FXML private ChoiceBox<String> SecondaryPosition;
    @FXML private ChoiceBox<String> tertiaryPosition;
    @FXML private ChoiceBox<String> FPDirection;
    @FXML private ChoiceBox<String> SPDirection;
    @FXML private ChoiceBox<String> secondaryDirection;
    @FXML private ChoiceBox<String> tertiaryDirection;
    @FXML private Button confirm1;
    @FXML private Button confirm2;
    @FXML private Button confirm3;
    @FXML private Button confirm4;

    //SENDS THE SELECTED PARAMETERS TO SERVER, BY FILLING CLIENTINPUT
    @FXML void confirmFirstPrimary(ActionEvent event) {

        if (firstPrimary.isSelected()) {
            clientInput.weaponName = weaponChosen;
            clientInput.effectType=1; //TODO FIRST PRIMARY FOR SERVER?
        }

        List<CheckBox> firstPrimarySelectedTargets = new ArrayList<>();
        firstPrimarySelectedTargets.add(player1primary1);
        firstPrimarySelectedTargets.add(player2primary1);
        firstPrimarySelectedTargets.add(player3primary1);
        firstPrimarySelectedTargets.add(player4primary1);
        firstPrimarySelectedTargets.add(player5primary1);

        for (CheckBox playerIsSelected : firstPrimarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                String s = playerIsSelected.getText();
                //TODO ADD STRING TO CLIENTINPUT, SEND MESSAGE TO SERVER
            }
        }
        boolean isFirstPrimaryPositionEmpty = FPPosition.getSelectionModel().isEmpty();
        boolean isFirstPrimaryDirectionEmpty = FPDirection.getSelectionModel().isEmpty();

        if (!isFirstPrimaryPositionEmpty) {
            String s = FPPosition.getValue();
            //TODO SEND STRING POSITION TARGET TO SERVER
        }
        if (!isFirstPrimaryDirectionEmpty) {
            String s = FPDirection.getValue();
            //TODO SEND STRING DIRECTION TO SERVER
        }

    }

    //SENDS THE SELECTED PARAMETERS TO SERVER, BY FILLING CLIENTINPUT
    @FXML void confirmSecondPrimary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;

        if (secondPrimary.isSelected()) {
            //TODO add effect to message
        }

        List<CheckBox> secondPrimarySelectedTargets = new ArrayList<>();
        secondPrimarySelectedTargets.add(player1primary2);
        secondPrimarySelectedTargets.add(player2primary2);
        secondPrimarySelectedTargets.add(player3primary2);
        secondPrimarySelectedTargets.add(player4primary2);
        secondPrimarySelectedTargets.add(player5primary2);

        for (CheckBox playerIsSelected : secondPrimarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                String f = playerIsSelected.getText();
                //TODO ADD STRING TO CLIENTINPUT, SEND MESSAGE TO SERVER
            }
        }

        boolean isSecondPrimaryPositionEmpty = SPPosition.getSelectionModel().isEmpty();
        boolean isSecondPrimaryDirectionEmpty = SPDirection.getSelectionModel().isEmpty();

        if (!isSecondPrimaryPositionEmpty) {
            String s = SPPosition.getValue();
            //TODO SEND STRING POSITION TARGET TO SERVER
        }
        if (!isSecondPrimaryDirectionEmpty) {
            String s = SPDirection.getValue();
            //TODO SEND STRING DIRECTION TO SERVER
        }
    }

    //SENDS THE SELECTED PARAMETERS TO SERVER, BY FILLING CLIENTINPUT
    @FXML void confirmSecondary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;

        if (secondary.isSelected()) {
            //TODO add effect to message
        }

        List<CheckBox> secondarySelectedTargets = new ArrayList<>();
        secondarySelectedTargets.add(player1secondary);
        secondarySelectedTargets.add(player2secondary);
        secondarySelectedTargets.add(player3secondary);
        secondarySelectedTargets.add(player4secondary);
        secondarySelectedTargets.add(player5secondary);

        for (CheckBox playerIsSelected : secondarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                String s = playerIsSelected.getText();
                //TODO ADD STRING TO CLIENTINPUT, SEND MESSAGE TO SERVER
            }
        }

        boolean isSecondaryPositionEmpty = SecondaryPosition.getSelectionModel().isEmpty();
        boolean isSecondaryDirectionEmpty = secondaryDirection.getSelectionModel().isEmpty();

        if (!isSecondaryPositionEmpty) {
            String s = SecondaryPosition.getValue();
            //TODO SEND STRING POSITION TO SERVER
        }

        if (!isSecondaryDirectionEmpty) {
            String s = secondaryDirection.getValue();
            //TODO SEND STRING DIRECTION TO SERVER
        }
    }


    @FXML void confirmTertiary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;

        if (tertiary.isSelected()) {
            //TODO add effect to message
        }

        List<CheckBox> tertiarySelectedTargets = new ArrayList<>();
        tertiarySelectedTargets.add(player1tertiary);
        tertiarySelectedTargets.add(player2tertiary);
        tertiarySelectedTargets.add(player3tertiary);
        tertiarySelectedTargets.add(player4tertiary);
        tertiarySelectedTargets.add(player5tertiary);

        for (CheckBox playerIsSelected : tertiarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                String s = playerIsSelected.getText();
                //TODO ADD STRING TO CLIENTINPUT, SEND MESSAGE TO SERVER
            }
        }

        boolean isTertiaryPositionEmpty = tertiaryPosition.getSelectionModel().isEmpty();


        boolean isTertiaryDirectionEmpty = tertiaryDirection.getSelectionModel().isEmpty();


        if (!isTertiaryPositionEmpty) {
            String s = tertiaryPosition.getValue();
            //TODO SEND STRING POSITION TO SERVER
        }
        if (!isTertiaryDirectionEmpty) {
            String s = tertiaryDirection.getValue();
            //TODO SEND STRING DIRECTION TO SERVER
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Loads the image

        String imagePath = "/JPGs/Weapons/";
        Image image = new Image(imagePath + weaponChosen + ".png");
        weaponView.setImage(image);

        //Populate the choice boxes

        ArrayList<String> positions = new ArrayList();
        positions.add("0");
        positions.add("1");
        positions.add("2");
        positions.add("2");
        positions.add("4");
        positions.add("5");
        positions.add("6");
        positions.add("7");
        positions.add("8");
        positions.add("9");
        positions.add("10");
        positions.add("11");

        ArrayList<String> directions = new ArrayList<>();
        directions.add("SOUTH");
        directions.add("NORTH");
        directions.add("EAST");
        directions.add("WEST");

        ObservableList<String> availablePositionChoices = FXCollections.observableArrayList(
                positions.get(0),
                positions.get(1),
                positions.get(2),
                positions.get(3),
                positions.get(4),
                positions.get(5),
                positions.get(6),
                positions.get(7),
                positions.get(8),
                positions.get(9),
                positions.get(10),
                positions.get(11));

        ObservableList<String> availableDirectionChoices = FXCollections.observableArrayList(
                directions.get(0),
                directions.get(1),
                directions.get(2),
                directions.get(3));

        FPPosition.setItems(availablePositionChoices);
        SPPosition.setItems(availablePositionChoices);
        SecondaryPosition.setItems(availablePositionChoices);
        tertiaryPosition.setItems(availablePositionChoices);

        FPDirection.setItems(availableDirectionChoices);
        SPDirection.setItems(availableDirectionChoices);
        secondaryDirection.setItems(availableDirectionChoices);
        tertiaryDirection.setItems(availableDirectionChoices);


        //SETS NICKNAMES OF PLAYERS
        ArrayList<PlayerOtherData> players = Game.controller.getLastGameStateMessage().gameBoardData.players;
        int index = 0;
        for (PlayerOtherData p : players) {
            if (index == 0) {
                player1primary1.setText(p.nickname);
                player1primary2.setText(p.nickname);
                player1secondary.setText(p.nickname);
                player1tertiary.setText(p.nickname);
            }

            if (index == 1) {
                player2primary1.setText(p.nickname);
                player2primary2.setText(p.nickname);
                player2secondary.setText(p.nickname);
                player2tertiary.setText(p.nickname);
            }

            if (index == 2) {
                player3primary1.setText(p.nickname);
                player3primary2.setText(p.nickname);
                player3secondary.setText(p.nickname);
                player3tertiary.setText(p.nickname);
            }

            if (index == 3) {
                player4primary1.setText(p.nickname);
                player4primary2.setText(p.nickname);
                player4secondary.setText(p.nickname);
                player4tertiary.setText(p.nickname);
            }

            if (index == 4) {
                player5primary1.setText(p.nickname);
                player5primary2.setText(p.nickname);
                player5secondary.setText(p.nickname);
                player5tertiary.setText(p.nickname);
            }
            index++;
        }
    }
}