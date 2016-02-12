package test;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import model.GameGrid;
import model.GameGrid.CASE_TYPES;
import model.GridLocation;

public class GameGridTest {

    GameGrid testgamegird = new GameGrid();
    GameGrid badgamegird = new GameGrid();


    @Before
    public void beforeMethod(){
        testgamegird.readFromFile("src/test/testfiles/testmap.txt", false);
        badgamegird.readFromFile("src/test/testfiles/testmap2.txt", false);
    }

    @Test
    public void testReadFromFile() throws IOException {  
        assertTrue("The information could not be read from the file",
                testgamegird.cases.length == 10);
    }

    @Test
    public void testWriteToFile() throws IOException {
        testgamegird.writeToFile("src/test/testfiles/testmapWrite.txt");
        File testfile = new File("src/test/testfiles/testmapWrite.txt");
        assertTrue("The file coud not be written", testfile.exists());
        testfile.delete();

    }

    @Test
    public void testGetCases() {
        CASE_TYPES[][] testcases = testgamegird.getCases();
        assertTrue("getCases method can't retrieve the array", testcases.length > 0);

    }

    @Test
    public void testIsConnected() {
        assertTrue("IsConnected failed for a connected map", testgamegird.isConnected());
        assertTrue("IsConnected failed for a not connected map", !badgamegird.isConnected());
    }

    @Test
    public void testEntryPoint() {
        GridLocation entry = testgamegird.entryPoint();
        assertTrue("entryPoint() failed to work", entry.xCoordinate > -1 && entry.yCoordinate > -1);

    }

    @Test
    public void testExitPoint() {
        GridLocation exit = testgamegird.exitPoint();
        assertTrue("exitPoint() failed to work", exit.xCoordinate > -1 && exit.yCoordinate > -1);

    }

    @Test
    public void testGridValid() {
        GridLocation testgridl = testgamegird.exitPoint();
        assertTrue("GridValid test failed for a vlid GridLocation",
                testgamegird.gridValid(testgridl));
    }

}
