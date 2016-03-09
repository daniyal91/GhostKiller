package test;

import org.junit.Test;

import junit.framework.TestCase;
import model.GridLocation;

public class GridLocationTestCase extends TestCase {

    @Test
    public void testDefaultConstructor() {
        GridLocation gridLocation = new GridLocation();
        TestCase.assertEquals(-1, gridLocation.x);
        TestCase.assertEquals(-1, gridLocation.y);
    }

    @Test
    public void testConstructor() {
        GridLocation gridLocation = new GridLocation(4, 5);
        TestCase.assertEquals(4, gridLocation.x);
        TestCase.assertEquals(5, gridLocation.y);
    }

    @Test
    public void testToString() {
        GridLocation gridLocation = new GridLocation(2, 3);
        String result = gridLocation.toString();
        String expected = "GridLocation (2, 3)";
        TestCase.assertEquals(result, expected);
    }

}
