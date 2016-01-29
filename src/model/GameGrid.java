package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class GameGrid {

    // FIXME : this variable should be private once editMap is refactored!
    public int[][] cases;

    /*
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
                    pr.print(this.cases[i][j] + " ");
                }
            }

            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public int[][] getCases() {
        return this.cases;
    }

    public void setCases(int[][] cases) {
        this.cases = cases;
    }

    // read and return an array from the text file "filename"
    public void readFromFile(String filename) {

        int linenumber = 0; // line number starts from the second line
        int rows = 0;
        int columns = 0; // n customer, k teams

        // Using an ArrayList instead of standard arrays.
        this.cases = new int[1][1]; //
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = null;

            // read the 1st line, dimensions of the map
            line = br.readLine();
            String[] tokens = line.split("\\s+");
            rows = Integer.parseInt(tokens[0]);
            columns = Integer.parseInt(tokens[1]);

            this.cases = new int[rows][columns];

            // read other lines
            while ((line = br.readLine()) != null) {
                // \\s+ means any number of white spaces between tokens
                tokens = line.split("\\s+");

                for (int i = 0; i < columns; i++) {
                    this.cases[linenumber][i] = Integer.parseInt(tokens[i]);
                }

                linenumber = linenumber + 1; // next line (lane) information

            } // while line

            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /*
     * Verifies if a map is connected from the entry point to the exit point.
     */
    public boolean isConnected() {

        // a new int[][] with the same dimension for tracking the connectivity
        int[][][] connectivities = new int[this.cases.length][this.cases[0].length][1];

        // check if it has a entry point before it :

        if (this.entryPoint() > -1) {
            connectivities[this.entryPoint()][0][0] = 1;
            this.connect(connectivities, this.entryPoint(), 0);
            System.out.println("entrance point: (" + this.entryPoint() + ",0)");
            System.out.println(
                            "exit point: (" + this.exitPoint() + "," + this.cases[0].length + ")");
        } else {

            System.out.println("no entrance point");
        }

        if (this.exitPoint() > -1) {
            return (connectivities[this.exitPoint()][this.cases[0].length - 1][0] == 1);
        } else {
            return false;
        }

    }

    /*
     * Returns the entry point of the grid. The entry point is assumed to be at the left edge
     *
     * @returns the height of the entry point, or -1 if no valid entry point.
     */
    private int entryPoint() {
        int entHgth = -1;
        for (int i = 0; i < this.cases.length; i++) {
            if (this.cases[i][0] == 1) {
                entHgth = i;
                break;
            }
        }
        return entHgth;
    }

    /*
     * Returns the exit point of the grid. The exit point is assumed to be at the right edge
     *
     * @returns the height of the exit point, or -1 if no valid exit point.
     */
    private int exitPoint() {
        int extHgth = -1;
        for (int i = 0; i < this.cases.length; i++) {
            if (this.cases[i][this.cases[0].length - 1] == 1) {
                extHgth = i;
                break;
            }
        }
        return extHgth;
    }

    public GameGrid() {

    }

    /*
     * Side method for cntcheck method, it connects the neighbor of the tile(i,j) together if they
     * are path tiles, from the entrance to the exit point
     */
    private void connect(int[][][] connectivites, int line, int column) {

        // check the right neighbor
        if (column < this.cases[0].length - 1 && this.cases[line][column + 1] == 1
                        && connectivites[line][column + 1][0] != 1) {
            connectivites[line][column + 1][0] = 1;
            this.connect(connectivites, line, column + 1);
        }

        // check the below neighbor
        if (line < this.cases.length - 1 && this.cases[line + 1][column] == 1
                        && connectivites[line + 1][column][0] != 1) {
            connectivites[line + 1][column][0] = 1;
            this.connect(connectivites, line + 1, column);
        }

        // check the above neighbor
        if (line > 0 && this.cases[line - 1][column] == 1
                        && connectivites[line - 1][column][0] != 1) {
            connectivites[line - 1][column][0] = 1;
            this.connect(connectivites, line - 1, column);
        }

        // check the left neighbor
        if (column > 0 && this.cases[line][column - 1] == 1
                        && connectivites[line][column - 1][0] != 1) {
            connectivites[line][column - 1][0] = 1;
            this.connect(connectivites, line, column - 1);
        }

    }

}