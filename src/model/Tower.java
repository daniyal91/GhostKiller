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

   
    public SPECIAL_EFFECTS getSpecialEffects() {
        return specialEffects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(int initialCost) {
        this.initialCost = initialCost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelCost() {
        return levelCost;
    }

    public void setLevelCost(int levelCost) {
        this.levelCost = levelCost;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRangeIncrease() {
        return rangeIncrease;
    }

    public void setRangeIncrease(int rangeIncrease) {
        this.rangeIncrease = rangeIncrease;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPowerIncrease() {
        return powerIncrease;
    }

    public void setPowerIncrease(int powerIncrease) {
        this.powerIncrease = powerIncrease;
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public void setRateOfFire(int rateOfFire) {
        this.rateOfFire = rateOfFire;
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
