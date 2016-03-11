package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Critter attackCritter(Tower tower, Collection<Critter> critters) {

        Critter[] critterList = (Critter[]) critters.toArray();  
        int index=0;
        int target=0;
        int distance=GridLocation.distance(tower.getLocation(), critterList[0].gridLocation);

        for (Critter critter: critters) {
            if (GridLocation.distance(tower.getLocation(), critter.gridLocation) <distance) {
                distance=GridLocation.distance(tower.getLocation(), critter.gridLocation);
                target=index;              
            }
            index++;
        }
        
        return critterList[target];
    }

}
