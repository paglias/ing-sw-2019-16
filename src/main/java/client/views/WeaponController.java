package client.views;

import javafx.beans.binding.Bindings;
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
import javafx.stage.Stage;
import messages.ActionEndMessage;
import messages.ActionMessage;
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

    private String weaponChosen;
    private ClientInput clientInput = new ClientInput();
    private ActionMessage shootMessage = new ActionMessage();

    public void setWeaponChosen(String weaponChosen) {
        this.weaponChosen = weaponChosen;

        //Loads the image
        String imagePath = "/JPGs/Weapons/";
        Image image = new Image(imagePath + weaponChosen + ".png");
        weaponView.setImage(image);
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
    @FXML private Button endButton;

    private  String shoot = "SHOOT";

    //SENDS THE SELECTED PARAMETERS TO SERVER, BY FILLING CLIENTINPUT
    @FXML void confirmFirstPrimary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;
        shootMessage.setActionItem(shoot);

        if (firstPrimary.isSelected()) {
            clientInput.effectType=1;
        }

        List<CheckBox> firstPrimarySelectedTargets = new ArrayList<>();
        firstPrimarySelectedTargets.add(player1primary1);
        firstPrimarySelectedTargets.add(player2primary1);
        firstPrimarySelectedTargets.add(player3primary1);
        firstPrimarySelectedTargets.add(player4primary1);
        firstPrimarySelectedTargets.add(player5primary1);

        for (CheckBox playerIsSelected : firstPrimarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                clientInput.players.add(playerIsSelected.getText());
            }
        }
        boolean isFirstPrimaryPositionEmpty = FPPosition.getSelectionModel().isEmpty();
        boolean isFirstPrimaryDirectionEmpty = FPDirection.getSelectionModel().isEmpty();

        if (!isFirstPrimaryPositionEmpty) {
            clientInput.positions.add(Integer.parseInt(FPPosition.getValue()));
        }
        if (!isFirstPrimaryDirectionEmpty){
            clientInput.direction = FPDirection.getValue();
        }

        shootMessage.setClientInput(clientInput);
        Game.controller.sendMsg(shootMessage);
    }

    //SENDS THE SELECTED PARAMETERS TO SERVER, BY FILLING CLIENTINPUT
    @FXML void confirmSecondPrimary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;
        shootMessage.setActionItem(shoot);

        if (secondPrimary.isSelected()) {
            clientInput.effectType = 1;
            clientInput.useSecondPrimary = true;
        }

        List<CheckBox> secondPrimarySelectedTargets = new ArrayList<>();
        secondPrimarySelectedTargets.add(player1primary2);
        secondPrimarySelectedTargets.add(player2primary2);
        secondPrimarySelectedTargets.add(player3primary2);
        secondPrimarySelectedTargets.add(player4primary2);
        secondPrimarySelectedTargets.add(player5primary2);

        for (CheckBox playerIsSelected : secondPrimarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                String t = playerIsSelected.getText();
                clientInput.players.add(t);
            }
        }

        boolean isSecondPrimaryPositionEmpty = SPPosition.getSelectionModel().isEmpty();
        boolean isSecondPrimaryDirectionEmpty = SPDirection.getSelectionModel().isEmpty();

        if (!isSecondPrimaryPositionEmpty) {
            clientInput.positions.add(Integer.parseInt(SPPosition.getValue()));
        }
        if (!isSecondPrimaryDirectionEmpty) {
            clientInput.direction = SPDirection.getValue();
        }

        shootMessage.setClientInput(clientInput);
        Game.controller.sendMsg(shootMessage);
    }

    //SENDS THE SELECTED PARAMETERS TO SERVER, BY FILLING CLIENTINPUT
    @FXML void confirmSecondary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;
        shootMessage.setActionItem(shoot);

        if (secondary.isSelected()) {
            clientInput.effectType = 2;
        }

        List<CheckBox> secondarySelectedTargets = new ArrayList<>();
        secondarySelectedTargets.add(player1secondary);
        secondarySelectedTargets.add(player2secondary);
        secondarySelectedTargets.add(player3secondary);
        secondarySelectedTargets.add(player4secondary);
        secondarySelectedTargets.add(player5secondary);

        for (CheckBox playerIsSelected : secondarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                clientInput.players.add(playerIsSelected.getText());
            }
        }

        boolean isSecondaryPositionEmpty = SecondaryPosition.getSelectionModel().isEmpty();
        boolean isSecondaryDirectionEmpty = secondaryDirection.getSelectionModel().isEmpty();

        if (!isSecondaryPositionEmpty) {
            clientInput.positions.add(Integer.parseInt(SecondaryPosition.getValue()));
        }

        if (!isSecondaryDirectionEmpty) {
            clientInput.direction = secondaryDirection.getValue();
        }

        shootMessage.setClientInput(clientInput);
        Game.controller.sendMsg(shootMessage);
    }


    @FXML void confirmTertiary(ActionEvent event) {

        clientInput.weaponName = weaponChosen;
        shootMessage.setActionItem(shoot);

        if (tertiary.isSelected()) {
            clientInput.effectType = 3;
        }

        List<CheckBox> tertiarySelectedTargets = new ArrayList<>();
        tertiarySelectedTargets.add(player1tertiary);
        tertiarySelectedTargets.add(player2tertiary);
        tertiarySelectedTargets.add(player3tertiary);
        tertiarySelectedTargets.add(player4tertiary);
        tertiarySelectedTargets.add(player5tertiary);

        for (CheckBox playerIsSelected : tertiarySelectedTargets) {
            if (playerIsSelected.isSelected()) {
                clientInput.players.add(playerIsSelected.getText());
            }
        }

        boolean isTertiaryPositionEmpty = tertiaryPosition.getSelectionModel().isEmpty();
        boolean isTertiaryDirectionEmpty = tertiaryDirection.getSelectionModel().isEmpty();


        if (!isTertiaryPositionEmpty) {
            clientInput.positions.add(Integer.parseInt(tertiaryPosition.getValue()));
        }

        if (!isTertiaryDirectionEmpty) {
            clientInput.direction = tertiaryDirection.getValue();
        }

        shootMessage.setClientInput(clientInput);
        Game.controller.sendMsg(shootMessage);
    }

    //Sends end message, closes the window
    @FXML public void endShoot(){
        ActionEndMessage endMessage = new ActionEndMessage();
        Game.controller.sendMsg(endMessage);
        Stage stage = (Stage) endButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Populate the choice boxes

        ArrayList<String> positions = new ArrayList<>();
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

        ObservableList<String> availablePositionChoices = FXCollections.observableArrayList(positions);

        ObservableList<String> availableDirectionChoices = FXCollections.observableArrayList(directions);

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