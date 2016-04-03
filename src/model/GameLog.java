package model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import controllers.GameController;
/**
 * It implements the Observer interface to get
 * informed of changes in the Game class objects.
 * @author Team 6
 *
 */

public class GameLog implements Observer {

    private Game game;
    private GameController gamecontroller;
    private String oldlog="";

    private SimpleDateFormat longfrmt = new SimpleDateFormat("dd MMM HH:mm:ss");
    private SimpleDateFormat shortfrmt = new SimpleDateFormat("HH:mm:ss");

    private HashMap<String, Set<String>> logMap = new HashMap<String, Set<String>>();
    /**
     * Constructs the game log object.
     * @param game an object of Game class representing the game 
     * @param gamecontroller an object of GameController class representing game controller 
     */

    public GameLog(Game game, GameController gamecontroller) {
        this.game = game;
        this.gamecontroller = gamecontroller;
    }

    @Override
    public void update(Observable observable, Object object) {
        Game game = (Game) observable;


        if (game.logfile==null){
            Calendar cal = Calendar.getInstance();
            game.logfile=longfrmt.format(cal.getTime()).replaceAll(":", "-");;
            game.logfile="GameLog-"+game.logfile;
            System.out.println( game.logfile);
        }

        PrintWriter pr;
        PrintWriter prt;
        PrintWriter prc;
        try {

            pr = new PrintWriter(new BufferedWriter(new FileWriter(game.logfile, true)));


            if (game.startlog){
                pr.println(initialLog(game));
                game.startlog=false;
            }
            Calendar cal = Calendar.getInstance();

            if (!game.log.equals(oldlog) && game.log!=""){
            pr.println(shortfrmt.format(cal.getTime())+"   -> ");
            pr.println(game.log);
            }

            String key=game.log.split("]")[0];
            System.out.println(key);
            if (game.isOver()){
                pr.println("over");
            }
            oldlog=game.log;
            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * Returns a string which shows the log
     * @param game an object of Game class 
     * @return a string representing initial log
     */

    private String initialLog(Game game) {
        String init;
        Calendar cal = Calendar.getInstance();
        init=longfrmt.format(cal.getTime())+"  Game Started \n";
        init+="Game Grid ("+game.grid.mapname+") : "+game.grid.cases.length+" x "+game.grid.cases[0].length+"\n";
        init+="Map Entry Point : "+game.grid.entryPoint()+" | Map Exit Point : "+game.grid.exitPoint()+" \n";
        init+="Starting health = "+game.getLives()+"\n";
        init+="Starting money = "+game.getMoney()+" units\n";

        return init;
    }

}
