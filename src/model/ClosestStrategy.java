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
    public Critter attackCritter(Tower tower, Collection<Critter> critters) {
        Critter[] critterList = (Critter[]) critters.toArray();  
        int index=0;
        int target=-1;
        int mindistance=1000;  // Attention required ! should be replaced 

        for (Critter critter: critters) {
            if (GridLocation.distance(tower.getLocation(), critter.gridLocation) <mindistance &&
                    GridLocation.distance(tower.getLocation(), critter.gridLocation) <= tower.getRange()) {
                mindistance=GridLocation.distance(tower.getLocation(), critter.gridLocation);
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
