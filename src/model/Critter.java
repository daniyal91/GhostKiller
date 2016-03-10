package model;

/**
 * Class representing a game critter.
 *
 * @author Team 6
 *
 */
public class Critter {

    public GridLocation gridLocation;

    public static int HEALTH_POINTS_PER_LEVEL = 100;
    public static int SPEED_PER_LEVEL = 20;
    public static int REWARD_PER_LEVEL = 50;

    private int healthPoints;
    private int level;

    public Critter(GridLocation gridLocation, int level) {
        this.gridLocation = gridLocation;
        this.healthPoints = Critter.HEALTH_POINTS_PER_LEVEL * level;
        this.level = level;
    }

    public Critter(Critter critter) {
        this.gridLocation = critter.gridLocation;
        this.healthPoints = Critter.HEALTH_POINTS_PER_LEVEL * critter.level;
        this.level = critter.level;
    }

    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
    }

}
