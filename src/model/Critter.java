package model;

/**
 * Class representing a game critter.
 *
 * @author Team 6
 *
 */
public class Critter {

    public GridLocation gridLocation;

    public static int HEALTH_POINTS_PER_LEVEL = 30;
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

    /**
     * Attacks the current critter with the specified damage.
     * The critter's health can never go below 0.
     *
     * @param damage Damage to deal to the critter.
     */
    public void takeDamage(int damage) {
        this.healthPoints = Math.max(this.healthPoints - damage, 0);
    }

    public int getReward() {
        return Critter.REWARD_PER_LEVEL * this.level;
    }

    public void setLocation(GridLocation newLocation) {
        this.gridLocation = newLocation;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public String toString() {
        String template = "Critter level %s at position %s with %s remaining health points";
        return String.format(template, this.level, this.gridLocation.toString(), this.healthPoints);
    }

}
