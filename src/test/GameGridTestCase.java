package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.GameGrid;
import model.GameGrid.CASE_TYPES;
import model.GameGridException;
import model.GridLocation;
/**
 * This class implements tests for objects of GamaGrid class using JUnit4.
 * @author Team 6
 *
 */

public class GameGridTestCase {

    GameGrid testgamegird = new GameGrid();
    GameGrid badgamegird = new GameGrid();
    
    /**
     * This method runs before each test method.
     * It initiates 2 game grids, one correct and one incorrect. 
     */

    @Before
    public void beforeMethod() {
        testgamegird.readFromFile("src/test/testfiles/testmap.txt", false);
        badgamegird.readFromFile("src/test/testfiles/testmap2.txt", false);
    }
    /**
     * This method checks if ReadFromFile method is working smoothly and game grid format is fine.
     * @throws IOException
     */


    @Test
    public void testReadFromFile() throws IOException {
        assertTrue("The information could not be read from the file", testgamegird.getCases().length == 10);
    }
    
    /**
     * This method checks if the file could not be written.
     * @throws IOException
     */

    @Test
    public void testWriteToFile() throws IOException {
        testgamegird.writeToFile("src/test/testfiles/testmapWrite.txt");
        File testfile = new File("src/test/testfiles/testmapWrite.txt");
        assertTrue("The file coud not be written", testfile.exists());
        testfile.delete();

    }
    
    /**
     * This method checks if getCases method can retrieve the array.
     */

    @Test
    public void testGetCases() {
        CASE_TYPES[][] testcases = testgamegird.getCases();
        assertTrue("getCases method can't retrieve the array", testcases.length > 0);

    }
    /**
     * This method checks the connectivity in map grids by using isConnected method.
     */

    @Test
    public void testIsConnected() {
        assertTrue("IsConnected failed for a connected map", testgamegird.isConnected());
        assertTrue("IsConnected failed for a not connected map", !badgamegird.isConnected());
    }
    /**
     * This method checks if entry point of the game grid is correct.
     */

    @Test
    public void testEntryPoint() {
        GridLocation entry = testgamegird.entryPoint();
        assertTrue("entryPoint() failed to work", entry.x > -1 && entry.y > -1);

    }
    /**
     * This method checks if exit point of the game grid is correct.
     */

    @Test
    public void testExitPoint() {
        GridLocation exit = testgamegird.exitPoint();
        assertTrue("exitPoint() failed to work", exit.x > -1 && exit.y > -1);

    }
    /**
     *  This method checks validateMap method by verifying if connecting path exists.
     * @throws GameGridException
     */

    @Test(expected = GameGridException.class)
    public void testNoConnectingPath() throws GameGridException {
        try {
            this.badgamegird.validateMap();
        } catch (GameGridException e) {
            assertEquals(e.getMessage(), "Invalid grid : no connecting path between exit point and entry point.");
            throw e;
        }
    }
    /**
     * This method checks validateMap method by verifying if entry point exists.
     * @throws GameGridException
     */

    @Test(expected = GameGridException.class)
    public void testNoEntryPoint() throws GameGridException {

        // Replacing all the entry points by grass.
        ArrayList<GridLocation> entryPoints = this.badgamegird.getCasesByType(CASE_TYPES.START);
        for (GridLocation entryPoint : entryPoints) {
            this.badgamegird.getCases()[entryPoint.x][entryPoint.y] = CASE_TYPES.GRASS;
        }

        try {
            this.badgamegird.validateMap();
        } catch (GameGridException e) {
            assertEquals(e.getMessage(), "Invalid grid : no entry point.");
            throw e;
        }
    }
    /**
     * This method checks validateMap method by adding more than one entry points.
     * @throws GameGridException
     */

    @Test(expected = GameGridException.class)
    public void testTooManyEntryPoints() throws GameGridException {

        // Manually adding 3 entry points.
        this.badgamegird.getCases()[0][0] = CASE_TYPES.START;
        this.badgamegird.getCases()[0][1] = CASE_TYPES.START;
        this.badgamegird.getCases()[0][2] = CASE_TYPES.START;

        try {
            this.badgamegird.validateMap();
        } catch (GameGridException e) {
            assertEquals(e.getMessage(), "Invalid grid : too many entry points.");
            throw e;
        }
    }
    /**
     * This method checks isValidExitPoint method by verifying if exit point exists.
     */

    @Test
    public void testValidExitPoint() {
        GridLocation testgridl = testgamegird.exitPoint();
        assertTrue("GridValid test failed for a valid GridLocation", testgamegird.isValidExitPoint(testgridl));
        assertFalse("GridValid test failed for a valid GridLocation", testgamegird.isValidEntryPoint(testgridl));
    }
    /**
     * This method checks isValidExitPoint method by verifying if entry point exists.
     */

    @Test
    public void testValidEntryPoint() {
        GridLocation testgridl = testgamegird.entryPoint();
        assertFalse("GridValid test failed for a valid GridLocation", testgamegird.isValidExitPoint(testgridl));
        assertTrue("GridValid test failed for a valid GridLocation", testgamegird.isValidEntryPoint(testgridl));
    }



}
