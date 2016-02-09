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

    public Game() {
        this.grid = new GameGrid();
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
