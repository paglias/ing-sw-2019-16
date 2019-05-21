package controllers;

import models.Player;
import models.Skulls;
import models.Square;

import java.util.ArrayList;
import java.util.List;

public class ClientParameters {
    public Player playerTarget;
    public Square newPosition;
    public Square targetSquare;
    public List<Square> squares;
    public Square.Direction direction;
    public Player secondTarget;
    public Player thirdTarget;
    public List<Player> playerTargets;
}
