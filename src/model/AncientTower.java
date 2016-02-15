package model;

public class AncientTower extends Tower {

    public AncientTower() {
        this.name = "Ancient tower";
        this.iconPath = "icons/AncientTower.png";
        this.initialCost = 20;
        this.setLevel(1);
        this.setLevelCost(15);
        this.setPower(4);
        this.setRange(8);
        this.setPowerIncrease(3);
        this.setRangeIncrease(3);
    }

}
