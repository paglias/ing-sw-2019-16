package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import messages.client_data.PlayerOtherData;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//MarksController windows showing how many marks has each player
public class MarksController implements Initializable {

    @FXML Label playerName;
    @FXML Label firstPlayerMarks;
    @FXML Label secondPlayerMarks;
    @FXML Label thirdPlayerMarks;
    @FXML Label fourthPlayerMarks;
    @FXML Label number1Marks;
    @FXML Label number2Marks;
    @FXML Label number3Marks;
    @FXML Label number4Marks;
    @FXML Button backButton;

    private int currentPlayer;
    private String currentPlayerName=null;
    private ArrayList<PlayerOtherData> currentPlayers =
            Game.controller.getLastGameStateMessage().gameBoardData.players;

    public void setCurrentPlayer(int playerNumber) {
        this.currentPlayer = playerNumber;
        currentPlayerName = currentPlayers.get(currentPlayer).nickname;
        playerName.setText(currentPlayerName);
    }

    // Receives A and B (in order). A and B are player numbers, in the list of players in the game (indexes)
    // Calculates how many times marking the second marked the first.
    public int calculateMarks(int markedPlayer, int markingPlayer){
        int i = 0;
        if (currentPlayers.get(markingPlayer)!=null) {
            for (String mark : currentPlayers.get(markedPlayer).marks) {
                if (mark.equals(currentPlayers.get(markingPlayer).nickname)) {
                    i++;
                }
            }
        }
        return i;
    }

    @FXML public void closeWindow(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        switch (currentPlayer) {
            case 0:
                firstPlayerMarks.setText(currentPlayers.get(1).nickname);
                secondPlayerMarks.setText(currentPlayers.get(2).nickname);
                thirdPlayerMarks.setText(currentPlayers.get(3).nickname);
                fourthPlayerMarks.setText(currentPlayers.get(4).nickname);

                number1Marks.setText(Integer.toString(calculateMarks(0,1)));
                number2Marks.setText(Integer.toString(calculateMarks(0,2)));
                number3Marks.setText(Integer.toString(calculateMarks(0,3)));
                number4Marks.setText(Integer.toString(calculateMarks(0,4)));
                break;
            case 1:
                firstPlayerMarks.setText(currentPlayers.get(0).nickname);
                secondPlayerMarks.setText(currentPlayers.get(2).nickname);
                thirdPlayerMarks.setText(currentPlayers.get(3).nickname);
                fourthPlayerMarks.setText(currentPlayers.get(4).nickname);

                number1Marks.setText(Integer.toString(calculateMarks(1,0)));
                number2Marks.setText(Integer.toString(calculateMarks(1,2)));
                number3Marks.setText(Integer.toString(calculateMarks(1,3)));
                number4Marks.setText(Integer.toString(calculateMarks(1,4)));
                break;
            case 2:
                firstPlayerMarks.setText(currentPlayers.get(0).nickname);
                secondPlayerMarks.setText(currentPlayers.get(1).nickname);
                thirdPlayerMarks.setText(currentPlayers.get(3).nickname);
                fourthPlayerMarks.setText(currentPlayers.get(4).nickname);

                number1Marks.setText(Integer.toString(calculateMarks(2,0)));
                number2Marks.setText(Integer.toString(calculateMarks(2,1)));
                number3Marks.setText(Integer.toString(calculateMarks(2,3)));
                number4Marks.setText(Integer.toString(calculateMarks(2,4)));
                break;
            case 3:
                firstPlayerMarks.setText(currentPlayers.get(0).nickname);
                secondPlayerMarks.setText(currentPlayers.get(1).nickname);
                thirdPlayerMarks.setText(currentPlayers.get(2).nickname);
                fourthPlayerMarks.setText(currentPlayers.get(4).nickname);

                number1Marks.setText(Integer.toString(calculateMarks(3,0)));
                number2Marks.setText(Integer.toString(calculateMarks(3,1)));
                number3Marks.setText(Integer.toString(calculateMarks(3,2)));
                number4Marks.setText(Integer.toString(calculateMarks(3,4)));
                break;
            case 4:
                firstPlayerMarks.setText(currentPlayers.get(0).nickname);
                secondPlayerMarks.setText(currentPlayers.get(1).nickname);
                thirdPlayerMarks.setText(currentPlayers.get(2).nickname);
                fourthPlayerMarks.setText(currentPlayers.get(3).nickname);

                number1Marks.setText(Integer.toString(calculateMarks(4,0)));
                number2Marks.setText(Integer.toString(calculateMarks(4,1)));
                number3Marks.setText(Integer.toString(calculateMarks(4,2)));
                number4Marks.setText(Integer.toString(calculateMarks(4,3)));
                break;

        }
    }
}