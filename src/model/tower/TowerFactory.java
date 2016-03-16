package model.tower;

/**
 * Factory class for creating Tower instances.
 *
 * @author Team 6
 *
 */
public class TowerFactory {

    /**
     * Creates the Tower instance associated with the name provided.
     *
     * @param towerName Name of the tower instance to create.
     *
     * @return A new Tower instance.
     */
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
