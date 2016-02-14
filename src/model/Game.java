package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;

public class Game extends Observable {

    public static Tower[] AVAILABLE_TOWERS = {
                    new KingTower(),
                    new ModernTower(),
                    new AncientTower()
    };

    private final HashMap<Point, Tower> towers = new HashMap<Point, Tower>();
    public GameGrid grid;

    private int money;

    public Game() {
        this.grid = new GameGrid();
        this.money = 100;
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
    public void buyTower(Tower tower, int line, int column) {
        System.out.println(tower.getInitialCost());
        System.out.println(this.money);
        if (tower.getInitialCost() > this.money) {
            // TODO maybe raise an exception to notify there is not enough money left.
            return;
        }
        this.money -= tower.getInitialCost();
        this.addTower(tower, line, column);
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
