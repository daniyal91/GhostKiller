package model;

public class GameThread extends Thread {

    private int timeout;
    private Game game;

    public GameThread(Game game) {
        this.timeout = 1000;
        this.game = game;
    }

    @Override
    public synchronized void run() {
        try {
            this.wait(this.timeout);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.game.moveCritter();
        this.run();
    }

}
