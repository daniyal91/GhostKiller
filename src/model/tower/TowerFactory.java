package model.tower;

public class TowerFactory {

    public static Tower createTower(String towerName) {
        if (towerName == "Fire tower") {
            return new FireTower();
        } else if (towerName == "Ice tower") {
            return new IceTower();
        } else if (towerName == "Explosion tower") {
            return new ExplosionTower();
        } else {
            throw new IllegalArgumentException("Invalid tower name " + towerName);
        }
    }

}
