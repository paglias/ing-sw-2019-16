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
    @FXML private Label userName1;
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
    @FXML private Text redAmmo;
    @FXML private Text blueAmmo;
    @FXML private Text yellowAmmo;
    @FXML private Text moveCounter;
    @FXML private ImageView skullOne;
    @FXML private ImageView skullTwo;
    @FXML private ImageView skullThree;
    @FXML private ImageView skullFour;
    @FXML private ImageView skullFive;
    @FXML private ImageView skullSix;
    @FXML private ImageView skullSeven;
    @FXML private ImageView skullEight;

    //Updates game values with message received by server
    public void updateWithData(GameStateMessage gameStatus){
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    @FXML void grabAction(ActionEvent event) {}



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