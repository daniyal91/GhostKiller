package model.tower;

/**
 * Ice tower is a subclass of the Tower class and can be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class IceTower extends Tower {

    /**
     * Constructs the IceTower object.
     */
    public IceTower() {
        this.name = "Ice tower";
        this.specialEffect = "freezing";
        this.iconPath = "icons/ModernTower.png";

        this.initialCost = 5;
        this.levelCost = 4;
        this.power = 1;
        this.range = 3;
        this.rateOfFire = 1;

    }

}
