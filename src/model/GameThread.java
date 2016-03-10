package model;

/**
 * The main game thread. This thread will call the makeTurn action
 * of the Game class until it is interrupted.
 *
 * @author Team 6
 *
 */
public class GameThread extends Thread {

    private int delay;
    private Game game;

    public GameThread(Game game, int delay) {
        this.delay = delay;
        this.game = game;
    }

    @Override
    public synchronized void run() {

        if (this.isInterrupted()) {
            return;
        }

        try {
            this.wait(this.delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.game.makeTurn();
        this.run();
    }

}
