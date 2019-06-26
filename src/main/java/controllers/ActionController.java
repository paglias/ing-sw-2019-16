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
        DISCARD,
    }

    // List of ActionItems
    public enum Action {
        MOVE(ActionItem.MOVE, ActionItem.MOVE, ActionItem.MOVE),
        MOVE_GRAB(ActionItem.MOVE, ActionItem.GRAB),
        SHOOT(ActionItem.SHOOT),
        RELOAD(ActionItem.RELOAD),
        USE_POWER_UP(ActionItem.USE_POWER_UP),
        DISCARD_AND_SPAWN(ActionItem.DISCARD_AND_SPAWN),
        DISCARD(ActionItem.DISCARD),

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

    /**
     * Get possible actions list.
     *
     * @return the list
     */
    public List<Action> getPossibleActions (){
        List<Action> actionsList = new ArrayList<>();

        actionsList.add(ActionController.Action.USE_POWER_UP);
        actionsList.add(ActionController.Action.DISCARD);

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

    /**
     * Move.
     *
     * @param clientInput the client input
     */
// Single items implementation
    public void move (ClientInput clientInput) {
        Square newPosition = gameBoardModel.getSquares().get(clientInput.position);
        player.move(newPosition);
    }

    /**
     * Grab.
     *
     * @param clientInput the client input
     */
    public void grab (ClientInput clientInput) {
        player.grabItem(clientInput.weaponName);
    }

    /**
     * Reload.
     *
     * @param clientInput the client input
     */
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

            // Targeting scope is a special case in that it doesn't follow the same rules as every other card
            // So it gets some special code
            if (card.getName().equals("TargetingScope")) {
                // Make sure the target is valid
                Player target = card.getPlayerTargets().get(0);
                if (!target.getDamage().contains(player)) {
                    throw new IllegalArgumentException("Invalid target.");
                };

                // Pay
                Card.Color cube = Card.Color.valueOf(clientInput.cube);
                player.removeCube(cube);
            }


            card.effect(actionType);

            Player activePlayer = gameBoardModel.getActivePlayer();
            // For all the target players call afterShoot to check adrenaline, if it's dead, ...
            card.getPlayerTargets().forEach(p -> activePlayer.afterShoot(p));
            card.reset();
        }
    }

    /**
     * Use power up.
     *
     * @param clientInput the client input
     */
    public void usePowerUp (ClientInput clientInput) {
        PowerUp powerUp = player.getPowerUps().get(clientInput.powerUpIndex);
        Effect effect = powerUp.getEffects(1).get(0); // A power up has only one primary effect

        // execute actions
        executeAction(effect, powerUp, clientInput);
        gameBoardModel.getPowerUpsDeck().discard(powerUp);
    }

    /**
     * Discard power up and spawn.
     *
     * @param clientInput the client input
     */
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

    /**
     * Discard a powerup or a weapon.
     *
     * @param clientInput the client input
     */
    public void discard (ClientInput clientInput) {
        if (clientInput.weaponName != null) {
            Weapon weapon = player.getWeaponByName(clientInput.weaponName);
            gameBoardModel.getWeaponsDeck().discard(weapon);
        } else if (clientInput.powerUpIndex != null) {
            PowerUp powerUp = player.getPowerUps().get(clientInput.powerUpIndex);
            gameBoardModel.getPowerUpsDeck().sell(player, powerUp);
        }
    }

    /**
     * Use weapon effect.
     *
     * @param clientInput the client input
     */
    public void shoot (ClientInput clientInput) {
        Weapon weapon = player.getWeaponByName(clientInput.weaponName);
        if (!weapon.isLoaded()) throw new IllegalArgumentException("Weapon is not loaded.");

        // Make sure the player only uses one weapon per SHOOT action
        player.getWeapons().forEach(w -> {
            if (w != weapon && w.getUsedEffects().size() > 0) {
                throw new IllegalArgumentException("Only one weapon per SHOoT action allowed!");
            }
        });

        // Make sure only one primary/secondary/tertiary effects can be used per action
        if (weapon.getUsedEffects().contains(clientInput.effectType)) {
            throw new IllegalArgumentException("Effect " + clientInput.effectType + " already used!");
        }

        // Make sure secondary/tertiary can only be used after primary
        if (clientInput.effectType > 1 && !weapon.getUsedEffects().contains(1)) {
            throw new IllegalArgumentException("Secondary/tertiary can only be used after primary has been used.");
        }

        List<Effect> effects = weapon.getEffects(clientInput.effectType);

        Effect effect;

        // Allow the use of the second primary effect
        if (clientInput.effectType == 1 && clientInput.useSecondPrimary) {
            effect = effects.get(1);
        } else {
            effect = effects.get(0);
        }

        if (effect.getCost() != null) {
            weapon.payEffect(player, effect);
        }

        // execute actions
        executeAction(effect, weapon, clientInput);

        weapon.getUsedEffects().add(clientInput.effectType);
    }
}