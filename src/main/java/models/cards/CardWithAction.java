package models.cards;

import models.Player;
import models.Square;

import java.util.ArrayList;
import java.util.List;

public class CardWithAction extends Card {
    protected String name;

    protected ArrayList<Effect> primaryEffect;
    protected ArrayList<Effect> secondaryEffect;
    protected ArrayList<Effect> tertiaryEffect;

    protected ArrayList<Player> playerTargets = new ArrayList<>();
    protected ArrayList<Square> positions= new ArrayList<>();
    protected Square.Direction direction;
    protected Player damagingPlayer;

    public String getName () {return this.name; }

    public void addPlayerTarget (Player playerTarget) {this.playerTargets.add(playerTarget);}
    public void setDirection(Square.Direction direction){this.direction = direction;}
    public void addPosition (Square position) {this.positions.add(position);}
    public void setDamagingPlayer(Player damagingPlayer){this.damagingPlayer = damagingPlayer;}

    public List<Player> getPlayerTargets () { return this.playerTargets; }

    /**
     * Gets effects.
     *
     * @param effectType the effect type
     * @return the effects
     */
    public List<Effect> getEffects (Integer effectType) {
        switch (effectType) {
            case 1:
                return this.primaryEffect;
            case 2:
                return this.secondaryEffect;
            case 3:
                return this.tertiaryEffect;
            default:
                throw new IllegalArgumentException("effectType must be 1, 2 or 3.");
        }
    }

    /**
     * List of weapon effects.
     *
     * @param effect the effect
     */
    public void effect(Action.Type effect) {
        switch (effect) {
            case MARK_VIEW:
                this.markView();
                return;
            case SHOOT:
                this.shoot();
                return;
            case SHOOT_CANT_SEE:
                this.ShootCantSee();
                return;
            case MARK_TWO_AWAY_VIEW:
                this.markTwoAwayView();
                return;
            case MARK:
                this.mark();
                return;
            case SHOOT_VIEW:
                this.shootView();
                return;
            case SHOOT_ONE_AWAY_VIEW:
                this.shootOneAwayView();
                return;
            case MOVE_TARGET:
                this.moveTarget();
                return;
            case MOVE:
                this.move();
                return;
            case SHOOT_SECOND_TARGET_VIEW:
                this.shootSecondTargetView();
                return;
            case SHOOT_TARGET_VIEW:
                this.shootTargetView();
                return;
            case SHOOT_EVERY:
                this.shootEvery();
                return;
            case MARK_EVERY:
                this.markEvery();
                return;
            case SHOOT_EVERY_ONE_AWAY_VIEW:
                this.shootEveryOneAwayView();
                return;
            case MARK_EVERY_ONE_AWAY_VIEW:
                this.markEveryOneAwayView();
                return;
            case MARK_ONE_AWAY_VIEW:
                this.markOneAwayView();
                return;
            case MOVE_DIRECTION:
                this.moveDirection();
                return;
            case SHOOT_ROOM_CAN_SEE:
                this.shootRoomCanSee();
                return;
            case ATTRACT_TARGET:
                this.attractTarget();
                return;
            case SHOOT_DIRECTION:
                this.shootDirection();
        }
    }

    /**
     * Reset.
     */
    public void reset(){
        damagingPlayer = null;
        direction = null;
        playerTargets.clear();
        positions.clear();
    }

    /**
     * Shoot target on the same square.
     */
    public void shoot(){
        Player playerTarget= playerTargets.get(0);
        if(playerTarget.getPosition()==damagingPlayer.getPosition()) {
            playerTarget.addDamage(damagingPlayer);
        }

        else throw new IllegalArgumentException("Not usable method");
    }


