package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.GridLocation;
import model.tower.FireTower;
import model.tower.Tower;

/**
 * Test case class for the Tower class.
 *
 * @author Team 6
 *
 */
public class TowerTestCase {

    /**
     * Tests the toString method when there is no location
     * for the tower.
     */
    @Test
    public void testToString() {
        Tower.resetIdCounter();
        Tower fireTower = new FireTower();

        String response = fireTower.toString();

        assertEquals(response, "Tower [-2] (Fire tower)");

    }

    /**
     * Tests the toString method when there is no location
     * for the tower.
     */
    @Test
    public void testToStringLocation() {
        Tower.resetIdCounter();
        Tower fireTower = new FireTower(new GridLocation(0, 0));

        String response = fireTower.toString();

        assertEquals(response, "Tower [-2] (Fire tower) placed at [GridLocation (0, 0)]");

    }

}
