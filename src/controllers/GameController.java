package controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import model.Game;
import model.GameGrid;
import model.tower.Tower;
import views.GameView;

/**
 * Main controller for listening to user inputs from the GameView.
 *
 * @author Team 6
 *
 */
public class GameController implements MouseListener, ActionListener {

    private Game game;
    private GameView gameView;

    // Used to determine if a tower was selected for placement.
    private Tower selectedTower = null;

    /**
     * Constructs a new GameController object. Links the Game object to a GameView object using the Observer design
     * pattern.
     *
     * @param game Game object to use with the view object.
     *
     */
    public GameController(Game game) {
        this.game = game;
        this.gameView = new GameView(game, this);
        this.game.addObserver(this.gameView);
        this.gameView.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent event) {

        // We refuse to listen to the user actions during a wave.
        if (this.game.isMakingTurn()) {
            return;
        }

        // The user clicked on one of the tower images.
        if (this.gameView.towerLabels.indexOf(event.getSource()) != -1) {
            int selectedTowerIndex = this.gameView.towerLabels.indexOf(event.getSource());
            this.selectedTower = Game.AVAILABLE_TOWERS[selectedTowerIndex];
            this.gameView.showTowerDetails(this.selectedTower, false, 0, 0, game);

        }

        //just a temporary test to move a critter
        else if (event.getSource()==this.gameView.playButton){
            this.game.sendWave();
            System.out.print("play");
        }
        // The user clicked on a tile on the game grid.
        else {
            JButton buttonClicked = (JButton) event.getSource();
            //   if (buttonClicked) {}
            Point clickLocation = this.gameView.getButtonLocation(buttonClicked);
            GameGrid.CASE_TYPES caseType = this.game.grid.getCases()[clickLocation.x][clickLocation.y];
            if (caseType == GameGrid.CASE_TYPES.GRASS) {
                if (this.selectedTower == null && this.game.hasTower(clickLocation.x, clickLocation.y)) {
                    Tower tower = this.game.getTower(clickLocation.x, clickLocation.y);
                    this.gameView.showTowerDetails(tower, true, clickLocation.x, clickLocation.y, game);
                } else if (this.selectedTower != null) {
                    Point towerLocation = this.gameView.getButtonLocation(buttonClicked);
                    this.game.buyTower(this.selectedTower, towerLocation.x, towerLocation.y);
                    this.selectedTower = null;
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.gameView.strategyComboBox) {
            JComboBox<String> strategyCombo = (JComboBox<String>) e.getSource();
            gameView.selectedTower.setAttackStrategy((String) strategyCombo.getSelectedItem());
        }
        // TODO Auto-generated method stub

    }


}
