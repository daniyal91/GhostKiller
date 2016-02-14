package model;

public abstract class Tower {

    /**
     * Refund rate of the towers.
     */
    public static double REFUND_RATE = 0.40;

    public static enum SPECIAL_EFFECTS {
        SLOW, SPLASH, TELEPORT
    }

    protected String name;
    protected String iconPath;

    protected int initialCost;
    private int level;
    private int levelCost;

    private int range;
    private int rangeIncrease;

    private int power;
    private int powerIncrease;
    private int rateOfFire;

    private SPECIAL_EFFECTS specialEffects;

    public String getName() {
        return name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelCost() {
        return levelCost;
    }

    public int getRange() {
        return range;
    }

    public int getRangeIncrease() {
        return rangeIncrease;
    }

    public int getPower() {
        return power;
    }

    public int getPowerIncrease() {
        return powerIncrease;
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public SPECIAL_EFFECTS getSpecialEffects() {
        return specialEffects;
    }

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

}
