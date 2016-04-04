package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
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

    private HashMap<String, List<String>> logMap = new HashMap<String, List<String>>();
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




            String[] lines = game.log.split("\\\n");


            for (int i=0;i<lines.length;i++)
            {
                lines[i]="@" + shortfrmt.format(cal.getTime())+" "+lines[i];

                if (lines[i].indexOf("[")==18) {

                    //String key=lines[i].split("]")[0]+"]";
                    String key=lines[i].substring(10, 21);

                    System.out.println("put with key :"+key);
                    if (logMap.containsKey(key)){
                        logMap.get(key).add(lines[i]+"\n");
                    }
                    else {
                        List<String> newList=new ArrayList<String>();
                        newList.add(lines[i]+"\n");
                        logMap.put(key, newList);   
                    }
                }
            }


            if (game.isOver()){
                pr.println("--------------------------------------------------");
                pr.println("-----------object logs----------------------------");
                Iterator it = logMap.entrySet().iterator();
                while (it.hasNext()) {

                    Map.Entry pair = (Map.Entry)it.next();
                    List<String> sList=(ArrayList<String>) pair.getValue();
                    Set<String> hs =new LinkedHashSet<>();
                    hs.addAll(sList);
                    sList.clear();
                    sList.addAll(hs);

                    pr.println(pair.getKey() + "\n" + sList);
                    pr.println();
                    it.remove(); // avoids a ConcurrentModificationException
                }


                //   System.out.println(logMap.get("critter [1]"));
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
        init+="Game Grid ("+game.grid.filePath+") : "+game.grid.cases.length+" x "+game.grid.cases[0].length+"\n";
        init+="Map Entry Point : "+game.grid.entryPoint()+" | Map Exit Point : "+game.grid.exitPoint()+" \n";
        init+="Starting health = "+game.getLives()+"\n";
        init+="Starting money = "+game.getMoney()+" units\n";

        return init;
    }

}
