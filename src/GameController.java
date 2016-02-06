import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.GameGrid;
import views.GameView;

public class GameController implements MouseListener {

    private GameGrid gameGrid;
    private GameView gameView;

    // Used to determine if a tower was selected for placement.
    private String selectedTower = "";

    public GameController(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

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
