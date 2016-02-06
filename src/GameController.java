import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.GameGrid;
import views.GameView;

public class GameController implements MouseListener {

    private GameGrid gameGrid;
    private GameView gameView;

    // Used to determine if a tower was selected for placement.
    private int selectedTower = -1;

    public GameController(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.gameView = new GameView(gameGrid, this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        System.out.println(event.getSource());
        System.out.println(this.gameView.towerLabels.indexOf(event.getSource()));
        // The user clicked on one of the tower images.
        if (this.gameView.towerLabels.indexOf(event.getSource()) != -1) {
            this.selectedTower = this.gameView.towerLabels.indexOf(event.getSource());
            System.out.println(this.selectedTower);
        // The user clicked on a tile on the game grid.
        } else {
            JButton buttonClicked = (JButton) event.getSource();
            if (buttonClicked.getIcon().toString().equals("icons/grass.jpg")) {
                if (this.selectedTower == -1) {
                    // TODO
                } else {
                    String towerImagePath = GameView.TOWER_IMAGES[this.selectedTower];
                    buttonClicked.setIcon(new ImageIcon(towerImagePath));
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
