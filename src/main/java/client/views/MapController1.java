package client.views;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import messages.GameStateMessage;
import messages.client_data.*;
import models.cards.Weapon;
import utils.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    @FXML private ImageView slot1Square0;
    @FXML private ImageView slot2Square0;
    @FXML private ImageView slot3Square0;
    @FXML private ImageView slot4Square0;
    @FXML private ImageView slot5Square0;
    @FXML private ImageView slot1Square1;
    @FXML private ImageView slot2Square1;
    @FXML private ImageView slot3Square1;
    @FXML private ImageView slot4Square1;
    @FXML private ImageView slot5Square1;
    @FXML private ImageView slot1Square2;
    @FXML private ImageView slot2Square2;
    @FXML private ImageView slot3Square2;
    @FXML private ImageView slot4Square2;
    @FXML private ImageView slot5Square2;
    @FXML private ImageView slot1Square4;
    @FXML private ImageView slot2Square4;
    @FXML private ImageView slot3Square4;
    @FXML private ImageView slot4Square4;
    @FXML private ImageView slot5Square4;
    @FXML private ImageView slot1Square5;
    @FXML private ImageView slot2Square5;
    @FXML private ImageView slot3Square5;
    @FXML private ImageView slot4Square5;
    @FXML private ImageView slot5Square5;
    @FXML private ImageView slot1Square6;
    @FXML private ImageView slot2Square6;
    @FXML private ImageView slot3Square6;
    @FXML private ImageView slot4Square6;
    @FXML private ImageView slot5Square6;
    @FXML private ImageView slot1Square7;
    @FXML private ImageView slot2Square7;
    @FXML private ImageView slot3Square7;
    @FXML private ImageView slot4Square7;
    @FXML private ImageView slot5Square7;
    @FXML private ImageView slot1Square8;
    @FXML private ImageView slot2Square8;
    @FXML private ImageView slot3Square8;
    @FXML private ImageView slot4Square8;
    @FXML private ImageView slot5Square8;
    @FXML private ImageView slot1Square9;
    @FXML private ImageView slot2Square9;
    @FXML private ImageView slot3Square9;
    @FXML private ImageView slot4Square9;
    @FXML private ImageView slot5Square9;
    @FXML private ImageView slot1Square10;
    @FXML private ImageView slot2Square10;
    @FXML private ImageView slot3Square10;
    @FXML private ImageView slot4Square10;
    @FXML private ImageView slot5Square10;
    @FXML private ImageView slot1Square11;
    @FXML private ImageView slot2Square11;
    @FXML private ImageView slot3Square11;
    @FXML private ImageView slot4Square11;
    @FXML private ImageView slot5Square11;
    @FXML private HBox HB0;
    @FXML private HBox HB1;
    @FXML private HBox HB2;
    @FXML private HBox HB4;
    @FXML private HBox HB5;
    @FXML private HBox HB6;
    @FXML private HBox HB7;
    @FXML private HBox HB8;
    @FXML private HBox HB9;
    @FXML private HBox HB10;
    @FXML private HBox HB11;
    @FXML private TextArea textArea;
    @FXML private Button endTurnButton;

    //These integers contain the players positions on the map, as square numbers
    Integer player1Position = null;
    Integer player2Position = null;
    Integer player3Position = null;
    Integer player4Position = null;
    Integer player5Position = null;

    // The players images
    ImageView player1Image = null;
    ImageView player2Image = null;
    ImageView player3Image = null;
    ImageView player4Image = null;
    ImageView player5Image = null;

    //Updates game values with message received by server
    public void updateWithData(GameStateMessage gameStateMessage){
        drawPlayers(gameStateMessage.gameBoardData.players);
        drawCurrentPlayer(gameStateMessage.playerYouData);
        drawWeaponSlots(gameStateMessage.gameBoardData.squares);
    }

    public void drawCurrentPlayer (PlayerYouData currentPlayer) {
        //Set totalpoints and actioncounter
        totalPoints.setText(String.valueOf(currentPlayer.totalPoints));
        actionCounter.setText(String.valueOf(currentPlayer.actionCounter));

        drawCubes(currentPlayer);
    }

    public void drawPlayers (ArrayList<PlayerOtherData> players) {
        //For-switch that gets the player position for each player and assigns it to the Integers above.
        //Then sends the player number and position to the loadPlayerOnMap function
        //IF ELSE: if the position of a player is the same as gamestatemessage, break from switch, do nothing.
        //If the position of a player is different than gamestatemessage, unloads all players
        // and call loadplayeronmap and load image.
        // Also sets the labels for username and status

        int pIndex = 0;
        Label usernameLabel = null;
        Label playerStatus = null;

        for (PlayerOtherData player : players) {
            switch (pIndex) {
                case 0:
                    usernameLabel = username1;
                    playerStatus = player1Active;

                    if (player.position != null && !player.position.equals(player1Position)) {
                        unloadPlayerFromMap(player1Position, player1Image);
                        player1Position = player.position;
                        loadPlayerOnMap(pIndex, player1Position);
                    }

                    break;
                case 1:
                    usernameLabel = username2;
                    playerStatus = player2Active;

                    if (player.position != null && !player.position.equals(player2Position)) {
                        unloadPlayerFromMap(player2Position, player2Image);
                        player2Position = player.position;
                        loadPlayerOnMap(pIndex, player2Position);
                    }
                    break;
                case 2:
                    usernameLabel = username3;
                    playerStatus = player3Active;

                    if (player.position != null && !player.position.equals(player3Position))  {
                        unloadPlayerFromMap(player3Position, player3Image);
                        player3Position = player.position;
                        loadPlayerOnMap(pIndex, player3Position);
                    }

                    break;
                case 3:
                    usernameLabel = username4;
                    playerStatus = player4Active;

                    if (player.position != null && !player.position.equals(player4Position)) {
                        unloadPlayerFromMap(player4Position, player4Image);
                        player4Position = player.position;
                        loadPlayerOnMap(pIndex, player4Position);
                    }
                    break;
                case 4:
                    usernameLabel = username5;
                    playerStatus = player5Active;

                    if (player.position != null && !player.position.equals(player5Position)) {
                        unloadPlayerFromMap(player5Position, player5Image);
                        player5Position = player.position;
                        loadPlayerOnMap(pIndex, player5Position);
                    }
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

        //At the end of this cycle the integers above contain the player positions and
        //if position change is detected, the old images on the map are unloaded and
        //new images are loaded in the correct VBOX/IMAGEVIEW/IMAGE
    }

    public void drawCubes (PlayerYouData currentPlayer) {
        //Set ammo on GUI
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

    }

    public void drawWeaponSlots (ArrayList<SquareData> squares) {
        //Sets the weapons in weaponslots
        ArrayList<WeaponsSlotData> weaponsSlots = new ArrayList<>(squares.stream()
                .filter(s -> s.isSpawnPoint == true)
                .map(s -> s.weaponsSlot)
                .collect(Collectors.toList()));


        for (WeaponsSlotData slot : weaponsSlots) {
            drawWeaponSlot(slot);
        }
    }

    public void drawWeaponSlot (WeaponsSlotData slot) {
        ArrayList<WeaponData> weapons = slot.weapons;
        Label weapon1;
        Label weapon2;
        Label weapon3;

        if (slot.color.equals("RED")) {
            weapon1 = redWeapon1;
            weapon2 = redWeapon2;
            weapon3 = redWeapon3;
        } else if (slot.color.equals("YELLOW")) {
            weapon1 = yellowWeapon1;
            weapon2 = yellowWeapon2;
            weapon3 = yellowWeapon3;
        } else { // BLUE
            weapon1 = blueWeapon1;
            weapon2 = blueWeapon2;
            weapon3 = blueWeapon3;
        }

        if (!weapons.isEmpty()) {
            weapon1.setText(weapons.get(0).name);
        }
        if (weapons.size() > 1) {
            weapon2.setText(weapons.get(1).name);
        }
        if (weapons.size() > 2) {
            weapon3.setText(weapons.get(2).name);
        }
    }

    //Receives playernumber and position from updateWithGamestate function (that separates each players position from the
    //gamestate message. Loads a JPG with color based on player number and his position.
    public void loadPlayerOnMap(int playerNumber, Integer position) {
        String correctImage = getImageToLoadByPlayerNumber(playerNumber);

        //Selects the location where to load the image.
        //HBOX is the parent of the objects (ImageView) that each load an image representing a player on a square.
        //HB1,2,3,4... are HBOXs on each square. Each HBOX has 5 Imageviews (in case 5 players are on the same square).
        //Each Imageview can load one image, based on player.
        HBox correctHBox = getHBoxByPosition(position);

        //Gets all the imageViews of the correct square (indentified by a HBOX)
        for (Node pictureNode : correctHBox.getChildren()) {
            ImageView picture = (ImageView) pictureNode;
            if (picture.getImage() == null) {
                Image finalPlayerImagePosition = new Image(correctImage);
                picture.setImage(finalPlayerImagePosition);
                if (playerNumber == 0) {
                    player1Image = picture;
                } else if (playerNumber == 1) {
                    player2Image = picture;
                } else if (playerNumber == 2) {
                    player3Image = picture;
                } else if (playerNumber == 3) {
                    player4Image = picture;
                } else if (playerNumber == 4) {
                    player5Image = picture;
                }
                break;
            }
        }
    }

    //Receives playernumber and position.
    //Finds the square based on the position
    //Finds the playerImageColor based on the position.
    //Unloads the player image from the position.
    //For loop that compares each image in that square (5 max possible images) and if
    //the one of those images matches the standard Player image, it removes it from the square.
    public void unloadPlayerFromMap(Integer previousPosition, ImageView previousImage){
        if (previousPosition == null) return;
        HBox correctHBox = getHBoxByPosition(previousPosition);

        for (Node pictureNode : correctHBox.getChildren()) {
            ImageView picture = (ImageView) pictureNode;
            if (picture == previousImage) picture.setImage(null);
        }
    }

    //Gets the correct square based by an int position (example, position 5 means square 5)
    public HBox getHBoxByPosition(int position){

        HBox correctHBox = new HBox();

        switch (position) {
            case 0:
                correctHBox = HB0;
                break;
            case 1:
                correctHBox = HB1;
                break;
            case 2:
                correctHBox = HB2;
                break;
            case 3:
                break;
            case 4:
                correctHBox = HB4;
                break;
            case 5:
                correctHBox = HB5;
                break;
            case 6:
                correctHBox = HB6;
                break;
            case 7:
                correctHBox = HB7;
                break;
            case 8:
                correctHBox = HB8;
                break;
            case 9:
                correctHBox = HB9;
                break;
            case 10:
                correctHBox = HB10;
                break;
            case 11:
                correctHBox = HB11;
                break;
        }
        return correctHBox;
    }

    public String getImageToLoadByPlayerNumber(int playerNumber) {

        String correctImage = "/JPGs";

        //Selects the correct image to be loaded, based on player order/number
        switch (playerNumber) {
            case 0:
                correctImage = correctImage + "/GameGray.jpg";
                break;
            case 1:
                correctImage = correctImage + "/GameBlue.jpg";
                break;
            case 2:
                correctImage = correctImage + "/GamePurple.jpg";
                break;
            case 3:
                correctImage = correctImage + "/GameGreen.jpg";
                break;
            case 4:
                correctImage = correctImage + "/GameYellow.jpg";
                break;
        }
        return correctImage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textArea.setEditable(false);//text area cant be changed by user, it only receives messages to print.

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
    @FXML public void quitGame(){
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

    @FXML void openWeaponsWindow(ActionEvent event) {
        genericWindows.availableWeapons();
    }

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

    @FXML void endTurn(){
        genericWindows.endTurn();
    }
    @FXML void discardAndSpawn(){
        genericWindows.spawn();
    }
}