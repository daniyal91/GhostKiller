package model;

/**
 * King tower is a subclass of the Tower class and can
 * be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class KingTower extends Tower {

    /**
     * Constructs the KingTower object.
     */
    public KingTower() {
        this.name = "King tower";
        this.iconPath = "icons/KingTower.png";
        this.initialCost = 10;
        this.levelCost = 8;
        this.power = 2;
        this.range = 5;
        this.rateOfFire = 1;
    }

}
