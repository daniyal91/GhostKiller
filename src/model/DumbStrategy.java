package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Dumb strategy. Returns a random critter in range.
 *
 * @author Team 6
 *
 */
public class DumbStrategy extends AttackStrategy {

    @Override
    public Critter attackCritter(Tower tower, Collection<Critter> critters, GridLocation endPoint) {
        ArrayList<Critter> inrange=new ArrayList<Critter>();

        for (Critter critter: critters) {
            if (GridLocation.distance(tower.getLocation(), critter.gridLocation) <= tower.getRange()) {
                inrange.add(critter);
            }
        }
        if (!inrange.isEmpty()) {
            return inrange.get((int)(Math.random() * inrange.size()));
        }
        return null;
    }

}
