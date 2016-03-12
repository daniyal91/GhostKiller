package model.tower;

/**
 * Ancient tower is a subclass of the Tower class and can be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class AncientTower extends Tower {

    /**
     * Constructs the AncientTower object.
     */
    public AncientTower() {
        this.name = "Ancient tower";
        this.iconPath = "icons/AncientTower.png";
        this.initialCost = 20;
        this.levelCost = 15;
        this.power = 4;
        this.range = 8;
        this.rateOfFire = 1;
    }

}
