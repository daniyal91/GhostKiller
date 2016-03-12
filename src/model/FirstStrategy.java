package model;

import java.util.Collection;

/**
 * Chooses the farther (closest to the end point) critter in range to attack.
 *
 * @author Team 6
 *
 */
public class FirstStrategy extends AttackStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Critter attackCritter(Tower tower, Collection<Critter> critters, GridLocation endPoint) {
        for (Critter critter: critters) {
            if (GridLocation.distance(tower.getLocation(), critter.gridLocation) <= tower.getRange()) {
                return critter;
            }
        }
        return null;
    }

}
