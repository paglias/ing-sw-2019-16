import models.Player;
import models.Square;
import models.cards.Weapon;

import static models.cards.Weapon.Effect.MOVE;

public class WeaponBetter {
    private Player targetPlayer;
    private Square newPosition;

    public void setTargetPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public void setNewPosition (Square newPosition) {
        this.newPosition = newPosition;
    }

    public void effect (Weapon.Effect effect) {
        switch (effect) {
            case MOVE:
                this.move();
        }
    }

    public void move () {
        targetPlayer.setPosition(newPosition);
        targetPlayer = null;
        newPosition = null;
    }

    public static void main () {
        WeaponBetter weapon = new WeaponBetter();
        weapon.setTargetPlayer(new Player());
        weapon.setNewPosition(new Square(Square.Color.BLUE, false));
        weapon.effect(MOVE);

    }
}
