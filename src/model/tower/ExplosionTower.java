package model.tower;

/**
 * Explosion tower is a subclass of the Tower class and can be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class ExplosionTower extends Tower {

    /**
     * Constructs the ExplosionTower object.
     */
    public ExplosionTower() {
        this.name = "Explosion tower";
        this.iconPath = "icons/AncientTower.png";
        this.specialEffect = "splashing";

        this.initialCost = 20;
        this.levelCost = 15;
        this.power = 4;
        this.range = 8;
        this.rateOfFire = 1;
    }

}
