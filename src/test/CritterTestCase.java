package test;

import org.junit.Test;

import junit.framework.TestCase;
import model.Critter;
import model.GridLocation;

public class CritterTestCase extends TestCase {

    @Test
    public void testTakeDamage() {
        Critter critter = new Critter(new GridLocation(1, 0), 3);
        int damage = critter.getHealthPoints() - 1;

        critter.takeDamage(damage, false);

        assertEquals(critter.getHealthPoints(), 1);
        assertFalse(critter.isDead());

    }

    @Test
    public void testKillCritter() {
        Critter critter = new Critter(new GridLocation(1, 0), 3);
        int damage = critter.getHealthPoints() + 1;

        critter.takeDamage(damage, false);

        assertEquals(critter.getHealthPoints(), 0);
        assertTrue(critter.isDead());

    }

    @Test
    public void testFreezeCritter() {
        Critter critter = new Critter(new GridLocation(1, 0), 3);

        // Freezing takes effect at the beginning of the next turn!
        critter.freeze();
        assertFalse(critter.isFrozen());

        critter.makeTurn();
        assertTrue(critter.isFrozen());

        // It should be impossible to freeze the critter
        // two turns in a row.
        critter.freeze();
        critter.makeTurn();
        assertFalse(critter.isFrozen());

        critter.freeze();
        critter.makeTurn();
        assertTrue(critter.isFrozen());

    }

    @Test
    public void testBurnCritter() {
        Critter critter = new Critter(new GridLocation(1, 0), 3);

        int damage = critter.getHealthPoints() / 2;
        critter.takeDamage(damage, true);

        // The burning damage should not apply instantly.
        assertEquals(critter.getHealthPoints(), damage);

        critter.makeTurn();

        // Burning damage, but should not kill the critter.
        assertFalse(critter.getHealthPoints() == damage);
        assertFalse(critter.isDead());

        // The burning damage should not last more
        // than one turn.
        critter.makeTurn();
        critter.makeTurn();
        critter.makeTurn();
        critter.makeTurn();
        assertFalse(critter.isDead());

    }

    @Test
    public void testBurnCritterMultiple() {
        Critter critter = new Critter(new GridLocation(1, 0), 3);

        int damage = critter.getHealthPoints() / 3;
        critter.takeDamage(damage, true);
        critter.takeDamage(damage, true);

        // The burning damage should not apply instantly.
        assertEquals(critter.getHealthPoints(), damage);

        critter.makeTurn();

        // Both burning damages should be applied. The
        // critter should be dead now.
        assertTrue(critter.isDead());

    }

}
