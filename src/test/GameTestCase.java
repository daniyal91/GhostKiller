package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Game;
import model.tower.AncientTower;
import model.tower.Tower;

public class GameTestCase {

    private Game game;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
    }

    @Test
    public void addTower() {
        Tower tower = new AncientTower();
        this.game.addTower(tower, 0, 0);
        assertTrue(this.game.hasTower(0, 0));
        assertFalse(this.game.hasTower(0, 1));
    }

    @Test
    public void buyTower() {
        Tower tower = new AncientTower();
        int towerCost = tower.getInitialCost();
        int initialGameMoney = this.game.getMoney();

        this.game.buyTower(tower, 0, 0);
        assertTrue(this.game.hasTower(0, 0));
        assertEquals(this.game.getMoney(), initialGameMoney - towerCost);

    }

    @Test
    public void sellTower() {
        Tower tower = new AncientTower();
        int towerCost = tower.getInitialCost();
        int initialGameMoney = this.game.getMoney();

        this.game.buyTower(tower, 0, 0);
        this.game.sellTower(0, 0);
        assertEquals(this.game.getMoney(), initialGameMoney - towerCost + tower.refundAmout());
        assertTrue(this.game.getMoney() < initialGameMoney);
    }

}
