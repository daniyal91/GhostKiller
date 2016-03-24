package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

import model.Game;
import model.tower.Tower;

public class Store {

    static void saveToFile(Game game,String filename){
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
            pr.println(game.getMoney());

            //towers
            if (!game.getTowers().isEmpty()) {
                for (Tower t : game.getTowers().values()) {
                    pr.print("t,");
                    pr.print(t.getLocation().x+",");
                    pr.print(t.getLocation().y+",");
                    pr.print(t.getName()+",");
                    pr.print(t.getLevel()+",");
                    pr.print(t.getAttackStrategy().getName()+",");
                    pr.println();
                    
                }
            }

            //critters
            if (!game.critters.isEmpty()) {
                for (Critter c : game.critters.values()) {
                    pr.print("c,");
                    pr.print(c.gridLocation.x+",");
                    pr.print(c.gridLocation.y+",");
                    pr.print(c.getHealthPoints()+",");
                    pr.print(c.isFrozen()+",");
                    // any option is a critter is burning or not ? 
                    pr.println();
                }

            } 
            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }



    }


}

