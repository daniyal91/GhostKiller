package model;

import java.util.Collection;

/**
 * Chooses the closest critter in range to attack.
 *
 * @author Team 6
 *
 */
public class ClosestStrategy extends AttackStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Critter attackCritter(Tower tower, Collection<Critter> critters, GridLocation endPoint) {

        int minimum = Integer.MAX_VALUE;
        Critter target = null;

        for (Critter critter: critters) {

            int distance = GridLocation.distance(tower.getLocation(), critter.gridLocation);

            if (distance > tower.getRange()) {
                continue;
            }


            if (distance < minimum) {
                minimum = distance;
                target = critter;
            }
        }
        return target;

    }
}
