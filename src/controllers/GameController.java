package controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import model.Game;
import model.Tower;
import views.GameView;

public class GameController implements MouseListener {

    private Game game;
    private GameView gameView;

    // Used to determine if a tower was selected for placement.
    private int selectedTower = -1;

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
            this.selectedTower = this.gameView.towerLabels.indexOf(event.getSource());
        // The user clicked on a tile on the game grid.
        } else {
            JButton buttonClicked = (JButton) event.getSource();
            if (buttonClicked.getIcon().toString().equals("icons/grass.jpg")) {
                if (this.selectedTower == -1) {
                    // TODO
                } else {
                    Tower tower = Game.AVAILABLE_TOWERS[this.selectedTower];
                    Point towerLocation = this.gameView.getButtonLocation(buttonClicked);
                    this.game.addTower(tower, towerLocation.x, towerLocation.y);
                    this.selectedTower = -1;
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
