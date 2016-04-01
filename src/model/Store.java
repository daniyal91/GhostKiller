package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.GameGrid.CASE_TYPES;
import model.tower.Tower;
import model.tower.TowerFactory;

public class Store {

    static void saveGame(Game game,String filename){
        PrintWriter pr;
        try {
            pr = new PrintWriter(filename);


            //size of the grid
            pr.print(game.grid.getCases().length + " " + game.grid.getCases()[0].length);

            //the gameGrid
            for (int i = 0; i < game.grid.getCases().length; i++) {
                pr.println();
                for (int j = 0; j < game.grid.getCases()[0].length; j++) {
                    pr.print(game.grid.getCases()[i][j].ordinal() + " ");
                }
            }

            pr.println();

            //health and money
            pr.print("h,");
            pr.println(game.getLives());
            pr.print("m,");
            pr.print(game.getMoney());

            //towers
            if (!game.getTowers().isEmpty()) {
                for (Tower t : game.getTowers().values()) {
                    pr.println();
                    pr.print("t,");
                    pr.print(t.getLocation().x+",");
                    pr.print(t.getLocation().y+",");
                    pr.print(t.getName()+",");
                    pr.print(t.getLevel()+",");
                    pr.print(t.getAttackStrategy().getName()+",");
                }
            }

            //critters
            if (!game.critters.isEmpty()) {
                for (Critter c : game.critters.values()) {
                    pr.println();
                    pr.print("c,");
                    pr.print(c.getLevel()+",");
                    pr.print(c.gridLocation.x+",");
                    pr.print(c.gridLocation.y+",");
                    pr.print(c.getHealthPoints()+",");
                    // any option is a critter is burning or not ?
                }

            }
            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }



    }


    static void loadGame(Game newgame, String filename) {

        int linenumber = 0; // line number starts from the second line
        int rows = 0;
        int columns = 0; // n customer, k teams

        // initializing the loaded Game and it's attributes
        // Game newgame=new Game();
        GameGrid newgrid=new GameGrid();
        newgrid.cases = new CASE_TYPES[1][1];
        HashMap<Point, Critter> newcritters = new HashMap<Point, Critter>();
        HashMap<Point, Tower> newtowers = new HashMap<Point, Tower>();
        int newmoney = 0,newhealth=0;
        String[] tokens;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = null;

            // read the 1st line, dimensions of the map
            line = br.readLine();
            tokens = line.split("\\s+");
            rows = Integer.parseInt(tokens[0]);
            columns = Integer.parseInt(tokens[1]);

            newgrid.cases = new CASE_TYPES[rows][columns];

            // read grids
            while ((line = br.readLine()).charAt(0) !='h')  {

                // \\s+ means any number of white spaces between tokens
                tokens = line.split("\\s+");

                for (int i = 0; i < columns; i++) {
                    int caseValue = Integer.parseInt(tokens[i]);
                    newgrid.cases[linenumber][i] = CASE_TYPES.values()[caseValue];
                }

                linenumber = linenumber + 1; // next line (lane) information

            } // end of read grids


            while ((line = br.readLine()) != null) {

                tokens = line.split(",");
                switch (tokens[0]) {
                    case "h":
                        newhealth=Integer.parseInt(tokens[1]);
                        break;
                    case "m":
                        newmoney=Integer.parseInt(tokens[1]);
                        break;
                    case "t":
                        String towertype="";
                        switch (tokens[3]){
                            case "Fire tower":
                                towertype="Fire tower";
                                break;
                            case "Ice tower":
                                towertype="Ice tower";
                                break;
                            case "Explosion tower":
                                towertype="Explosion towerr";
                                break;
                        }

                        Tower temptower=TowerFactory.createTower(towertype);
                        temptower.setLevel(Integer.parseInt(tokens[4]));
                        String strategy="";
                        switch (tokens[5]){
                            case "random":
                                strategy="random";
                                break;
                            case "nearest":
                                strategy="nearest";
                                break;
                            case "weakest":
                                strategy="weakest";
                                break;
                            case "strongest":
                                strategy="strongest";
                                break;
                            case "first":
                                strategy="first";
                                break;
                        }

                        temptower.setAttackStrategy(strategy);

                        newtowers.put(new Point(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2])),temptower);
                        break;
                    case "c" :
                        GridLocation temploc=new GridLocation(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
                        Critter tempcritter=new Critter(temploc,Integer.parseInt(tokens[1]));
                        if (tokens[4].equals("true")) {
                            tempcritter.freeze();
                        }
                        newcritters.put(temploc, tempcritter);
                        break;
                }

            }

            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        newgame.grid=newgrid;
        newgame.setLives(newhealth);
        newgame.setMoney(newmoney);
        newgame.critters=newcritters;
        newgame.setTowers(newtowers);

    }


}

