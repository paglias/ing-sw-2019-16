package messages.client_data;

import models.GameBoard;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class GameBoardData {
    public Boolean gameSetup; // If the game is setup (map and n of skulls chosen)
    public Boolean gameStarted; // If the game has started
    public int nMap; // The number of the map used
    public int skullsN; // The number of skulls
    public ArrayList<String> killers; // A list of the players that killed other players (list of nicknames)
    public ArrayList<PlayerOtherData> players; // A list of PlayerOther
    public Boolean isFinalFrenzy; // If we're during a final frenzy
    public ArrayList<SquareData> squares; // List of squares objects (with public info)

    /**
     *
     *   Class used to represent the GameBoard on the client
     *  Contains all the data from the GameBoard model that can be shown to all users
     *  And optimized for usage on the client
     * Every attribute is public to make it easier to create it
     *
     * @param gameBoard the game board
     */
    public GameBoardData (GameBoard gameBoard) {
        gameSetup = gameBoard.isGameSetup();
        gameStarted = gameBoard.hasStarted();
        nMap = gameBoard.getMap();
        skullsN = gameBoard.getSkulls().getNRemaining();
        killers = new ArrayList<>(gameBoard.getSkulls().getKillers().stream()
                    .map(p -> p.getNickname()).collect(Collectors.toList()));
        players = new ArrayList<>(gameBoard.getPlayers().stream()
                .map(p -> new PlayerOtherData(p)).collect(Collectors.toList()));
        isFinalFrenzy = gameBoard.isFinalFrenzy();
        squares = new ArrayList<>(gameBoard.getSquares().stream()
                .map(s -> new SquareData(s)).collect(Collectors.toList()));
    }
}
