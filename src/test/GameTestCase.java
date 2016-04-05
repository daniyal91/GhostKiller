package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Game;
import model.tower.ExplosionTower;
import model.tower.Tower;

/**
 * This class performs tests for objects of Game class. 
 * @author Team 6
 *
 */

public class GameTestCase {

    private Game game;
    
    /**
     * This method runs before each test method.
     * It initiates an object of Game class.
     * @throws Exception
     */

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
    }
    /**
     * Adds a tower in location(0,0), and checks hasTower method.
     */

    @Test
    public void addTower() {
        Tower tower = new ExplosionTower();
        this.game.addTower(tower, 0, 0);
        assertTrue(this.game.hasTower(0, 0));
        assertFalse(this.game.hasTower(0, 1));
    }
    
    /**
     * Checks buyTower method. 
     */
    
    

    @Test
    public void buyTower() {
        Tower tower = new ExplosionTower();
        int towerCost = tower.getInitialCost();
        int initialGameMoney = this.game.getMoney();

        this.game.buyTower(tower, 0, 0);
        assertTrue(this.game.hasTower(0, 0));
        assertEquals(this.game.getMoney(), initialGameMoney - towerCost);

    }
    
    /**
     * Checks sellTower method.
     */

    @Test
    public void sellTower() {
        Tower tower = new ExplosionTower();
        int towerCost = tower.getInitialCost();
        int initialGameMoney = this.game.getMoney();

        this.game.buyTower(tower, 0, 0);
        this.game.sellTower(0, 0);
        assertEquals(this.game.getMoney(), initialGameMoney - towerCost + tower.refundAmout());
        assertTrue(this.game.getMoney() < initialGameMoney);
    }

}
