package model;

import java.awt.Point;

/**
 * This class implements a location on the game grid.
 *
 * @author Team 6
 *
 */
public class GridLocation extends Point {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs the GridLocation object. Will default to an invalid location (-1, -1).
     */
    public GridLocation() {
        this.x = -1;
        this.y = -1;
    }

    public GridLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a textual representation of the GridLocation object.
     */
    @Override
    public String toString() {
        String template = "GridLocation (%s, %s)";
        return String.format(template, this.x, this.y);
    }

}
