package controllers;

import models.GameBoard;
import models.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionController {
    public enum Action{
        MOVE,
        GRAB,
        SHOOT,
        RELOAD
    }
    public enum UserInputs {
        POSITION,
        WEAPON_NAME,
        PLAYER_TARGET,
        PLAYER_TARGETS,
        SECOND_TARGET,
        THIRD_TARGET,
        DIRECTION,
    }
    // Normal actions
    public Action[] moveAction={Action.MOVE, Action.MOVE, Action.MOVE};
    public Action[] moveGrabAction={Action.MOVE,Action.GRAB};
    public Action[] shootAction={Action.SHOOT};
    public Action[] reloadAction= {Action.RELOAD};

    // Adrenaline actions
    public Action[] moveShootAction={Action.MOVE,Action.SHOOT};
    public Action[] moveMoveGrabAction= {Action.MOVE,Action.MOVE,Action.GRAB};

    // Final frenzy actions
    public Action[] moveReloadShootAction={Action.MOVE, Action.RELOAD, Action.SHOOT};
    public Action[] threeMovesGrabAction={Action.MOVE,Action.MOVE,Action.MOVE,Action.GRAB};
    public Action[] moveMoveAction={Action.MOVE,Action.MOVE,Action.MOVE,Action.MOVE};
    public Action[] moveMoveReloadShootAction= {Action.MOVE,Action.MOVE,Action.RELOAD,Action.SHOOT};

    public List<Action[]> getPossibleActions(Player player, GameBoard gameBoard){
        List<Action[]> actionsList= new ArrayList<>();
        int adrenalineLevel = player.getAdrenaline();

        if(!gameBoard.isFinalFrenzy()) {
            if (player.getActionCounter() == 0) {
                actionsList.add(reloadAction);
                return actionsList;

            } else if (adrenalineLevel == 0) {
                actionsList.add(moveAction);
                actionsList.add(moveGrabAction);
                actionsList.add(shootAction);
                return actionsList;

            } else if (adrenalineLevel == 1) {
                actionsList.add(moveAction);
                actionsList.add(moveGrabAction);
                actionsList.add(shootAction);
                actionsList.add(moveMoveGrabAction);
                return actionsList;

            } else { // adrenaline 2
                actionsList.add(moveAction);
                actionsList.add(moveGrabAction);
                actionsList.add(shootAction);
                actionsList.add(moveMoveGrabAction);
                actionsList.add(moveShootAction);
                return actionsList;
            }
        } else {
                if(player.isBeforeFirstPlayer()) {
                    actionsList.add(moveReloadShootAction);
                    actionsList.add(moveMoveAction);
                    actionsList.add(moveMoveGrabAction);
                    return actionsList;
                } else {
                    actionsList.add(moveMoveReloadShootAction);
                    actionsList.add(threeMovesGrabAction);
                    return actionsList;
                }
        }
   }
}