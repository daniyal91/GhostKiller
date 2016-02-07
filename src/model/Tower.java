package model;

public abstract class Tower {

    public static enum SPECIAL_EFFECTS {
        SLOW, SPLASH, TELEPORT
    }

    protected String name;
    protected String iconPath;

    private int initialCost;
    private int level;
    private int levelCost;
    private int refundRate;

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

    public int getRefundRate() {
        return refundRate;
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

}
