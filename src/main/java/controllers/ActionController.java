package controllers;

import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import models.cards.Weapon;
import models.cards.WeaponAction;
import models.cards.WeaponEffect;

import java.util.ArrayList;
import java.util.HashMap;
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
        DIRECTION
    }

    public GameController gameController;
    public GameBoard gameBoardModel;
    public Player player;

    public ActionController (GameController gameController) {
        this.gameController = gameController;
        this.gameBoardModel = gameController.getGameBoard();
        this.player = gameBoardModel.getActivePlayer();
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

    public void shoot (String weaponName, Integer effectType, ClientInput clientInput) {
        Weapon weapon = player.getWeaponByName(weaponName);
        if (!weapon.isLoaded()) throw new IllegalArgumentException("Weapon is not loaded.");
        WeaponEffect effect = weapon.getEffect(effectType);

        if (effect.getCost() != null) {
            weapon.payEffect(player, effect);
        }

        // execute actions
        for (WeaponAction weaponAction: effect.getActions()) {
            WeaponAction.Type actionType = weaponAction.getType();
            HashMap<WeaponEffect.Input, Integer> parameters = weaponAction.getParameters();

            // reset the parameters just to be sure there's no old data
            weapon.reset();
            parameters.forEach((WeaponEffect.Input type, Integer index) -> {
                switch (type) {
                    case TARGET:
                        weapon.addPlayerTarget(clientInput.getPlayers(gameBoardModel).get(index));
                        return;
                    case POSITION:
                        weapon.addPosition(clientInput.getPositions(gameBoardModel).get(index));
                        return;
                    case DIRECTION:
                        weapon.setDirection(clientInput.getDirection());
                }
            });
            weapon.effect(actionType);

            Player activePlayer = gameBoardModel.getActivePlayer();
            // For all the target players call afterShoot to check adrenaline, if it's dead, ...
            weapon.getPlayerTargets().forEach(p -> activePlayer.afterShoot(gameBoardModel, p));
            weapon.reset();
        }

    }
}