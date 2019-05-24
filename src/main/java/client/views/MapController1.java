package client.views;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController1 implements Initializable {
    AlertBoxes alertBoxes = new AlertBoxes();

    @FXML
    ImageView image1;
    @FXML
    AnchorPane imageAnchorPane;
    @FXML
    Button quitButton;
    @FXML
    SplitPane horizontalSplit;
    @FXML
    SplitPane verticalSplit;
    @FXML
    SplitPane horizontalSplit2;

    @FXML private AnchorPane upperLeftPane;

    @FXML private Button settingsButton;

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

    @FXML private TextField actionChosen;

    @FXML private Button confirmAction;

    @FXML private Button powerUpsButton;

    @FXML private TextField powerUpChosen;

    @FXML private Button confirmPowerUp;

    @FXML private Button grabButton;

    @FXML private Text redAmmo;

    @FXML private Text blueAmmo;

    @FXML private Text yellowAmmo;

    @FXML private Text moveCounter;


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

    //Show possible settings? //TODO
    @FXML
    public void showSettings(){
        alertBoxes.settings();
    }

    //Alert box for quitting the game
    @FXML
    public void quitGame(){
        alertBoxes.quitGame();
    }

    //Opens a window with the actions available. Many buttons will be disabled.
    @FXML
    public void openActionWindow(){
        alertBoxes.actionWindow();
    }


    //Buttons methods
    @FXML void openPowerUpsWindow(ActionEvent event) {}

    @FXML void confirmAction(ActionEvent event) {}

    @FXML void confirmPowerUp(ActionEvent event) {}

    @FXML void drawPowerUp(ActionEvent event) {}

    @FXML void grabAction(ActionEvent event) {}



    //Opens window showing marks on player
    @FXML void marksPlayer1(ActionEvent event) {
        alertBoxes.showMarks(1);
    }

    @FXML void marksPlayer2(ActionEvent event) {}

    @FXML void marksPlayer3(ActionEvent event) {}

    @FXML void marksPlayer4(ActionEvent event) {}

    @FXML void marksPlayer5(ActionEvent event) {}



    //Handling clicks on weapons on board
    @FXML void redWeapon1Click(MouseEvent event) {}

    @FXML void redWeapon2Click(MouseEvent event) {}

    @FXML void redWeapon3Click(MouseEvent event) {}

    @FXML void yellowWeapon1Click(MouseEvent event) {}

    @FXML void yellowWeapon2Click(MouseEvent event) {}

    @FXML void yellowWeapon3Click(MouseEvent event) {}

    @FXML void blueWeapon1Click(MouseEvent event) {}

    @FXML void blueWeapon2Click(MouseEvent event) {}

    @FXML void blueWeapon3Click(MouseEvent event) {}
}