package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;

import model.GameGrid.CASE_TYPES;
import model.tower.AncientTower;
import model.tower.KingTower;
import model.tower.ModernTower;
import model.tower.Tower;

/**
 * This class implements the main gaming logic in which user can buy, sell, upgrade towers. It is also observable so
 * that a view class can be notified of internal changes.
 *
 * @author Team 6
 *
 */
public class Game extends Observable {

    /**
     * Initial amount of money that the player has to buy towers.
     */
    public static int INITIAL_MONEY = 100;

    /**
     * List of available towers that the user can buy.
     */
    public static Tower[] AVAILABLE_TOWERS = {new KingTower(), new ModernTower(), new AncientTower()};

    private final HashMap<Point, Tower> towers = new HashMap<Point, Tower>();
    public GameGrid grid;

    public final HashMap<Point, Critter> critters = new HashMap<Point, Critter>();

    private int money;

    private Path shortestPath;

    private Thread gameThread;

    private int crittersPerWave = 1;

    private int crittersReleased;

    /**
     * Constructs the Game object with an empty 100x100 grid.
     */
    public Game() {
        this.grid = new GameGrid(100, 100);
        this.money = Game.INITIAL_MONEY;
        this.shortestPath = new Path(this.grid);
    }

    /**
     * Gets the current amount of money the player has.
     *
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Buys a news tower and place it at the specified position on the grid.
     *
     * @param tower The tower to buy.
     * @param line Line where to place the new tower.
     * @param column Column where to place the new tower.
     */
    public void buyTower(Tower tower, int line, int column) {
        if (tower.getInitialCost() > this.money) {
            // TODO maybe raise an exception to notify there is not enough money left.
            return;
        } else if (this.hasTower(line, column)) {
            // TODO maybe raise an exception to notify there is already a tower at this location.
            return;
        }
        this.money -= tower.getInitialCost();
        Tower newTower = new Tower(tower, new GridLocation(line, column));
        this.addTower(newTower, line, column);
    }

    /**
     * Sells a tower at a specific location of the game grid.
     *
     * @param line Line where to place the new tower.
     * @param column Column where to place the new tower.
     */
    public void sellTower(int line, int column) {
        Tower tower = this.getTower(line, column);
        this.money += tower.refundAmout();
        this.towers.remove(new Point(line, column));
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Adds a tower at the specific location.
     *
     * @param t Type of tower to place.
     * @param line Line where to place the new tower.
     * @param column Column where to place the new tower.
     */
    public void addTower(Tower t, int line, int column) {
        Point location = new Point(line, column);
        this.towers.put(location, t);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Checks if there is a tower at a specific location.
     *
     * @param line Line where to check for tower.
     * @param column Column where to check for tower.
     *
     * @return True if there is a tower at that location.
     */
    public boolean hasTower(int line, int column) {
        Point location = new Point(line, column);
        return this.towers.get(location) != null;
    }

    /**
     * Returns the tower at a specific location.
     *
     * @param line Line where tower is.
     * @param column Column where the tower is.
     *
     * @return Tower located at the specified location
     */
    public Tower getTower(int line, int column) {
        Point location = new Point(line, column);
        return this.towers.get(location);
    }

    /**
     * Upgrade the level of the tower at a specific location.
     *
     * @param line Line of the tower to uprade.
     * @param column Column of the tower to upgrade.
     */
    public void upgradeTower(int line, int column) {
        Tower tower = this.getTower(line, column);
        if (this.money >= tower.getLevelCost()) {
            tower.upgradeLevel();
            this.money -= tower.getLevelCost();
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Initiates a new wave of critters.
     */
    public void sendWave() {
        this.crittersReleased = 0;
        // FIXME : Remove hardcoded delay.
        this.gameThread = new GameThread(this, 1000);
        gameThread.start();
    }

    public void addCritter(Critter c) {
        this.critters.put(c.gridLocation, c);
        this.setChanged();
        this.notifyObservers();
    }

    public boolean hasCritter(int i, int j) {
        Point location = new Point(i, j);
        return (this.critters.get(location) != null);
    }

    public boolean noCritter(int i, int j) {
        Point location = new Point(i, j);
        return (this.grid.getCases()[i][j] == CASE_TYPES.ROAD && this.critters.get(location) == null);
    }

    /**
     * Makes a game turn. A turn consists of critters moving, towers shooting,
     * the player earning money and losing life points, etc.
     */
    public void makeTurn() {

        System.out.println("moveCritter");
        for (Object o : this.critters.keySet().toArray()) {

            Critter critter = this.critters.get(o);
            this.critters.remove(critter.gridLocation);

            GridLocation nextLocation = this.shortestPath.nextStep(critter.gridLocation);

            // There is another location the critter can move to.
            if (nextLocation != null) {
                critter.setLocation(nextLocation);
                this.addCritter(critter);
                System.out.println(nextLocation);
            }

        }

        if (this.crittersPerWave > this.crittersReleased) {

            GridLocation start = this.shortestPath.pathList().get(0);

            Critter critty = new Critter(start, 1);
            this.addCritter(critty);
            this.crittersReleased++;
            System.out.println("Adding a new critter on the grid.");

        }

        // This turn is over.
        if (this.critters.size() == 0 && this.crittersReleased == this.crittersPerWave) {
            System.out.print("Current turn is over.");
            this.gameThread.interrupt();

        }

        // Towers attacking if the turn is not over.
        for (Tower tower: this.towers.values()) {
            tower.attack(this.critters.values(), this.grid.exitPoint());
        }

        for (Critter critter: this.critters.values()) {
            System.out.println(critter);
            if (critter.isDead()) {
                this.money += critter.getReward();
                this.critters.remove(critter.gridLocation);
            }
        }

        this.setChanged();
        this.notifyObservers();

    }

}
