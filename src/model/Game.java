package model;

import java.util.HashMap;

public class Game {

    public static Tower[] AVAILABLE_TOWERS = {
                    new KingTower(),
                    new ModernTower(),
                    new AncientTower()
    };

    private final HashMap<GridLocation, Tower> towers = new HashMap<GridLocation, Tower>();
    private GameGrid grid;

    public Game() {

    }

    public void addTower(Tower t, int line, int column) {
        GridLocation location = new GridLocation(line, column);
        this.towers.put(location, t);
    }

    public boolean hasTower(int line, int column) {
        GridLocation location = new GridLocation(line, column);
        return this.towers.get(location) != null;
    }

    public Tower getTower(int line, int column) {
        GridLocation location = new GridLocation(line, column);
        return this.towers.get(location);
    }

}
