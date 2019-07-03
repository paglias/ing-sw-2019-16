package models.cards;

import models.GameBoard;
import models.Player;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeaponTest {
    @Test
    void loadWeapons () {
        // Test that loading doesn't fail
        ArrayList<Weapon> weapons = Weapon.loadWeapons();
        assertEquals(weapons.size(), 21);
    }

    @Test
    void isLoaded () {
        // When created Weapons are loaded
        Weapon weapon = new Weapon();
        assertTrue(weapon.isLoaded());
    }

    @Test
    void getUsedEffects () {
        GameBoard gameBoard = new GameBoard();
        Weapon weapon = (Weapon) gameBoard.getWeaponsDeck().pick();
        assertEquals(weapon.getUsedEffects().size(), 0);
    }

    @Test
    void payCost () {
        Player p = new Player();

        GameBoard gameBoard = new GameBoard();
        Weapon weapon = (Weapon) gameBoard.getWeaponsDeck().pick();

        while (weapon.getCost().size() == 0) {
            weapon = (Weapon) gameBoard.getWeaponsDeck().pick();
        }

        int ncubes = p.getCubes().size();

        weapon.payCost(p);

        assertEquals(p.getCubes().size(), ncubes - weapon.getCost().size());
    }

}
