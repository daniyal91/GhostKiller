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

        Critter[] critterList = (Critter[]) critters.toArray();
        int index=0;
        int target=-1;
        int minhealth=critterList[0].HEALTH_POINTS_PER_LEVEL;

        for (Critter critter: critters) {
            if (critter.getHealthPoints() < minhealth &&
                    GridLocation.distance(tower.getLocation(), critter.gridLocation) <= tower.getRange()) {
                minhealth=critter.getHealthPoints();
                target=index;
            }
            index++;
        }
        if (target >-1)
            return critterList[target];
        else
            return null;
    }
}
