package controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import model.Game;
import model.GameGrid;
import model.Tower;
import views.GameView;

public class GameController implements MouseListener {

    private Game game;
    private GameView gameView;

    // Used to determine if a tower was selected for placement.
    private Tower selectedTower = null;

    public GameController(Game game) {
        this.game = game;
        this.gameView = new GameView(game.grid, this);
        this.game.addObserver(this.gameView);
        this.gameView.show();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // The user clicked on one of the tower images.
        if (this.gameView.towerLabels.indexOf(event.getSource()) != -1) {
            int selectedTowerIndex = this.gameView.towerLabels.indexOf(event.getSource());
            this.selectedTower = Game.AVAILABLE_TOWERS[selectedTowerIndex];
            this.gameView.showTowerDetails(this.selectedTower);

        // The user clicked on a tile on the game grid.
        } else {
            JButton buttonClicked = (JButton) event.getSource();
            Point clickLocation = this.gameView.getButtonLocation(buttonClicked);
            GameGrid.CASE_TYPES caseType = this.game.grid.cases[clickLocation.x][clickLocation.y];
            if (caseType == GameGrid.CASE_TYPES.GRASS) {
                if (this.selectedTower == null && this.game.hasTower(clickLocation.x,clickLocation.y)) {
                    Tower tower = this.game.getTower(clickLocation.x, clickLocation.y);
                    this.gameView.showTowerDetails(tower);
                } else {
                    Point towerLocation = this.gameView.getButtonLocation(buttonClicked);
                    this.game.addTower(this.selectedTower, towerLocation.x, towerLocation.y);
                    this.selectedTower = null;
                }
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


}
