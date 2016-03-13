package model.tower;

import java.util.Collection;

import model.Critter;
import model.GridLocation;

/**
 * Fire tower is a subclass of the Tower class and can be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class FireTower extends Tower {

    public FireTower() {
        super();
    }

    public FireTower(GridLocation gridLocation) {
        super(gridLocation);
    }

    @Override
    protected void setDetails() {
        this.name = "Fire tower";
        this.specialEffect = "burning";
        this.iconPath = "icons/KingTower.png";

        this.initialCost = 10;
        this.levelCost = 8;
        this.power = 2;
        this.range = 5;
        this.rateOfFire = 1;
    }

    @Override
    public GridLocation attack(Collection<Critter> critters, GridLocation endPoint) {

        Critter critterToAttack = this.attackStrategy.attackCritter(this, critters, endPoint);

        if (critterToAttack != null) {
            critterToAttack.takeDamage(this.power, true);
            return critterToAttack.gridLocation;
        }

        return null;

    }


}
