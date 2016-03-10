package model;

/**
 * Class representing a game critter.
 *
 * @author Team 6
 *
 */
public class Critter {

    public GridLocation gridLocation;
    private int hitPoint;
    private int strength;
    private int speed;
    private int level;
    int health;

    public Critter(GridLocation gridLocation, int level) {
        this.gridLocation = gridLocation;
        this.level = level;
    }

    public Critter(Critter critter) {
        this.gridLocation = critter.gridLocation;
        this.health = critter.health;
    }

}
