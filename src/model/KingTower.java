package model;

public class KingTower extends Tower {

    public KingTower() {
        this.name = "King tower";
        this.iconPath = "icons/KingTower.png";
        this.initialCost = 10;
        this.setLevel(1);
        this.setLevelCost(8);
        this.setPower(2);
        this.setRange(5);
        this.setRateOfFire(1);
    }

}
