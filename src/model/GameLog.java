package model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import controllers.GameController;

public class GameLog implements Observer{

    private Game game;
    private GameController gamecontroller;

    public GameLog(Game game, GameController gamecontroller) {
        this.game = game;
        this.gamecontroller = gamecontroller;
    }



    @Override
    public void update(Observable observable, Object object) {
        Game game = (Game) observable;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd HH:mm:ss");

        if (game.logfile==null){    
            game.logfile=sdf.format(cal.getTime()).replaceAll(":", "-");;
            System.out.println( game.logfile);
        }

        PrintWriter pr;
        try {

            pr = new PrintWriter(new BufferedWriter(new FileWriter(game.logfile, true)));


            if (game.startlog){
                pr.println(initialLog(game));
                game.startlog=false;
            }
            pr.print(sdf.format(cal.getTime())+"   -> ");
            pr.println(game.log);

            pr.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String initialLog(Game game) {
        return "starting log"; 

    }





}
