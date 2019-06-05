package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

//Marks windows showing how many marks has each player
public class Marks implements Initializable {

    @FXML Label currentPlayerMarks;
    @FXML Label firstPlayerMarks;
    @FXML Label secondPlayerMarks;
    @FXML Label thirdPlayerMarks;
    @FXML Label fourthPlayerMarks;
    @FXML Label number1Marks;
    @FXML Label number2Marks;
    @FXML Label number3Marks;
    @FXML Label number4Marks;

    private int currentPlayer;

    public void setCurrentPlayer() {
        GenericWindows alertBox = new GenericWindows();
        this.currentPlayer = alertBox.getCurrentPlayer();
    }

    public void initialize(URL location, ResourceBundle resources) {
        setCurrentPlayer();
        String player = "Player";
        switch (currentPlayer) {
            case 1:
                currentPlayerMarks.setText("1");
                firstPlayerMarks.setText(player + "2");
                secondPlayerMarks.setText(player + "3");
                thirdPlayerMarks.setText(player + "4");
                fourthPlayerMarks.setText(player + "5");
                break;
            case 2:
                currentPlayerMarks.setText("2");
                firstPlayerMarks.setText(player + "1");
                secondPlayerMarks.setText(player + "3");
                thirdPlayerMarks.setText(player + "4");
                fourthPlayerMarks.setText(player + "5");
                break;
            case 3:
                currentPlayerMarks.setText("3");
                firstPlayerMarks.setText(player + "1");
                secondPlayerMarks.setText(player + "2");
                thirdPlayerMarks.setText(player + "4");
                fourthPlayerMarks.setText(player + "5");
                break;
            case 4:
                currentPlayerMarks.setText("4");
                firstPlayerMarks.setText(player + "1");
                secondPlayerMarks.setText(player + "2");
                thirdPlayerMarks.setText(player + "3");
                fourthPlayerMarks.setText(player + "5");
                break;
            case 5:
                currentPlayerMarks.setText("5");
                firstPlayerMarks.setText(player + "1");
                secondPlayerMarks.setText(player + "2");
                thirdPlayerMarks.setText(player + "3");
                fourthPlayerMarks.setText(player + "4");
                break;

        }
    }
    //TODO DYNAMICALLY UPDATE CURRENT MARKS
}
