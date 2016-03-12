package test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import junit.framework.TestCase;
import model.Critter;
import model.GridLocation;
import model.strategy.AttackStrategy;
import model.strategy.ClosestStrategy;
import model.strategy.StrongestStrategy;
import model.strategy.WeakestStrategy;
import model.tower.KingTower;
import model.tower.Tower;

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

        Critter closest = strategy.attackCritter(tower, critters, new GridLocation(0, 0));
        assertEquals(closest.gridLocation.x, 4);
        assertEquals(closest.gridLocation.y, 4);

    }

    @Test
    public void testClosestStrategyNoCritters() {
        AttackStrategy strategy = new ClosestStrategy();

        Tower tower = new Tower(new KingTower(), new GridLocation(5, 5));

        Collection<Critter> critters = new ArrayList<Critter>();
        Critter closest = strategy.attackCritter(tower, critters, new GridLocation(0, 0));
        assertNull(closest);
    }

    @Test
    public void testClosestStrategyNoCrittersInRange() {
        AttackStrategy strategy = new ClosestStrategy();

        Tower tower = new Tower(new KingTower(), new GridLocation(5, 5));

        // Creating a location just out of range for the tower.
        int range = tower.getRange();
        GridLocation outOfRange = new GridLocation(tower.getLocation().x, tower.getLocation().y + range + 1);

        Collection<Critter> critters = new ArrayList<Critter>();
        critters.add(new Critter(outOfRange, 1));

        Critter closest = strategy.attackCritter(tower, critters, new GridLocation(0, 0));
        assertNull(closest);
    }

    @Test
    public void testWeakestStrategy() {
        AttackStrategy strategy = new WeakestStrategy();

        Tower tower = new Tower(new KingTower(), new GridLocation(5, 5));

        ArrayList<Critter> critters = new ArrayList<Critter>();
        Critter weakest = new Critter(new GridLocation(4, 4), 1);
        Critter strongest = new Critter(new GridLocation(6, 6), 1);

        weakest.takeDamage(20);

        critters.add(strongest);
        critters.add(weakest);

        Critter closest = strategy.attackCritter(tower, critters, new GridLocation(0, 0));
        assertEquals(closest.gridLocation.x, 4);
        assertEquals(closest.gridLocation.y, 4);

    }

    @Test
    public void testWeakestStrategyNoCrittersInRange() {
        AttackStrategy strategy = new WeakestStrategy();

        Tower tower = new Tower(new KingTower(), new GridLocation(5, 5));

        // Creating a location just out of range for the tower.
        int range = tower.getRange();
        GridLocation outOfRange = new GridLocation(tower.getLocation().x, tower.getLocation().y + range + 1);

        Collection<Critter> critters = new ArrayList<Critter>();
        critters.add(new Critter(outOfRange, 1));

        Critter closest = strategy.attackCritter(tower, critters, new GridLocation(0, 0));
        assertNull(closest);
    }

    @Test
    public void testStrongestStrategy() {
        AttackStrategy strategy = new StrongestStrategy();

        Tower tower = new Tower(new KingTower(), new GridLocation(5, 5));

        ArrayList<Critter> critters = new ArrayList<Critter>();
        Critter weakest = new Critter(new GridLocation(4, 4), 1);
        Critter strongest = new Critter(new GridLocation(6, 6), 1);

        weakest.takeDamage(20);

        critters.add(strongest);
        critters.add(weakest);

        Critter closest = strategy.attackCritter(tower, critters, new GridLocation(0, 0));
        assertEquals(closest.gridLocation.x, 6);
        assertEquals(closest.gridLocation.y, 6);

    }



}
