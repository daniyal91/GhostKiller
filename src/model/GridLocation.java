package model;

/**
 * This class implements grid location which assign x and y to each cell map.
 *
 * @author Team 6
 *
 */
public class GridLocation {

    public int xCoordinate;
    public int yCoordinate;

    /**
     * Constructs the GridLocation object.
     * Will default to an invalid location (-1, -1).
     */
    public GridLocation() {
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    }

    /**
     * Constructs the GridLocation with the specified coordinates.
     *
     * @param xCoordinate x coordinate of the new location.
     * @param yCoordinate y coordinate of the new location.
     */
    public GridLocation(int xCoordinate, int yCoordinate) {
        super();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Returns a textual representation of the GridLocation object.
     */
    @Override
    public String toString() {
        return "Grid [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }

}
