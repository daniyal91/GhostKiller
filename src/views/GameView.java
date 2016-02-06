package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.GameGrid;

public class GameView {

    public final static String[] TOWER_IMAGES =
                    {"icons/AncientTower.png", "icons/KingTower.png", "icons/ModernTower.png"};

    private JFrame gameFrame;
    public ArrayList<JLabel> towerLabels;

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

        for (int i = 0; i < row * col; i++) {
            JButton tile = new JButton();
            tile.setContentAreaFilled(false);
            tile.setFocusPainted(false);
            tile.setOpaque(false);
            tile.setBorderPainted(false);
            tile.setIcon(new ImageIcon("icons/grass2.jpg"));

            switch (gameGrid.getCases()[i / col][i % col]) {
                case GRASS:
                    tile.setIcon(new ImageIcon("icons/grass.jpg"));
                    break;
                case BUSH:
                    tile.setIcon(new ImageIcon("icons/grass2.jpg"));
                    break;
                case ROAD:
                    tile.setIcon(new ImageIcon("icons/road.jpg"));
                    break;
                case START:
                    tile.setIcon(new ImageIcon("icons/start.png"));
                    break;
                case END:
                    tile.setIcon(new ImageIcon("icons/end.png"));
                    break;
            }

            tile.addMouseListener(controller);

            map.add(tile);
        }

        mainPane.add(map);

        this.gameFrame.setSize(540 * col / 10, 700 * row / 10);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.gameFrame.setVisible(true);

        // Area where towers are displayed
        JPanel towerSelectionArea = new JPanel();
        towerSelectionArea.setBackground(Color.DARK_GRAY);
        mainPane.add(towerSelectionArea, BorderLayout.NORTH);
        JLabel towerSelectionText = new JLabel("Towers");
        towerSelectionText.setForeground(Color.white);
        towerSelectionArea.add(towerSelectionText);

        this.towerLabels = new ArrayList<JLabel>();

        // Adding towers and their click listeners
        for (int i = 0; i < TOWER_IMAGES.length; i++) {

            ImageIcon towerIcon = new ImageIcon(TOWER_IMAGES[i]);
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


}
