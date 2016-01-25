import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameGrid;

public class GameView {

    private JFrame gameFrame;

    public GameView(GameGrid gameGrid) {

        int row = gameGrid.getCases().length;
        int col = gameGrid.getCases()[0].length;

        this.gameFrame = new JFrame("Tower defense game");
        JPanel map = new JPanel(new GridLayout(row, col, 0, 0));

        for (int i = 0; i < row * col; i++) {
            JButton tile = new JButton();
            tile.setContentAreaFilled(false);
            tile.setFocusPainted(false);
            tile.setOpaque(false);
            tile.setBorderPainted(false);

            if (gameGrid.getCases()[i / col][i % row] == 1) {
                tile.setIcon(new ImageIcon("icons/road.jpg"));
            } else {
                tile.setIcon(new ImageIcon("icons/grass.jpg"));
            }

            map.add(tile);
        }

        this.gameFrame.setContentPane(map);
        this.gameFrame.setSize(400 * col / 10, 400 * row / 10);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);

    }


}
