package model.tower;

import java.util.Collection;

import model.Critter;
import model.GridLocation;
import model.strategy.AttackStrategy;
import model.strategy.RandomStrategy;

/**
 * Base class for game towers
 *
 * @author Team 6
 *
 */
public class Tower {


    /**
     * Refund rate of the towers.
     */
    public static double REFUND_RATE = 0.40;

    /**
     * Special effects available for the towers.
     *
     */
    public static enum SPECIAL_EFFECTS {
        SLOW, SPLASH, TELEPORT
    }

    protected String name;
    protected String iconPath;
    protected int initialCost;
    protected int level = 1;
    protected int levelCost;
    protected int range;
    protected int power;
    protected int rateOfFire;
    protected SPECIAL_EFFECTS specialEffects;

    private AttackStrategy attackStrategy;
    private GridLocation location;


    /**
     * Default constructor for the Tower class.
     */
    public Tower() {}

    /**
     * Creates a Tower instance from an existing instance.
     * FIXME should find another way to create a Tower!
     *
     * @param T Tower instance to create the new instance from.
     */
    public Tower(Tower T, GridLocation location) {
        super();
        this.name = T.name;
        this.iconPath = T.iconPath;
        this.initialCost = T.initialCost;
        this.level = T.level;
        this.levelCost = T.levelCost;
        this.range = T.range;
        this.power = T.power;
        this.rateOfFire = T.rateOfFire;
        this.location = location;

        // FIXME remove hardcoded strategy.
        this.attackStrategy = new RandomStrategy();
    }

    public void attack(Collection<Critter> critters, GridLocation endPoint) {

        Critter critterToAttack = this.attackStrategy.attackCritter(this, critters, endPoint);
        if (critterToAttack != null) {
            System.out.println("Attacking critter" + critterToAttack.toString());
            critterToAttack.takeDamage(this.power);
        }

    }

    /**
     * Gets the special effects of the tower.
     */
    public SPECIAL_EFFECTS getSpecialEffects() {
        return specialEffects;
    }

    /**
     * Gets the name of the tower.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the path of the icon image used to represent the tower.
     *
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Gets the initial cost to buy the tower.
     */
    public int getInitialCost() {
        return initialCost;
    }

    /**
     * Gets the current level of the tower.
     */
    public int getLevel() {
        return level;
    }

    public GridLocation getLocation() {
        return this.location;
    }

    /**
     * Gets the cost to upgrade the level of the tower.
     *
     */
    public int getLevelCost() {
        return levelCost;
    }

    /**
     * Gets the range of attack of the tower.
     */
    public int getRange() {
        return range;
    }

    /**
     * Gets the attack power of the tower.
     */
    public int getPower() {
        return power;
    }

    /**
     * Gets the rate of fire of the tower.
     */
    public int getRateOfFire() {
        return rateOfFire;
    }

    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    /**
     * Returns a textual representation of the tower.
     */
    @Override
    public String toString() {
        return "Tower named " + this.name;
    }

    /**
     * Calculates the refund amount of the tower
     *
     * @return The refund amount of the tower.
     */
    public int refundAmout() {
        int totalCost = this.initialCost + (this.level * this.levelCost);
        return (int) (totalCost * Tower.REFUND_RATE);
    }

    /**
     * Upgrades the level of the tower by 1, and adjust its details accordingly
     */
    public void upgradeLevel() {
        this.level++;
        this.power *= 2;
        this.range *= 2;
        this.rateOfFire *= 2;
    }

}
