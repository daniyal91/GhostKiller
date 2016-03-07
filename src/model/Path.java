package model;

import java.util.ArrayList;
import model.GridLocation;

public class Path {

    public GameGrid gamegrid;

    public Path(GameGrid gamegrid){
        this.gamegrid=gamegrid;
    }


    public GridLocation nextStep(GridLocation gridl ,  ArrayList<GridLocation>  pathlist) {          
     
  //it does not work ! why !!!?

        System.out.println(gridl);
        System.out.println(pathlist.indexOf(gridl));  // it returns -1 instead of zero as gridl does not exist
        return pathlist.get((pathlist.indexOf(new GridLocation(gridl))+2));       
        
    }


    //  return the shortest path as an array
    public int[][] shortestPath(GameGrid gamegrid) {
        int[][][] cntivity=gamegrid.connectivities();

        for (int i=0;i<gamegrid.getCases().length;i++){
            for (int j=0;j<gamegrid.getCases()[0].length;j++) {
                System.out.print(cntivity[i][j][1]+"  ");

            }
            System.out.println();
        }
        pathRelax(cntivity);
        int[][] shoretst=new int[cntivity.length][cntivity[0].length];
        for (int i =0 ; i<cntivity.length ; i++) {
            for (int j =0 ; j<cntivity[0].length ; j++) {
                shoretst[i][j]=cntivity[i][j][2];
            }
        }
        return shoretst;
    }

    
    
    // returns the shortest path as an array list starts with the entry point  
    public ArrayList<GridLocation> pathList(int[][][] connectivites) {
        ArrayList <GridLocation> pathlist=new ArrayList<GridLocation>();

        GridLocation grid=gamegrid.exitPoint();
        while(!(grid.xCoordinate== gamegrid.entryPoint().xCoordinate && grid.yCoordinate== gamegrid.entryPoint().yCoordinate)) {
            pathlist.add(0,grid);
            grid=minNeighbor(grid ,connectivites);

        }
        pathlist.add(0,this.gamegrid.entryPoint());
        return pathlist;
    }





    // calculate the shortest path in connectivities[][][2] cells
    public void pathRelax(int[][][] connectivites) {

        GridLocation grid=gamegrid.exitPoint();
        connectivites [grid.xCoordinate][grid.yCoordinate][2]=connectivites [grid.xCoordinate][grid.yCoordinate][1];
        while(!(grid.xCoordinate== gamegrid.entryPoint().xCoordinate && grid.yCoordinate== gamegrid.entryPoint().yCoordinate)) {
            grid=minNeighbor(grid ,connectivites);

            connectivites [grid.xCoordinate][grid.yCoordinate][2]=connectivites [grid.xCoordinate][grid.yCoordinate][1];

            //       
        }
        
    }


    
    //find the neighbor with the minimum distance from the entry ( closest)
    public GridLocation minNeighbor(GridLocation gridl, int[][][] connectivites) {
        int line=gridl.xCoordinate;
        int column=gridl.yCoordinate;
        GridLocation minNeighbor=gridl; 

        // check the left 
        if (isPath(line, column - 1, connectivites)) {
            if (connectivites[line][column - 1][1]<connectivites[minNeighbor.xCoordinate][minNeighbor.yCoordinate][1] ) {
                minNeighbor=new GridLocation(line,column-1);
            }   
        }

        //check above
        if (isPath(line-1, column, connectivites)) {
            if (connectivites[line-1][column][1]<connectivites[minNeighbor.xCoordinate][minNeighbor.yCoordinate][1] ) {
                minNeighbor=new GridLocation(line-1,column);
            }
        }

        //check below
        if (isPath(line+1, column, connectivites)) {
            if (connectivites[line+1][column][1]<connectivites[minNeighbor.xCoordinate][minNeighbor.yCoordinate][1] ) {
                minNeighbor=new GridLocation(line+1,column);
            }
        }


        // check the right 
        if (isPath(line, column + 1, connectivites)) {
            if (connectivites[line][column + 1][1]<connectivites[minNeighbor.xCoordinate][minNeighbor.yCoordinate][1] ) {
                minNeighbor=new GridLocation(line,column+1);
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

        if (connectivities[line][column][0]==0 ) {
            return false;
        }

        return true;
    }


}




