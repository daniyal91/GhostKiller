package test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import model.GameGrid;
import model.GridLocation;
import model.Path;

public class PathTestCase {


    GameGrid testgamegird= new GameGrid();
    ArrayList <GridLocation> testshortestpath=new ArrayList<GridLocation>();
    Path testpath;

    @Before
    public void beforeTest(){
        testgamegird.readFromFile("src/test/testfiles/testmap.txt",false);
        testpath=new Path(testgamegird);
        testshortestpath=testpath.getShortestPath();
    }

    @Test
    public void testShortestPath() {
        //tests the start point of the shortest path
        assertTrue(testshortestpath.get(0).equals(new GridLocation(1,2)));
        //tests the length of the shortest path
        assertTrue(testshortestpath.size()==10);
        //tests the length of the shortest path
        assertTrue(testshortestpath.get(testshortestpath.size()-1).equals(new GridLocation(4,8)));
    }

    @Test
    public void testGetNextLocation() {
        GridLocation current=new GridLocation(4,4);
        GridLocation next=new GridLocation(4,5);
        assertTrue(testpath.getNextLocation(current).equals(next));       
    }
    
    @Test
    public void testConnectivityofShortestPath() {
        GridLocation step=testshortestpath.get(0);
        for (int i=1;i<testshortestpath.size();i++) {
            step=testpath.getNextLocation(step);
        }     
        assertTrue(step.equals(new GridLocation(4,8)));       
    }


}
