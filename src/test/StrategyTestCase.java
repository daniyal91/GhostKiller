package test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import junit.framework.TestCase;
import model.AttackStrategy;
import model.ClosestStrategy;
import model.Critter;
import model.GridLocation;
import model.KingTower;
import model.Tower;

public class StrategyTestCase extends TestCase {

    @Test
    public void testClosestStrategy() {
        AttackStrategy strategy = new ClosestStrategy();

        Tower tower = new Tower(new KingTower(), new GridLocation(5, 5));

        Collection<Critter> critters = new ArrayList<Critter>();
        critters.add(new Critter(new GridLocation(1, 1), 1));
        critters.add(new Critter(new GridLocation(2, 2), 1));
        critters.add(new Critter(new GridLocation(3, 3), 1));
        critters.add(new Critter(new GridLocation(4, 4), 1));

        Critter closest = strategy.attackCritter(tower, critters);
        assertEquals(closest.gridLocation.x, 4);
        assertEquals(closest.gridLocation.y, 4);

    }

}
