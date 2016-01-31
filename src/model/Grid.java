package model;

public class Grid {
    
    
    private int xCoordinate;
    private int yCoordinate;
        
   
    public Grid(){
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    }
    
    public Grid(int xCoordinate, int yCoordinate) {
        super();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
   
    
    
    public int getx() {
        return xCoordinate;
    }
    public void setx(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public int gety() {
        return yCoordinate;
    }
    public void sety(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    
    
    @Override
    public String toString() {
        return "Grid [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }

}
