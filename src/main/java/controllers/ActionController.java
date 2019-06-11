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
    // Single items that
    public enum ActionItem {
        MOVE,
        GRAB,
        SHOOT,
        RELOAD,
        USE_POWER_UP
    }

    // List of ActionItems
    public enum Action {
        MOVE(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE),
        MOVE_GRAB(ActionItem.MOVE, ActionItem.GRAB),
        SHOOT(ActionItem.SHOOT),
        RELOAD(ActionItem.RELOAD),
        USE_POWER_UP(ActionItem.USE_POWER_UP),

        // Adrenaline actions
        MOVE_SHOOT(ActionItem.MOVE, ActionItem.SHOOT),
        MOVE_MOVE_GRAB(ActionItem.MOVE, ActionItem.MOVE, ActionItem.GRAB),

        // Final frenzy actions
        MOVE_RELOAD_SHOOT(ActionItem.MOVE, ActionItem.RELOAD, ActionItem.SHOOT),
        THREE_MOVE_GRAB(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE, ActionItem.GRAB),
        FOUR_MOVE(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE),
        MOVE_MOVE_RELOAD_SHOOT(ActionItem.MOVE, ActionItem.MOVE, ActionItem.RELOAD, ActionItem.SHOOT);

        private final ActionItem[] actionItems;

        Action(ActionItem ...actionItems) {
            this.actionItems = actionItems;
        }
    }

    public GameController gameController;
    public GameBoard gameBoardModel;
    public Player player;

    public ActionController (GameController gameController) {
        this.gameController = gameController;
        this.gameBoardModel = gameController.getGameBoard();
        this.player = gameBoardModel.getActivePlayer();
    }

    public List<Action> getPossibleActions (){
        List<Action> actionsList = new ArrayList<>();
        actionsList.add(Action.USE_POWER_UP);
        int adrenalineLevel = player.getAdrenaline();

        if(!gameBoardModel.isFinalFrenzy()) {
            if (player.getActionCounter() == 0) {
                actionsList.add(Action.RELOAD);
                return actionsList;
            } else if (adrenalineLevel == 0) {
                actionsList.add(Action.MOVE);
                actionsList.add(Action.MOVE_GRAB);
                actionsList.add(Action.SHOOT);
                return actionsList;
            } else if (adrenalineLevel == 1) {
                actionsList.add(Action.MOVE);
                actionsList.add(Action.MOVE_GRAB);
                actionsList.add(Action.SHOOT);
                actionsList.add(Action.MOVE_MOVE_GRAB);
                return actionsList;

            } else { // adrenaline 2
                actionsList.add(Action.MOVE);
                actionsList.add(Action.MOVE_GRAB);
                actionsList.add(Action.SHOOT);
                actionsList.add(Action.MOVE_MOVE_GRAB);
                actionsList.add(Action.MOVE_SHOOT);
                return actionsList;
            }
        } else {
                if(player.isBeforeFirstPlayer()) {
                    actionsList.add(Action.MOVE_RELOAD_SHOOT);
                    actionsList.add(Action.FOUR_MOVE);
                    actionsList.add(Action.MOVE_MOVE_GRAB);
                    return actionsList;
                } else {
                    actionsList.add(Action.MOVE_MOVE_RELOAD_SHOOT);
                    actionsList.add(Action.THREE_MOVE_GRAB);
                    return actionsList;
                }
        }
    }

    // Single items implementation

    public void move (ClientInput clientInput) {

    }

    public void grab (ClientInput clientInput) {

    }

    public void reload (ClientInput clientInput) {

    }

    public void usePowerUp (ClientInput clientInput) {

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