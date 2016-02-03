package model;

public class GridLocation {
    
    
    public int xCoordinate;
    public int yCoordinate;
        
   
    public GridLocation(){
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    }
    
    public GridLocation(int xCoordinate, int yCoordinate) {
        super();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
     
    @Override
    public String toString() {
        return "Grid [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }

}
