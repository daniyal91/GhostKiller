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

        critter.takeDamage(damage);

        assertEquals(critter.getHealthPoints(), 1);
        assertFalse(critter.isDead());

    }

    @Test
    public void testKillCritter() {
        Critter critter = new Critter(new GridLocation(1, 0), 3);
        int damage = critter.getHealthPoints() + 1;

        critter.takeDamage(damage);

        assertEquals(critter.getHealthPoints(), 0);
        assertTrue(critter.isDead());

    }

}
