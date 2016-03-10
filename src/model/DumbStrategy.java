package model;

import java.util.Collection;

/**
 * Dumb strategy. Returns the first critter in range.
 *
 * @author Team 6
 *
 */
public class DumbStrategy extends AttackStrategy {

    @Override
    public Critter attackCritter(Tower tower, Collection<Critter> critters) {

        for (Critter critter: critters) {
            if (GridLocation.distance(tower.getLocation(), critter.gridLocation) <= tower.getRange()) {
                return critter;
            }
        }
        return null;
    }

}
