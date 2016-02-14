package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class GameGrid {

    public static enum CASE_TYPES {
        GRASS, BUSH, ROAD, START, END, NONE
    };

    public static String[] CASE_TYPES_ICON_PATHS = {"icons/grass.jpg", "icons/grass2.jpg",
                    "icons/road.jpg", "icons/start.png", "icons/end.png"};

    // FIXME : this variable should be private once editMap is refactored!
    public CASE_TYPES[][] cases;

    Random randomGenerator = new Random();

    public GameGrid() {

    }

    public GameGrid(int lineCount, int columnCount) {
        this.cases = new CASE_TYPES[lineCount][columnCount];
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                this.cases[i][j] = CASE_TYPES.GRASS;
            }
        }
    }

    /**
     * Writes a serialized version of the game grid to a file.
     */
    public void writeToFile(String filename) {
        PrintWriter pr;
        try {
            pr = new PrintWriter(filename);
            pr.print(this.cases.length + " " + this.cases[0].length);

            for (int i = 0; i < this.cases.length; i++) {
                pr.println();
                for (int j = 0; j < this.cases[0].length; j++) {
                    pr.print(this.cases[i][j].ordinal() + " ");
                }
            }

            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public CASE_TYPES[][] getCases() {
        return this.cases;
    }

    public void setCases(CASE_TYPES[][] cases) {
        this.cases = cases;
    }

    // read and return an array from the text file "filename"
    public void readFromFile(String filename, Boolean addRandomBushes) {

        int linenumber = 0; // line number starts from the second line
        int rows = 0;
        int columns = 0; // n customer, k teams

        // Using an ArrayList instead of standard arrays.
        this.cases = new CASE_TYPES[1][1]; //
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = null;

            // read the 1st line, dimensions of the map
            line = br.readLine();
            String[] tokens = line.split("\\s+");
            rows = Integer.parseInt(tokens[0]);
            columns = Integer.parseInt(tokens[1]);

            this.cases = new CASE_TYPES[rows][columns];

            // read other lines
            while ((line = br.readLine()) != null) {

                // \\s+ means any number of white spaces between tokens
                tokens = line.split("\\s+");

                for (int i = 0; i < columns; i++) {
                    int caseValue = Integer.parseInt(tokens[i]);
                    this.cases[linenumber][i] = CASE_TYPES.values()[caseValue];
                    if (addRandomBushes && this.cases[linenumber][i] == CASE_TYPES.GRASS
                                    && randomGenerator.nextInt(100) > 92) {
                        this.cases[linenumber][i] = CASE_TYPES.BUSH;
                    }
                }

                linenumber = linenumber + 1; // next line (lane) information

            } // while line

            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Validates that a map is valid.
     * Many checks are made, including if one exit and one entry point exist, and
     * if there is a connecting path between them.
     *
     * @throws GameGridException
     */
    public void validateMap() throws GameGridException {

        ArrayList<GridLocation> entryPoints = this.getCasesByType(CASE_TYPES.START);
        if (entryPoints.size() > 1) {
            throw new GameGridException("Invalid grid : too many entry points.");
        }

        if (entryPoints.size() == 0) {
            throw new GameGridException("Invalid grid : no entry point.");
        }

        ArrayList<GridLocation> exitPoints = this.getCasesByType(CASE_TYPES.END);
        if (exitPoints.size() > 1) {
            throw new GameGridException("Invalid grid : too many exit points.");
        }

        if (exitPoints.size() == 0) {
            throw new GameGridException("Invalid grid : no exit point.");
        }

        if (!this.isConnected()) {
            throw new GameGridException("Invalid grid : no connecting path between exit point and entry point.");
        }

    }

    /**
     * Verifies if a map is connected from the entry point to the exit point.
     * We must be sure that there is only one exit point and one entry point
     * before calling this function!
     */
    public boolean isConnected() {

        GridLocation entryPoint = this.entryPoint();
        GridLocation exitPoint = this.exitPoint();

        // a new int[][] with the same dimension for tracking the connectivity
        int[][][] connectivities = new int[this.cases.length][this.cases[0].length][1];

        connectivities[entryPoint.xCoordinate][entryPoint.yCoordinate][0] = 1;
        this.connect(connectivities, entryPoint.xCoordinate, entryPoint.yCoordinate);
        return connectivities[exitPoint.xCoordinate][exitPoint.yCoordinate][0] == 1;

    }

    public ArrayList<GridLocation> getCasesByType(CASE_TYPES caseType) {

        ArrayList<GridLocation> response = new ArrayList<GridLocation>();

        for (int i = 0; i < this.cases.length; i++) {
            for (int j = 0; j < this.cases[0].length; j++) {
                if(this.cases[i][j] == caseType) {
                    response.add(new GridLocation(i, j));
                }

            }
        }

        return response;

    }


    /**
     * Returns the entry point of the grid. The entry point is assumed to be at the left edge
     *
     * @returns the height of the entry point, or -1 if no valid entry point.
     */
    public GridLocation entryPoint() {
        return this.getCasesByType(CASE_TYPES.START).get(0);
    }

    /**
     * Returns the exit point of the grid. The exit point is assumed to be at the right edge
     *
     * @returns the height of the exit point, or -1 if no valid exit point.
     */
    public GridLocation exitPoint() {
        return this.getCasesByType(CASE_TYPES.END).get(0);
    }

    public boolean gridValid(GridLocation grid) {
        if (grid.xCoordinate * grid.yCoordinate == 0 || grid.xCoordinate == this.cases.length - 1
                        || grid.yCoordinate == this.cases[0].length - 1)
            return true;
        else
            return false;


    }

    private boolean isRoad(int line, int column, int[][][] connectivities) {

        if (line < 0) {
            return false;
        }
        if (line > this.cases.length - 1) {
            return false;
        }
        if (column < 0) {
            return false;
        }
        if (column > this.cases[0].length - 1) {
            return false;
        }
        if (this.cases[line][column] == CASE_TYPES.GRASS
                        || this.cases[line][column] == CASE_TYPES.BUSH) {
            return false;
        }
        if (connectivities[line][column][0] == 1) {
            return false;
        }
        return true;
    }

    /**
     * Side method for isConnected method, it connects the neighbor of the tile(i,j) together if
     * they are path tiles, from the entrance to the exit point
     */
    private void connect(int[][][] connectivites, int line, int column) {

        // check the right neighbor
        if (this.isRoad(line, column + 1, connectivites)) {
            connectivites[line][column + 1][0] = 1;
            this.connect(connectivites, line, column + 1);
        }

        // check the below neighbor
        if (this.isRoad(line + 1, column, connectivites)) {
            connectivites[line + 1][column][0] = 1;
            this.connect(connectivites, line + 1, column);
        }

        // check the above neighbor
        if (this.isRoad(line - 1, column, connectivites)) {
            connectivites[line - 1][column][0] = 1;
            this.connect(connectivites, line - 1, column);
        }

        // check the left neighbor
        if (this.isRoad(line, column - 1, connectivites)) {
            connectivites[line][column - 1][0] = 1;
            this.connect(connectivites, line, column - 1);
        }

    }

}
