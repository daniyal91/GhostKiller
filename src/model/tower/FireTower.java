package model.tower;

/**
 * Fire tower is a subclass of the Tower class and can be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class FireTower extends Tower {

    /**
     * Constructs the FireTower object.
     */
    public FireTower() {
        this.name = "Fire tower";
        this.specialEffect = "burning";
        this.iconPath = "icons/KingTower.png";

        this.initialCost = 10;
        this.levelCost = 8;
        this.power = 2;
        this.range = 5;
        this.rateOfFire = 1;
    }

}
