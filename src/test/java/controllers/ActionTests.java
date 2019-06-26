package controllers;

import messages.GameSettingsMessage;
import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class ActionTests {
    GameBoard gameBoard;
    List<Action> ActionsList= new ArrayList<>();
    ActionController actionController;
    GameController gameController;
    Square square1;
    Square square2;
    Square square3;
    Square square4;
    ClientInput clientInput;
    Player player;


    @BeforeEach
    void setup(){
        gameBoard= new GameBoard();
        gameController= new GameController();
        actionController= new ActionController(gameController);
        clientInput= new ClientInput();
        Player player = new Player();





    }

   /* @Test
   void getPossibleActions(){
        int adrenaline=player.getAdrenaline();
        adrenaline=0;
        boolean finalFrenzy=gameBoard.isFinalFrenzy();
        finalFrenzy= false;


    }*/

    @Test
    void move(){
        GameSettingsMessage msg = new GameSettingsMessage();
        msg.setSkullsNumber(8);
        msg.setMapNumber(1);
        gameController.getGameBoard().addPlayer(player);
        player.setActive(true);
        player.setPosition(square1);
        square1.addCanAccessSquare(square2);
        square1.setNumber(0);
        square2.setNumber(1);
        gameController.setup(msg);
        clientInput.position=1;
        actionController.move(clientInput);
        assertEquals(player.getPosition().getNumber(), 1);
    }





}
