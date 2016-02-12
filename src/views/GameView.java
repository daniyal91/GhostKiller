package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Game;
import model.GameGrid;
import model.Tower;

public class GameView implements Observer {

    private JFrame gameFrame;
    public ArrayList<JLabel> towerLabels;
    private JButton[][] tiles;

    public GameView(GameGrid gameGrid, MouseListener controller) {

        this.gameFrame = new JFrame("Tower defense game");

        // mainPane to add all other panels
        JPanel mainPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPane.setLayout(new BorderLayout(0, 0));
        this.gameFrame.setContentPane(mainPane);

        int row = gameGrid.getCases().length;
        int col = gameGrid.getCases()[0].length;
        JPanel map = new JPanel(new GridLayout(row, col, 0, 0));

        this.tiles = new JButton[row][col];

        for (int i = 0; i < row * col; i++) {

            this.tiles[i / col][i % col] = new JButton();
            this.tiles[i / col][i % col].setContentAreaFilled(false);
            this.tiles[i / col][i % col].setFocusPainted(false);
            this.tiles[i / col][i % col].setOpaque(false);
            this.tiles[i / col][i % col].setBorderPainted(false);

            int caseTypeOrdinal = gameGrid.getCases()[i / col][i % col].ordinal();
            String iconPath = GameGrid.CASE_TYPES_ICON_PATHS[caseTypeOrdinal];
            this.tiles[i / col][i % col].setIcon(new ImageIcon(iconPath));

            this.tiles[i / col][i % col].addMouseListener(controller);

            map.add(this.tiles[i / col][i % col]);
        }

        mainPane.add(map);

        this.gameFrame.setSize(540 * col / 10, 700 * row / 10);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Area where towers are displayed
        JPanel towerSelectionArea = new JPanel();
        towerSelectionArea.setBackground(Color.DARK_GRAY);
        mainPane.add(towerSelectionArea, BorderLayout.NORTH);
        JLabel towerSelectionText = new JLabel("Towers");
        towerSelectionText.setForeground(Color.white);
        towerSelectionArea.add(towerSelectionText);

        this.towerLabels = new ArrayList<JLabel>();

        // Adding towers and their click listeners
        for (int i = 0; i < Game.AVAILABLE_TOWERS.length; i++) {

            Tower tower = Game.AVAILABLE_TOWERS[i];
            ImageIcon towerIcon = new ImageIcon(tower.getIconPath());
            JLabel towerLabel = new JLabel(towerIcon);

            this.towerLabels.add(towerLabel);
            towerSelectionArea.add(towerLabel);
            towerLabel.addMouseListener(controller);

        }

        // Health and bank panel
        JPanel healthBankPanel = new JPanel();
        healthBankPanel.setBackground(Color.DARK_GRAY);
        mainPane.add(healthBankPanel, BorderLayout.SOUTH);

        // Bank image
        JLabel bankImgLabel = new JLabel(new ImageIcon("icons/bank_icon.png"));
        healthBankPanel.add(bankImgLabel);
        JLabel bankTxt = new JLabel("$100");
        bankTxt.setForeground(Color.green);
        healthBankPanel.add(bankTxt);

        // Health image
        JLabel lifeImgLabel = new JLabel(new ImageIcon("icons/life_icon.png"));
        healthBankPanel.add(lifeImgLabel);
        JLabel lifeTxt = new JLabel("75%");
        lifeTxt.setForeground(Color.green);
        healthBankPanel.add(lifeTxt);

    }

    public void show() {
        this.gameFrame.setVisible(true);
    }

    /**
     * After it is instantiated, the view should only be updated
     * using this method. The view should know what to look
     * for in the Game object in order to update it's representation.
     */
    @Override
    public void update(Observable observable, Object object) {
        Game game = (Game) observable;
        // For now, we only update the locations of the towers.
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                if (game.hasTower(i, j)) {
                    this.placeTower(i, j, game.getTower(i, j));
                }
            }
        }
    }

    private void placeTower(int line, int column, Tower tower){     
        this.tiles[line][column].setBackground(new Color(45,111,1));
        this.tiles[line][column].setOpaque(true);
        this.tiles[line][column].setIcon(new ImageIcon(tower.getIconPath()));
    }

    public Point getButtonLocation(JButton button) {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                if (this.tiles[i][j] == button) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void showTowerDetails(Tower t) {
        // TODO show the details of the tower in a new panel.
        System.out.println(t);
    }


}
