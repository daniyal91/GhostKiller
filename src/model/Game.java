package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;

public class Game extends Observable {

    /**
     * Initial amount of money that the player has to buy towers.
     */
    public static int INITIAL_MONEY = 100;

    /**
     * List of available towers that the user can buy.
     */
    public static Tower[] AVAILABLE_TOWERS = {
                    new KingTower(),
                    new ModernTower(),
                    new AncientTower()
    };

    private final HashMap<Point, Tower> towers = new HashMap<Point, Tower>();
    public GameGrid grid;

    private int money;

    public Game() {
        this.grid = new GameGrid(100, 100);
        this.money = Game.INITIAL_MONEY;
    }

    public int getMoney() {
        return this.money;
    }

    /**
     * Buys a news tower and place it at the specified position on the grid.
     *
     * @param tower   The tower to buy.
     * @param line    Line where to place the new tower.
     * @param column  Column where to place the new tower.
     */
    public void buyTower(Tower tower, int line, int column) {;
        if (tower.getInitialCost() > this.money) {
            // TODO maybe raise an exception to notify there is not enough money left.
            return;
        }
        this.money -= tower.getInitialCost();
        this.addTower(tower, line, column);
    }

    /**
     * Sells a tower at a specific location of the game grid.
     *
     * @param line    Line where to place the new tower.
     * @param column  Column where to place the new tower.
     */
    public void sellTower(int line, int column) {

        Tower tower = this.getTower(line, column);
        this.money += tower.refundAmout();
        this.towers.remove(tower);
        this.setChanged();
        this.notifyObservers();

    }

    public void addTower(Tower t, int line, int column) {
        Point location = new Point(line, column);
        this.towers.put(location, t);
        this.setChanged();
        this.notifyObservers();
    }

    public boolean hasTower(int line, int column) {
        Point location = new Point(line, column);
        return this.towers.get(location) != null;
    }

    public Tower getTower(int line, int column) {
        Point location = new Point(line, column);
        return this.towers.get(location);
    }

}
