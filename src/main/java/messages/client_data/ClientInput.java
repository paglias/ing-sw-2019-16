package messages.client_data;

import models.GameBoard;
import models.Player;
import models.Square;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ClientInput {
    // Use powerup action
    public Integer powerUpIndex;

    // Move action
    public Integer position;

    // Grab & Shoot & Reload action
    public String weaponName;

    // Shoot action
    public Integer effectType;
    public ArrayList<String> players;
    public ArrayList<Integer> positions;
    public String direction;

    public ArrayList<Player> getPlayers (GameBoard gameBoard) {
        return new ArrayList<>(
                players
                        .stream()
                        .map(gameBoard::getPlayerByNickname)
                        .collect(Collectors.toList())
        );
    }

    public ArrayList<Square> getPositions (GameBoard gameBoard) {
        return new ArrayList<>(
                positions
                        .stream()
                        .map(pos -> gameBoard.getSquares().get(pos))
                        .collect(Collectors.toList())
        );
    }

    public Square.Direction getDirection () {
        return Square.Direction.valueOf(direction);
    }
}
