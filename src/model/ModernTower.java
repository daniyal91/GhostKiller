package model;

public class ModernTower extends Tower {

    public ModernTower() {
        this.name = "Modern tower";
        this.iconPath = "icons/ModernTower.png";
        this.initialCost = 5;
        this.setLevel(1);
        this.setLevelCost(4);
        this.setPower(1);
        this.setRange(3);
        this.setRateOfFire(1);
    }

}
