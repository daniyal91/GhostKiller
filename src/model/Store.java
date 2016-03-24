package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.Game;

public final class Store {
    static Game game;
    
    
    static void saveToFile(String filename){
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
            
            //towers
            for (int i = 0; i < game.getTowers().size(); i++) {
                 pr.print("t,");
                 pr.print(game.getTowers().get(i).getLocation().x+",");
                 pr.print(game.getTowers().get(i).getLocation().y+",");
                 pr.print(game.getTowers().get(i).getName()+",");
                 pr.print(game.getTowers().get(i).getLevel()+",");
                 pr.print(game.getTowers().get(i).getAttackStrategy().getName()+",");
                 pr.println();
            }
            
            //critter
            for (int i = 0; i < game.getTowers().size(); i++) {
                pr.print("c,");
              
                pr.println();
           }
            
            
            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        
        
        
    }
    

}