    /**
     * Mark target on the same square..
     */
    public void mark(){
        Player playerTarget= playerTargets.get(0);
        if(playerTarget.getPosition().equals(damagingPlayer.getPosition())) {
            playerTarget.addMark(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    /**
     * Move to an adjacent square .
     */
    public void move() {
        Square position= positions.get(0);
        List<Square> CanAccessDirectly= damagingPlayer.getPosition().getCanAccessDirectly();
        if(CanAccessDirectly.contains(position)){
            damagingPlayer.move(position);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    /**
     * Shoot to a target that you can see, distant one square.
     */
    public void shootOneAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    /**
     * Mark a target that you can see, distant one square.
     */
    public void markOneAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addMark(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    /**
     * Shoot to a target that you can see, distant two squares.
     */
    public void shootTwoAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(!canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    /**
     * Mark a target that you can see, distant two squares..
     */
    public void markTwoAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(!canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addMark(damagingPlayer);
        }
    }

    /**
     * Shoot every other target in your square.
     */
    public void shootEvery() {
        for (Player Players : playerTargets) {
            if (damagingPlayer.getPosition().equals(Players.getPosition())) {
                Players.addDamage(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
    }

    /**
     * Mark every other target in your square.
     */
    public void markEvery() {
        for (Player Players : playerTargets) {
            if (damagingPlayer.getPosition().equals(Players.getPosition())) {
                Players.addMark(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
    }

    /**
     * Shoot every target in a room you can see.
     */
    public void shootRoomCanSee() {
        Square targetSquare= positions.get(0);
        if (!damagingPlayer.getPosition().getCanView().contains(targetSquare)) {
            throw new IllegalArgumentException("Invalid target square, cannot see from player.");
        }

        for (Player player : playerTargets) {
            if (player.getPosition() == targetSquare) {
                player.addDamage(damagingPlayer);
            }
        }
    }

    /**
     * Choose an adjacent square and shoot every target on that square.
     */
    public void shootEveryOneAwayView() {
        for (Player Players : playerTargets) {
            List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
            Square position = Players.getPosition();
            if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
                Players.addDamage(damagingPlayer);

            }
            else throw new IllegalArgumentException("Not usable method");
        }

    }

    /**
     * Choose an adjacent square and mark every target on that square.
     */
    public void markEveryOneAwayView() {
        for (Player Players : playerTargets) {
            List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
            Square position = Players.getPosition();
            if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
                Players.addMark(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
    }

    /**
     * Shoot a target you can see.
     */
    public void shootView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");


    }

    /**
     *  Mark a target you can see.
     */
    public void markView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addMark(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");

    }

    /**
     * Move the target in an adjacent square.
     */
    public void moveTarget() {
        Player playerTarget= playerTargets.get(0);
        List<Square> CanAccessDirectly = playerTarget.getPosition().getCanAccessDirectly();
        Square newPosition=positions.get(0);
        if (CanAccessDirectly.contains(newPosition)) {
            playerTarget.move(newPosition);
        }
        else throw new IllegalArgumentException("Not usable method");

    }

    /**
     * Shoot every target in a cardinal direction you choose.
     */
    public void shootDirection() {
        Square position= damagingPlayer.getPosition();
        List<Square> allSquares = damagingPlayer.getGameBoard().getSquares();

        int playersShot=0;
        for(Square square: position.filterDirectionSquare(allSquares, direction)){
            for(Player player:square.getPlayersHere(playerTargets)){
                player.addDamage(damagingPlayer);
                playersShot++;
            }
        }
        if(playersShot==0){
            throw new IllegalArgumentException("Not usable method");
        }
    }

    /**
     * Move target in a definite direction.
     */
    public void moveDirection () {
        List<Square> squares = damagingPlayer.getGameBoard().getSquares();
        List<Square> squareList = playerTargets.get(0).getPosition().filterDirectionSquare(squares, direction);
        if (squareList.contains(positions.get(0))) {
            playerTargets.get(0).setPosition(positions.get(0));
        } else {
            throw new IllegalArgumentException("Not usable method");
        }
    }

    /**
     * Shoot one target that your target can see.
     */
    public void shootTargetView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        Player secondTarget= playerTargets.get(1);
        Square secondTargetPosition= secondTarget.getPosition();
        if(CanView.contains(position)) {
            playerTarget.addDamage(damagingPlayer);
            if (position.getCanView().contains(secondTargetPosition)) {
                secondTarget.addDamage(damagingPlayer);
            }
        }
        else throw new IllegalArgumentException("Not usable method");


    }

    /**
     * Choose one target. Your target can see another target.
     * the second target can see a third target, shoot the third.
     */
    public void shootSecondTargetView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        Player secondTarget= playerTargets.get(1);
        Square secondTargetPosition = secondTarget.getPosition();
        Player thirdTarget= playerTargets.get(2);
        Square thirdTargetPosition= thirdTarget.getPosition();
        if (CanView.contains(position)) {
            playerTarget.addDamage(damagingPlayer);
            if (position.getCanView().contains(secondTargetPosition)){
                secondTarget.addDamage(damagingPlayer);
            }
            if(secondTargetPosition.getCanView().contains(thirdTargetPosition)){
                thirdTarget.addDamage(damagingPlayer);
            }
        }
        else throw new IllegalArgumentException("Not usable method");

    }

    /**
     * Attract target to your square.
     */
    public void attractTarget() {
        Square position= damagingPlayer.getPosition();
        Player playerTarget= playerTargets.get(0);
        Square targetPosition= playerTarget.getPosition();
        if(position.sameDirection(targetPosition,direction)){
            playerTarget.setPosition(position);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    /**
     * Shoot a target you can't see.
     */
    public void ShootCantSee(){
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(!CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");

    }
}
