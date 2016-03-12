package model;

import java.util.Collection;

/**
 * Chooses the weakest (with minimum health points) critter in range to attack.
 *
 * @author Team 6
 *
 */
public class WeakestStrategy extends AttackStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Critter attackCritter(Tower tower, Collection<Critter> critters, GridLocation endPoint) {

        Critter target = null;
        int minimumHealth = Integer.MAX_VALUE;

        for (Critter critter: critters) {

            int distance = GridLocation.distance(tower.getLocation(), critter.gridLocation);
            if (distance > tower.getRange()) {
                continue;
            }

            if (critter.getHealthPoints() < minimumHealth) {
                minimumHealth = critter.getHealthPoints();
                target = critter;
            }
        }
        return target;
    }
}
