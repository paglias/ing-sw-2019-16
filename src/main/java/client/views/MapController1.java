package client.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Adjusts map size correctly
        image1.fitWidthProperty().bind(imageAnchorPane.widthProperty());
        image1.fitHeightProperty().bind(imageAnchorPane.heightProperty());

        //Locks all splitpanes so they cannot be moved

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

    @FXML
    public void timerPrint(){
        alertBoxes.timerPrint();
    }

    @FXML
    public void openPowerUpsWindow(){
    }

    @FXML
    public void confirmAction(){
    }

    @FXML
    public void confirmPowerUp(){
    }

    @FXML
    public void grabAction() {
    }

    @FXML
    public void drawPowerUp(){
    }

    @FXML
    public void marksPlayer1(){
    }

    @FXML
    public void marksPlayer2(){
    }

    @FXML
    public void marksPlayer3(){
    }

    @FXML
    public void marksPlayer4(){
    }

    @FXML
    public void marksPlayer5(){
    }
}