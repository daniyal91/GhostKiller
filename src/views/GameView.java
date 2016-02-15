package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Game;
import model.GameGrid;
import model.GameGrid.CASE_TYPES;
import model.Tower;

public class GameView implements Observer {

    private JFrame gameFrame;
    public ArrayList<JLabel> towerLabels;
    private JButton[][] tiles;
    private JLabel cashLabel;

    public GameView(Game game, MouseListener controller) {

        this.gameFrame = new JFrame("Tower defense game");

        // mainPane to add all other panels
        JPanel mainPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPane.setLayout(new BorderLayout(0, 0));
        this.gameFrame.setContentPane(mainPane);

        int row = game.grid.getCases().length;
        int col = game.grid.getCases()[0].length;
        JPanel map = new JPanel(new GridLayout(row, col, 0, 0));

        this.tiles = new JButton[row][col];

        for (int i = 0; i < row * col; i++) {

            this.tiles[i / col][i % col] = new JButton();
            this.tiles[i / col][i % col].setContentAreaFilled(false);
            this.tiles[i / col][i % col].setFocusPainted(false);
            this.tiles[i / col][i % col].setOpaque(false);
            this.tiles[i / col][i % col].setBorderPainted(false);

            int caseTypeOrdinal = game.grid.getCases()[i / col][i % col].ordinal();
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
        this.cashLabel = new JLabel("$" + game.getMoney());
        this.cashLabel.setForeground(Color.green);
        healthBankPanel.add(this.cashLabel);

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
        this.cashLabel.setText("$" + game.getMoney());
    }

    private void placeTower(int line, int column, Tower tower){
        this.tiles[line][column].setBackground(new Color(45,111,1));
        this.tiles[line][column].setOpaque(true);
        this.tiles[line][column].setIcon(new ImageIcon(tower.getIconPath()));
    }
    
    private void removeTower(int line, int column) {
        this.tiles[line][column].setIcon(new ImageIcon(GameGrid.CASE_TYPES_ICON_PATHS[0]));
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

    public void showTowerDetails(Tower t, boolean placedOnTile, final int x, final int y, final Game game) {
        // Open new window for tower inspection.
        final JFrame towerInspectionWindow = new JFrame("Tower Inspection");
        towerInspectionWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        towerInspectionWindow.setBounds(950,300,350,300);
        JPanel towerInspectionPanel = new JPanel();
        towerInspectionPanel.setBackground(Color.DARK_GRAY);
        towerInspectionWindow.setContentPane(towerInspectionPanel);
        // Tower Image and Sell button.
        JPanel towerImagePanel = new JPanel();
        towerInspectionPanel.add(towerImagePanel, BorderLayout.NORTH);
        JLabel towerImage = new JLabel(new ImageIcon(t.getIconPath()));
        towerImage.setBackground(Color.DARK_GRAY);
        towerImagePanel.setBackground(Color.DARK_GRAY);
        towerImagePanel.add(towerImage);
        if(placedOnTile)
        {
	        JButton sellTower = new JButton();
	        sellTower.setBackground(Color.white);
	        sellTower.setText("Sell Tower");
	        // TODO: Move this method to the controller
	        sellTower.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	            	game.sellTower(x, y);
	            	removeTower(x, y);
	            	towerInspectionWindow.dispose();
	            }
	        });
	        towerImagePanel.add(sellTower);
        }
        // Tower Details
        JPanel towerDetailsPanel = new JPanel();
        towerDetailsPanel.setBackground(Color.DARK_GRAY);
        towerDetailsPanel.setLayout(new GridLayout(0,2));
        towerInspectionPanel.add(towerDetailsPanel, BorderLayout.SOUTH);
        JLabel towerNameTxt = new JLabel("Name: ");
        towerNameTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerNameTxt);
        JLabel towerName = new JLabel(t.getName());
        towerName.setForeground(Color.white);
        towerDetailsPanel.add(towerName);
        JLabel towerLevelTxt = new JLabel("Level: ");
        towerLevelTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerLevelTxt);
        JLabel towerLevel = new JLabel(Integer.toString(t.getLevel()));
        towerLevel.setForeground(Color.white);
        towerDetailsPanel.add(towerLevel);
        JLabel towerInitCostTxt = new JLabel("Initial Cost: ");
        towerInitCostTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerInitCostTxt);
        JLabel towerInitCost = new JLabel(Integer.toString(t.getInitialCost()));
        towerInitCost.setForeground(Color.white);
        towerDetailsPanel.add(towerInitCost);
        JLabel towerCostLevelTxt = new JLabel("Cost to increase level: ");
        towerCostLevelTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerCostLevelTxt);
        JLabel towerCostLevel = new JLabel(Integer.toString(t.getLevelCost()));
        towerCostLevel.setForeground(Color.white);
        towerDetailsPanel.add(towerCostLevel);
        JLabel towerRangeTxt = new JLabel("Range: ");
        towerRangeTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerRangeTxt);
        JLabel towerRange = new JLabel(Integer.toString(t.getRange()));
        towerRange.setForeground(Color.white);
        towerDetailsPanel.add(towerRange);
        JLabel towerPowerTxt = new JLabel("Power: ");
        towerPowerTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerPowerTxt);
        JLabel towerPower = new JLabel(Integer.toString(t.getPower()));
        towerPower.setForeground(Color.white);
        towerDetailsPanel.add(towerPower);
        JLabel towerRateFireTxt = new JLabel("Rate of fire: ");
        towerRateFireTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerRateFireTxt);
        JLabel towerRateFire = new JLabel(Integer.toString(t.getRateOfFire()));
        towerRateFire.setForeground(Color.white);
        towerDetailsPanel.add(towerRateFire);
        JLabel towerSpecialEffectsTxt = new JLabel("Special Effects: ");
        towerSpecialEffectsTxt.setForeground(Color.white);
        towerDetailsPanel.add(towerSpecialEffectsTxt);
        JLabel towerSpecialEffects = new JLabel("Effects");
        towerSpecialEffects.setForeground(Color.white);
        towerDetailsPanel.add(towerSpecialEffects);
        if(placedOnTile)
        {
        	JLabel refundAmountTxt = new JLabel("Refund Amount: ");
        	refundAmountTxt.setForeground(Color.white);
            towerDetailsPanel.add(refundAmountTxt);
            JLabel refundAmount = new JLabel(Integer.toString(t.refundAmout()));
            refundAmount.setForeground(Color.white);
            towerDetailsPanel.add(refundAmount);
        }
        towerInspectionWindow.setVisible(true);
    }

}
