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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import messages.GameStateMessage;
import messages.client_data.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MapController1 extends AbstractView implements Initializable {

    private GenericWindows genericWindows = new GenericWindows();

    @FXML private ImageView image1;
    @FXML private Button endTurnButton;
    @FXML private AnchorPane imageAnchorPane;
    @FXML private SplitPane horizontalSplit;
    @FXML private SplitPane verticalSplit;
    @FXML private SplitPane horizontalSplit2;
    @FXML private Label username5;
    @FXML private Label username1;
    @FXML private Label username2;
    @FXML private Label username3;
    @FXML private Label username4;
    @FXML private Label redWeapon1;
    @FXML private Label redWeapon2;
    @FXML private Label redWeapon3;
    @FXML private Label blueWeapon1;
    @FXML private Label blueWeapon2;
    @FXML private Label blueWeapon3;
    @FXML private Label yellowWeapon1;
    @FXML private Label yellowWeapon2;
    @FXML private Label yellowWeapon3;
    @FXML private Button actionsButton;
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
    @FXML private GridPane playerOneDamage;
    @FXML private GridPane playerTwoDamage;
    @FXML private GridPane playerThreeDamage;
    @FXML private GridPane playerFourDamage;
    @FXML private GridPane playerFiveDamage;

    //These integers contain the players positions on the map, as square numbers
    private Integer player1Position = null;
    private Integer player2Position = null;
    private Integer player3Position = null;
    private Integer player4Position = null;
    private Integer player5Position = null;

    // The players images
    private ImageView player1Image = null;
    private ImageView player2Image = null;
    private ImageView player3Image = null;
    private ImageView player4Image = null;
    private ImageView player5Image = null;

    //Updates game values with message received by server
    public void updateWithData(GameStateMessage gameStateMessage) {
        drawPlayers(gameStateMessage.gameBoardData.players);
        drawCurrentPlayer(gameStateMessage.playerYouData);
        drawWeaponSlots(gameStateMessage.gameBoardData.squares);
        drawSkulls(gameStateMessage.gameBoardData.skullsN);
        drawDamage(gameStateMessage);

        StringBuilder textAreaBuilder = new StringBuilder();

        if (textArea.getText() != null) {
            textAreaBuilder.append(textArea.getText());
        }

        for (String msg : gameStateMessage.actionsHistory) {
            textAreaBuilder.append(msg);
            textAreaBuilder.append('\n');
        }

        textArea.setText(textAreaBuilder.toString());

        if (gameStateMessage.playerYouData.isActive){
            actionsButton.setDisable(false);
            endTurnButton.setDisable(false);
            weaponsButton.setDisable(false);
        }
    }

    public void drawCurrentPlayer(PlayerYouData currentPlayer) {
        //Set totalpoints and actioncounter
        totalPoints.setText(String.valueOf(currentPlayer.totalPoints));
        actionCounter.setText(String.valueOf(currentPlayer.actionCounter));

        drawCubes(currentPlayer);
    }

    public void drawPlayers(List<PlayerOtherData> players) {
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

                    if (player.position != null && !player.position.equals(player3Position)) {
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

    public void drawCubes(PlayerYouData currentPlayer) {
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

    public void drawWeaponSlots(List<SquareData> squares) {
        //Sets the weapons in weaponslots
        ArrayList<WeaponsSlotData> weaponsSlots = new ArrayList<>(squares.stream()
                .filter(s -> s.isSpawnPoint)
                .map(s -> s.weaponsSlot)
                .collect(Collectors.toList()));


        for (WeaponsSlotData slot : weaponsSlots) {
            drawWeaponSlot(slot);
        }
    }

    public void drawWeaponSlot(WeaponsSlotData slot) {
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
    public void unloadPlayerFromMap(Integer previousPosition, ImageView previousImage) {
        if (previousPosition == null) return;
        HBox correctHBox = getHBoxByPosition(previousPosition);

        for (Node pictureNode : correctHBox.getChildren()) {
            ImageView picture = (ImageView) pictureNode;
            if (picture == previousImage) picture.setImage(null);
        }
    }

    //Gets the correct square based on an int position (example, position 5 means square 5)
    public HBox getHBoxByPosition(int position) {

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

    //Returns correct imagePath based on player number received (index in arraylist)
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

    //loads skulls on map
    public void drawSkulls(int skulls) {
        int i;
        String skullPath = "/JPGs/Skull.png";
        Image skullImage = new Image(skullPath);
        for (i = 0; i < skulls; i++) {
            switch (i) {
                case 0:
                    skullOne.setImage(skullImage);
                    break;
                case 1:
                    skullTwo.setImage(skullImage);
                    break;
                case 2:
                    skullThree.setImage(skullImage);
                    break;
                case 3:
                    skullFour.setImage(skullImage);
                    break;
                case 4:
                    skullFive.setImage(skullImage);
                    break;
                case 5:
                    skullSix.setImage(skullImage);
                    break;
                case 6:
                    skullSeven.setImage(skullImage);
                    break;
                case 7:
                    skullEight.setImage(skullImage);
            }
        }
    }

    //Adds damage on each player based on gamestatemessage
    public void drawDamage(GameStateMessage gameState) {

        ArrayList<PlayerOtherData> players = gameState.gameBoardData.players;

        int index = 0;
        for (PlayerOtherData player : players) {
            switch(index) {
                case 0:
                    drawDamageOnOnePlayer(playerOneDamage, player);
                    break;
                case 1:
                    drawDamageOnOnePlayer(playerTwoDamage, player);
                    break;
                case 2:
                    drawDamageOnOnePlayer(playerThreeDamage, player);
                    break;
                case 3:
                    drawDamageOnOnePlayer(playerFourDamage, player);
                    break;
                case 4:
                    drawDamageOnOnePlayer(playerFiveDamage, player);
                    break;
                case 5:
                    drawDamageOnOnePlayer(playerOneDamage, player);
                    break;
            }
            index++;
        }
    }

    //Used for one player at a time
    //Receives the correct playerboard and the correct player to get the damage from.
    // (damage is an arraylist of nicknames)
    //For each nickname inside the player damage, creates a borderpane, loads an ImageView inside the gridpane
    //The loading location varies based on the position of the Arraylist damage
    //Also checks if the borderpane is empty before loading.
    public void drawDamageOnOnePlayer(GridPane playerboard, PlayerOtherData player){
        int index=0;
        for (String damagingPlayer : player.damage) {
            switch (index) {
                case 0:
                    ImageView imageView;
                    BorderPane pane= new BorderPane();
                    imageView = getImageViewToLoad(damagingPlayer);

                    if (pane.getCenter()!= null){
                        pane.setCenter(null);
                    }

                    pane.setCenter(imageView);
                    imageView.fitWidthProperty().bind(pane.widthProperty());
                    playerboard.add(pane, 0, 0);
                    break;

                case 1:
                    ImageView imageView1;
                    BorderPane pane1= new BorderPane();
                    imageView1 = getImageViewToLoad(damagingPlayer);

                    if (pane1.getCenter()!= null) {
                        pane1.setCenter(null);
                    }
                    pane1.setCenter(imageView1);
                    imageView1.fitWidthProperty().bind(pane1.widthProperty());
                    playerboard.add(pane1, 1, 0);
                    break;

                case 2:
                    ImageView imageView2;
                    BorderPane pane2= new BorderPane();
                    imageView2 = getImageViewToLoad(damagingPlayer);

                    if (pane2.getCenter()!= null) {
                        pane2.setCenter(null);
                    }
                    pane2.setCenter(imageView2);
                    imageView2.fitWidthProperty().bind(pane2.widthProperty());
                    playerboard.add(pane2, 2, 0);
                    break;

                case 3:
                    ImageView imageView3;
                    BorderPane pane3= new BorderPane();
                    imageView3 = getImageViewToLoad(damagingPlayer);

                    if (pane3.getCenter()!= null) {
                        pane3.setCenter(null);
                    }
                    pane3.setCenter(imageView3);
                    imageView3.fitWidthProperty().bind(pane3.widthProperty());
                    playerboard.add(pane3, 3, 0);
                    break;

                case 4:
                    ImageView imageView4;
                    BorderPane pane4= new BorderPane();
                    imageView4 = getImageViewToLoad(damagingPlayer);

                    if (pane4.getCenter()!= null) {
                        pane4.setCenter(null);
                    }
                    pane4.setCenter(imageView4);
                    imageView4.fitWidthProperty().bind(pane4.widthProperty());
                    playerboard.add(pane4, 4, 0);
                    break;

                case 5:
                    ImageView imageView5;
                    BorderPane pane5= new BorderPane();
                    imageView5 = getImageViewToLoad(damagingPlayer);

                    if (pane5.getCenter()!= null) {
                        pane5.setCenter(null);
                    }
                    pane5.setCenter(imageView5);
                    imageView5.fitWidthProperty().bind(pane5.widthProperty());
                    playerboard.add(pane5, 5, 0);
                    break;

                case 6:
                    ImageView imageView6;
                    BorderPane pane6= new BorderPane();
                    imageView6 = getImageViewToLoad(damagingPlayer);

                    if (pane6.getCenter()!= null) {
                        pane6.setCenter(null);
                    }
                    pane6.setCenter(imageView6);
                    imageView6.fitWidthProperty().bind(pane6.widthProperty());
                    playerboard.add(pane6, 6, 0);
                    break;

                case 7:
                    ImageView imageView7;
                    BorderPane pane7= new BorderPane();
                    imageView7 = getImageViewToLoad(damagingPlayer);

                    if (pane7.getCenter()!= null) {
                        pane7.setCenter(null);
                    }
                    pane7.setCenter(imageView7);
                    imageView7.fitWidthProperty().bind(pane7.widthProperty());
                    playerboard.add(pane7, 7, 0);
                    break;

                case 8:
                    ImageView imageView8;
                    BorderPane pane8= new BorderPane();
                    imageView8 = getImageViewToLoad(damagingPlayer);

                    if (pane8.getCenter()!= null) {
                        pane8.setCenter(null);
                    }
                    pane8.setCenter(imageView8);
                    imageView8.fitWidthProperty().bind(pane8.widthProperty());
                    playerboard.add(pane8, 8, 0);
                    break;

                case 9:
                    ImageView imageView9;
                    BorderPane pane9= new BorderPane();
                    imageView9 = getImageViewToLoad(damagingPlayer);

                    if (pane9.getCenter()!= null) {
                        pane9.setCenter(null);
                    }
                    pane9.setCenter(imageView9);
                    imageView9.fitWidthProperty().bind(pane9.widthProperty());
                    playerboard.add(pane9, 9, 0);
                    break;

                case 10:
                    ImageView imageView10;
                    BorderPane pane10= new BorderPane();
                    imageView10 = getImageViewToLoad(damagingPlayer);

                    if (pane10.getCenter()!= null) {
                        pane10.setCenter(null);
                    }
                    pane10.setCenter(imageView10);
                    imageView10.fitWidthProperty().bind(pane10.widthProperty());
                    playerboard.add(pane10, 10, 0);
                    break;

                case 11:
                    ImageView imageView11;
                    BorderPane pane11= new BorderPane();
                    imageView11 = getImageViewToLoad(damagingPlayer);

                    if (pane11.getCenter()!= null) {
                        pane11.setCenter(null);
                    }
                    pane11.setCenter(imageView11);
                    imageView11.fitWidthProperty().bind(pane11.widthProperty());
                    playerboard.add(pane11, 11, 0);
                    break;
            }
            index++;
        }
    }

    //Receives string/nicknames of a player
    //Returns imageView object containing Image of the correct player
    public ImageView getImageViewToLoad(String damagingPlayer) {

        ImageView imageView = new ImageView();
        if (damagingPlayer.equals(username1.getText())&& username1.getText()!= null) {
            String imagePlayer = getImageToLoadByPlayerNumber(0);
            Image playerOne = new Image(imagePlayer);
            imageView.setImage(playerOne);
        }
        if (damagingPlayer.equals(username2.getText()) && username2.getText()!= null) {
            String imagePlayer = getImageToLoadByPlayerNumber(1);
            Image playerOne = new Image(imagePlayer);
            imageView.setImage(playerOne);
        }
        if (damagingPlayer.equals(username3.getText()) && username3.getText()!= null) {
            String imagePlayer = getImageToLoadByPlayerNumber(2);
            Image playerOne = new Image(imagePlayer);
            imageView.setImage(playerOne);
        }
        if (damagingPlayer.equals(username4.getText()) && username4.getText()!= null) {
            String imagePlayer = getImageToLoadByPlayerNumber(3);
            Image playerOne = new Image(imagePlayer);
            imageView.setImage(playerOne);
        }
        if (damagingPlayer.equals(username5.getText()) && username5.getText()!= null) {
            String imagePlayer = getImageToLoadByPlayerNumber(4);
            Image playerOne = new Image(imagePlayer);
            imageView.setImage(playerOne);
        }
        return imageView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        actionsButton.setDisable(true);
        endTurnButton.setDisable(true);
        weaponsButton.setDisable(true);

        textArea.setEditable(false);//text area cant be changed by user, it only receives messages to print.

        Game.controller.registerCurrentView(this);

        //Adjusts map size correctly
        image1.fitWidthProperty().bind(imageAnchorPane.widthProperty());
        image1.fitHeightProperty().bind(imageAnchorPane.heightProperty());

        //Locks all splitPanes so they cannot be moved
        ArrayList<SplitPane> splitPanes = new ArrayList<>();
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