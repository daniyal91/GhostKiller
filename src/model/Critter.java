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
    private int burningDamage = 0;
    private boolean isFrozen = false;
    private boolean wasFreezed = false;

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

    /**
     * Indicates whether the critter is dead or not.
     *
     * @return true if the critter has any remaining health point,
     *              false otherwise.
     */
    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    /**
     * Indicates whether the critter is frozen or not.
     *
     * @return true if the critter is frozen, false otherwise.
     */
    public boolean isFrozen() {
        return this.wasFreezed;
    }

    /**
     * Attacks the current critter with the specified damage.
     * The critter's health can never go below 0.
     *
     * @param damage Damage to deal to the critter.
     * @param burning Boolean specifying if the damage is burning
     *                (if it lasts after the current turn.)
     */
    public void takeDamage(int damage, boolean burning) {
        this.healthPoints = Math.max(this.healthPoints - damage, 0);
        if (burning) {
            this.burningDamage += damage / 2;
        }
    }

    /**
     * Freezes down the current critter.
     */
    public void freeze() {
        this.isFrozen = true;
    }

    /**
     * Resets the effects associated with the critter.
     * This is somewhat similar to making the critter
     * turn-aware.
     */
    public void makeTurn() {
        if (this.wasFreezed) {
            this.wasFreezed = false;
        } else if (this.isFrozen) {
            this.wasFreezed = true;
        }
        this.isFrozen = false;
        this.healthPoints = Math.max(this.healthPoints - this.burningDamage, 0);
        this.burningDamage = 0;
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
