package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Path {

    public GameGrid gamegrid;
    private ArrayList<GridLocation> shortestPath;

    public Path(GameGrid gamegrid) {
        this.gamegrid = gamegrid;
    }

    public GridLocation nextStep(GridLocation gridl) {

        ArrayList <GridLocation> shortestPath = this.pathList();
        int index = -1;
        Iterator<GridLocation> itr = shortestPath.iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(gridl)) {
                if (!itr.hasNext()) {
                    return null;
                }
                index = shortestPath.indexOf(itr.next());
                break;
            }

        }

        return shortestPath.get(index);

    }

    public ArrayList<GridLocation> getShortestPath() {
        return this.shortestPath;
    }

    // returns the shortest path as an array list starts with the entry point
    public ArrayList<GridLocation> pathList() {
        ArrayList<GridLocation> pathlist = new ArrayList<GridLocation>();
        GridLocation grid = gamegrid.exitPoint();
        grid = minNeighbor(grid, this.gamegrid.connectivities());
        while (!(grid.x == gamegrid.entryPoint().x
                && grid.y == gamegrid.entryPoint().y)) {
            pathlist.add(0, grid);
            grid = minNeighbor(grid, this.gamegrid.connectivities());

        }
        return pathlist;
    }

    // calculate the shortest path in connectivities[][][2] cells
    public void pathRelax(int[][][] connectivites) {

        GridLocation grid = gamegrid.exitPoint();
        grid = minNeighbor(grid, connectivites);
        connectivites[grid.x][grid.y][2] = connectivites[grid.x][grid.y][1];
        while (!(grid.x == gamegrid.entryPoint().x
                && grid.y == gamegrid.entryPoint().y)) {
            grid = minNeighbor(grid, connectivites);

            connectivites[grid.x][grid.y][2] = connectivites[grid.x][grid.y][1];

            //
        }

    }

    // find the neighbor with the minimum distance from the entry ( closest)
    public GridLocation minNeighbor(GridLocation gridl, int[][][] connectivites) {
        int line = gridl.x;
        int column = gridl.y;
        GridLocation minNeighbor = gridl;

        // check the left
        if (isPath(line, column - 1, connectivites)) {
            if (connectivites[line][column
                                    - 1][1] < connectivites[minNeighbor.x][minNeighbor.y][1]) {
                minNeighbor = new GridLocation(line, column - 1);
            }
        }

        // check above
        if (isPath(line - 1, column, connectivites)) {
            if (connectivites[line
                              - 1][column][1] < connectivites[minNeighbor.x][minNeighbor.y][1]) {
                minNeighbor = new GridLocation(line - 1, column);
            }
        }

        // check below
        if (isPath(line + 1, column, connectivites)) {
            if (connectivites[line + 1][column][1] < connectivites[minNeighbor.x][minNeighbor.y][1]) {
                minNeighbor = new GridLocation(line + 1, column);
            }
        }


        // check the right
        if (isPath(line, column + 1, connectivites)) {
            if (connectivites[line][column + 1][1] < connectivites[minNeighbor.x][minNeighbor.y][1]) {
                minNeighbor = new GridLocation(line, column + 1);
            }
        }

        return minNeighbor;
    }

    /**
     * Determines if the location specified belongs to the path
     *
     * @param line Line of the coordinate to validate.
     * @param column Column of the coordinate to validate.
     * @param connectivities Matrix used for the connectivity check.
     * @return True if the location is a valid path location
     */
    public boolean isPath(int line, int column, int[][][] connectivities) {

        if (line < 0) {
            return false;
        }
        if (line > connectivities.length - 1) {
            return false;
        }
        if (column < 0) {
            return false;
        }
        if (column > connectivities[0].length - 1) {
            return false;
        }

        if (connectivities[line][column][0] == 0) {
            return false;
        }

        return true;
    }


}


