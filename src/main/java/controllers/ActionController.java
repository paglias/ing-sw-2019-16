package controllers;

import messages.client_data.ClientInput;
import models.GameBoard;
import models.Player;
import models.Square;
import models.cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ActionController {
    // Single items that
    public enum ActionItem {
        MOVE,
        GRAB,
        SHOOT,
        RELOAD,
        USE_POWER_UP,
        DISCARD_AND_SPAWN,
    }

    // List of ActionItems
    public enum Action {
        MOVE(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE),
        MOVE_GRAB(ActionItem.MOVE, ActionItem.GRAB),
        SHOOT(ActionItem.SHOOT),
        RELOAD(ActionItem.RELOAD),
        USE_POWER_UP(ActionItem.USE_POWER_UP),
        DISCARD_AND_SPAWN(ActionItem.DISCARD_AND_SPAWN),

        // Adrenaline actions
        MOVE_SHOOT(ActionItem.MOVE, ActionItem.SHOOT),
        MOVE_MOVE_GRAB(ActionItem.MOVE, ActionItem.MOVE, ActionItem.GRAB),

        // Final frenzy actions
        MOVE_RELOAD_SHOOT(ActionItem.MOVE, ActionItem.RELOAD, ActionItem.SHOOT),
        THREE_MOVE_GRAB(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE, ActionItem.GRAB),
        FOUR_MOVE(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE),
        MOVE_MOVE_RELOAD_SHOOT(ActionItem.MOVE, ActionItem.MOVE, ActionItem.RELOAD, ActionItem.SHOOT);

        private final ArrayList<ActionItem> actionItems;
        public List<ActionItem> getActionItems () { return this.actionItems; }

        Action(ActionItem ...actionItems) {
            this.actionItems = new ArrayList<>(Arrays.asList(actionItems));
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
        actionsList.add(ActionController.Action.USE_POWER_UP);

        if (player.isDead()) actionsList.add(Action.DISCARD_AND_SPAWN);

        int adrenalineLevel = player.getAdrenaline();

        if(!gameBoardModel.isFinalFrenzy()) {
            if (player.getActionCounter() == 0) {
                actionsList.add(ActionController.Action.RELOAD);
                return actionsList;
            } else if (adrenalineLevel == 0) {
                actionsList.add(ActionController.Action.MOVE);
                actionsList.add(ActionController.Action.MOVE_GRAB);
                actionsList.add(ActionController.Action.SHOOT);
                return actionsList;
            } else if (adrenalineLevel == 1) {
                actionsList.add(ActionController.Action.MOVE);
                actionsList.add(ActionController.Action.MOVE_GRAB);
                actionsList.add(ActionController.Action.SHOOT);
                actionsList.add(ActionController.Action.MOVE_MOVE_GRAB);
                return actionsList;

            } else { // adrenaline 2
                actionsList.add(ActionController.Action.MOVE);
                actionsList.add(ActionController.Action.MOVE_GRAB);
                actionsList.add(ActionController.Action.SHOOT);
                actionsList.add(ActionController.Action.MOVE_MOVE_GRAB);
                actionsList.add(ActionController.Action.MOVE_SHOOT);
                return actionsList;
            }
        } else {
                if(player.isBeforeFirstPlayer()) {
                    actionsList.add(ActionController.Action.MOVE_RELOAD_SHOOT);
                    actionsList.add(ActionController.Action.FOUR_MOVE);
                    actionsList.add(ActionController.Action.MOVE_MOVE_GRAB);
                    return actionsList;
                } else {
                    actionsList.add(ActionController.Action.MOVE_MOVE_RELOAD_SHOOT);
                    actionsList.add(ActionController.Action.THREE_MOVE_GRAB);
                    return actionsList;
                }
        }
    }

    // Single items implementation
    public void move (ClientInput clientInput) {
        Square newPosition = gameBoardModel.getSquares().get(clientInput.position);
        player.move(newPosition);
    }

    public void grab (ClientInput clientInput) {
        player.grabItem(clientInput.weaponName);
    }

    public void reload (ClientInput clientInput) {
        Weapon weapon = player.getWeaponByName(clientInput.weaponName);
        player.reload(weapon);
    }

    private void executeAction (Effect effect, CardWithAction card, ClientInput clientInput) {
        for (models.cards.Action action : effect.getActions()) {
            models.cards.Action.Type actionType = action.getType();
            HashMap<Effect.Input, Integer> parameters = action.getParameters();

            // reset the parameters just to be sure there's no old data
            card.reset();
            card.setDamagingPlayer(player);
            parameters.forEach((Effect.Input type, Integer index) -> {
                switch (type) {
                    case TARGET:
                        card.addPlayerTarget(clientInput.getPlayers(gameBoardModel).get(index));
                        return;
                    case POSITION:
                        card.addPosition(clientInput.getPositions(gameBoardModel).get(index));
                        return;
                    case DIRECTION:
                        card.setDirection(clientInput.getDirection());
                }
            });
            card.effect(actionType);

            Player activePlayer = gameBoardModel.getActivePlayer();
            // For all the target players call afterShoot to check adrenaline, if it's dead, ...
            card.getPlayerTargets().forEach(p -> activePlayer.afterShoot(p));
            card.reset();
        }
    }

    public void usePowerUp (ClientInput clientInput) {
        PowerUp powerUp = player.getPowerUps().get(clientInput.powerUpIndex);
        Effect effect = powerUp.getEffects(1).get(0); // A power up has only one primary effect

        // TODO targeting scope dovrebbe essere a pagamento!
        // TODO regole di utilizzo diverse
        if (effect.getCost() != null) {
            // weapon.payEffect(player, effect);
        }

        // execute actions
        executeAction(effect, powerUp, clientInput);
        gameBoardModel.getPowerUpsDeck().discard(powerUp);
    }

    public void discardPowerUpAndSpawn (ClientInput clientInput) {
        PowerUp powerUp = player.getPowerUps().get(clientInput.powerUpIndex);
        gameBoardModel.getPowerUpsDeck().discard(powerUp);

        Square spawnPosition = gameBoardModel.getSquares().stream()
                .filter(s -> s.getColor().toString().equals(powerUp.getColor().toString()) && s.isSpawnPoint())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        player.setPosition(spawnPosition);

        // Make sure to reset the user to the initial state
        player.setDead(false);
        player.getDamage().clear();
    }

    public void shoot (ClientInput clientInput) {
        Weapon weapon = player.getWeaponByName(clientInput.weaponName);
        if (!weapon.isLoaded()) throw new IllegalArgumentException("Weapon is not loaded.");
        Effect effect = weapon.getEffects(clientInput.effectType).get(0); // TODO allow multiple inputs

        if (effect.getCost() != null) {
            weapon.payEffect(player, effect);
        }

        // execute actions
        executeAction(effect, weapon, clientInput);
    }
}