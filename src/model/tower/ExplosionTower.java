package model.tower;

import java.util.Collection;

import model.Critter;
import model.GridLocation;

/**
 * Explosion tower is a subclass of the Tower class and can be placed on the grid during the game.
 *
 * @author Team 6
 *
 */
public class ExplosionTower extends Tower {

    public ExplosionTower() {
        super();
    }
    public ExplosionTower(GridLocation gridLocation) {
        super(gridLocation);
    }

    @Override
    protected void setDetails() {
        this.name = "Explosion tower";
        this.iconPath = "icons/AncientTower.png";
        this.specialEffect = "splashing";

        this.initialCost = 20;
        this.levelCost = 15;
        this.power = 4;
        this.range = 8;
        this.rateOfFire = 1;
    }

    @Override
    public GridLocation attack(Collection<Critter> critters, GridLocation endPoint) {

        Critter critterToAttack = this.attackStrategy.attackCritter(this, critters, endPoint);

        if (critterToAttack != null) {
            critterToAttack.takeDamage(this.power, false);
            for (Critter critter: critters) {
                if (GridLocation.nearby(critter.gridLocation, critterToAttack.gridLocation)) {
                    System.out.print("Nearby critter taking damage!!!");
                    critter.takeDamage(this.power / 4, false);
                }
            }
            return critterToAttack.gridLocation;
        }

        return null;

    }

}
