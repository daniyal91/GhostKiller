package test;

import static org.junit.Assert.*;
import org.junit.Test;

import model.GridLocation;

public class GridLocationTest {
    
    @Test
    public void testDefaultConstructor() {
        GridLocation mygridloc=new GridLocation();
         assertTrue("default constructor test failed", mygridloc.xCoordinate==-1 && mygridloc.yCoordinate==-1);     
    }
    

    @Test
    public void testConstructor() {
        GridLocation mygridloc=new GridLocation(4,5);
         assertTrue("default constructor test failed", mygridloc.xCoordinate==4 && mygridloc.yCoordinate==5);     
    }
    
    
    
    
    @Test
    public void testToString() {
        GridLocation mygridloc=new GridLocation(2,3);
        String result=mygridloc.toString();
        assertEquals("toString() method test failed","Grid [xCoordinate=" + 2 + ", yCoordinate=" + 3 + "]",result);

        
    }

}
