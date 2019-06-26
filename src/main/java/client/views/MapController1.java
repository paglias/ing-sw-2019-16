package client.views;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import messages.GameStateMessage;
import messages.client_data.PlayerOtherData;
import messages.client_data.PlayerYouData;
import utils.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController1 extends AbstractView implements Initializable {
    GenericWindows genericWindows = new GenericWindows();

    @FXML private ImageView image1;
    @FXML private AnchorPane imageAnchorPane;
    @FXML private Button quitButton;
    @FXML private SplitPane horizontalSplit;
    @FXML private SplitPane verticalSplit;
    @FXML private SplitPane horizontalSplit2;
    @FXML private AnchorPane upperLeftPane;
    @FXML private AnchorPane lowerLeftPane;
    @FXML private GridPane playerboardGrid;
    @FXML private Label username5;
    @FXML private Button marksPlayer5;
    @FXML private Label username1;
    @FXML private Button marksPlayer1;
    @FXML private Label username2;
    @FXML private Button marksPlayer2;
    @FXML private Label username3;
    @FXML private Button marksPlayer3;
    @FXML private Label username4;
    @FXML private Button marksPlayer4;
    @FXML private GridPane gridPane1;
    @FXML private Label redWeapon1;
    @FXML private Label redWeapon2;
    @FXML private Label redWeapon3;
    @FXML private Label blueWeapon1;
    @FXML private Label blueWeapon2;
    @FXML private Label blueWeapon3;
    @FXML private Label yellowWeapon1;
    @FXML private Label yellowWeapon2;
    @FXML private Label yellowWeapon3;
    @FXML private Button drawButton;
    @FXML private Button actionsButton;
    @FXML private Button powerUpsButton;
    @FXML private Button weaponsButton;
    @FXML private Text redAmmo;
    @FXML private Text blueAmmo;
    @FXML private Text yellowAmmo;
    @FXML private Text actionCounter;
    @FXML private Text totalPoints;
    @FXML private ImageView skullOne;
    @FXML private ImageView skullTwo;
    @FXML private ImageView skullThree;
    @FXML private ImageView skullFour;
    @FXML private ImageView skullFive;
    @FXML private ImageView skullSix;
    @FXML private ImageView skullSeven;
    @FXML private ImageView skullEight;
    @FXML private Label player1Active;
    @FXML private Label player2Active;
    @FXML private Label player3Active;
    @FXML private Label player4Active;
    @FXML private Label player5Active;

    //Updates game values with message received by server
    public void updateWithData(GameStateMessage gameStateMessage){
        int pIndex = 0;

        ArrayList<PlayerOtherData> players = gameStateMessage.gameBoardData.players;
        PlayerYouData currentPlayer = gameStateMessage.playerYouData;

        Label usernameLabel = null;
        Label playerStatus = null;

        for (PlayerOtherData player : players) {
            switch (pIndex) {
                case 0:
                    usernameLabel = username1;
                    playerStatus = player1Active;
                    break;
                case 1:
                    usernameLabel = username2;
                    playerStatus = player2Active;
                    break;
                case 2:
                    usernameLabel = username3;
                    playerStatus = player3Active;
                    break;
                case 3:
                    usernameLabel = username4;
                    playerStatus = player4Active;
                    break;
                case 4:
                    usernameLabel = username5;
                    playerStatus = player5Active;
                    break;
            }

            if (usernameLabel != null && playerStatus != null) {
                usernameLabel.setText(player.nickname);
                if (player.isActive) {
                    playerStatus.setText("ACTIVE");
                } else if (!player.isConnected) {
                    playerStatus.setText("DISABLED");
                } else {
                    playerStatus.setText("CONNECTED");
                }
            }


            pIndex++;
        }

        totalPoints.setText(String.valueOf(currentPlayer.totalPoints));
        actionCounter.setText(String.valueOf(currentPlayer.actionCounter));

        int redAmmoN = 0;
        int blueAmmoN = 0;
        int yellowAmmoN = 0;

        for (String cube : currentPlayer.cubes) {
            switch (cube) {
                case "RED":
                    redAmmoN++;
                    break;
                case "BLUE":
                    blueAmmoN++;
                    break;
                case "YELLOW":
                    yellowAmmoN++;
                    break;
            }
        }

        redAmmo.setText(String.valueOf(redAmmoN));
        yellowAmmo.setText(String.valueOf(yellowAmmoN));
        blueAmmo.setText(String.valueOf(blueAmmoN));

        //TODO NSKULLS = LOAD A SKULL JPG IN EACH IMAGEVIEW (SKULLONE, SKULLTWO...)
        //TODO red/blue/yellow weapons label: update the labels with the current weaponSlot weapons
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Game.controller.registerCurrentView(this);

        //Adjusts map size correctly
        image1.fitWidthProperty().bind(imageAnchorPane.widthProperty());
        image1.fitHeightProperty().bind(imageAnchorPane.heightProperty());

        //Locks all splitPanes so they cannot be moved
        ArrayList<SplitPane> splitPanes = new ArrayList();
        splitPanes.add(verticalSplit);
        splitPanes.add(horizontalSplit);
        splitPanes.add(horizontalSplit2);

        for (SplitPane pane : splitPanes ) {
            final double pos[] = pane.getDividerPositions();

            pane.setDividerPositions(pos);

            for (int i = 0; i < pane.getDividers().size(); i++) {
                final int ind = i;
                SplitPane.Divider divider = pane.getDividers().get(i);
                divider.positionProperty().addListener(
                        (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
                        {
                            divider.setPosition(pos[ind]);
                        });
            }
        }
    }

    //Alert box for quitting the game
    @FXML
    public void quitGame(){
        genericWindows.quitGame();
    }

    //Opens a window with the actions available. Many buttons will be disabled.
    @FXML
    public void openActionWindow(){
        genericWindows.actionWindow();
    }


    //Opens the powerup window where you can choose what powerup you want to use
    @FXML void openPowerUpsWindow() {
        genericWindows.powerUps();
    }

    @FXML void drawPowerUp(ActionEvent event) {}

    @FXML void openWeaponsWindow(ActionEvent event) {}



    //Opens window showing marks on player
    @FXML void marksPlayer1(ActionEvent event) {
        genericWindows.showMarks(1);
    }

    @FXML void marksPlayer2(ActionEvent event) {
        genericWindows.showMarks(2);
    }

    @FXML void marksPlayer3(ActionEvent event) {
        genericWindows.showMarks(3);
    }

    @FXML void marksPlayer4(ActionEvent event) {
        genericWindows.showMarks(4);
    }

    @FXML void marksPlayer5(ActionEvent event) {
        genericWindows.showMarks(5);
    }


    //Handling clicks on weapons on board
    @FXML void redWeapon1Click(MouseEvent event) {
        genericWindows.showWeapon(1);

    }
    @FXML void redWeapon2Click(MouseEvent event) {
        genericWindows.showWeapon(2);

    }
    @FXML void redWeapon3Click(MouseEvent event) {
        genericWindows.showWeapon(3);

    }
    @FXML void yellowWeapon1Click(MouseEvent event) {
        genericWindows.showWeapon(4);

    }
    @FXML void yellowWeapon2Click(MouseEvent event) {
        genericWindows.showWeapon(5);

    }
    @FXML void yellowWeapon3Click(MouseEvent event) {
        genericWindows.showWeapon(6);

    }
    @FXML void blueWeapon1Click(MouseEvent event) {
        genericWindows.showWeapon(7);

    }
    @FXML void blueWeapon2Click(MouseEvent event) {
        genericWindows.showWeapon(8);

    }
    @FXML void blueWeapon3Click(MouseEvent event) {
        genericWindows.showWeapon(9);

    }
}