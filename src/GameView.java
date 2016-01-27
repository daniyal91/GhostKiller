import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.GameGrid;

public class GameView {

    private JFrame gameFrame;
    private String selectedTower = ""; 
    
    public GameView(GameGrid gameGrid) {

        int row = gameGrid.getCases().length;
        int col = gameGrid.getCases()[0].length;

        this.gameFrame = new JFrame("Tower defense game");
        
        // mainPane to add all other panels
        JPanel mainPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPane.setLayout(new BorderLayout(0, 0));
        this.gameFrame.setContentPane(mainPane);
        
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
            
            tile.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent e) {
        			JButton placeTower = (JButton)e.getSource();
        			if(placeTower.getIcon().toString().equals("icons/grass.jpg"))
        			{
        				if(selectedTower.equals(""))
						{
        					// TODO
        				}
        				else
        				{
        					placeTower.setIcon(new ImageIcon(selectedTower));
                			selectedTower = "";
        				}
        			}
        		}
    	
        	});

            map.add(tile);
        }
        
        mainPane.add(map);
        
        //this.gameFrame.setContentPane(map);
        this.gameFrame.setSize(540 * col / 10, 820 * row / 10);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
        
        //Area where towers are displayed
        JPanel towerSelectionArea = new JPanel();
        towerSelectionArea.setBackground(Color.DARK_GRAY);
        mainPane.add(towerSelectionArea, BorderLayout.NORTH);
        JLabel towerSelectionText = new JLabel("Towers");
        towerSelectionText.setForeground(Color.white);
        towerSelectionArea.add(towerSelectionText);
        // Tower images
        final String[] towers= {"icons/AncientTower.png","icons/KingTower.png","icons/ModernTower.png"};
        
        // Adding tower 1 and its click listener
    	JLabel imgLabelTower1 = new JLabel(new ImageIcon(towers[0]));
    	towerSelectionArea.add(imgLabelTower1);
    	imgLabelTower1.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
	          selectedTower = towers[0];
    		}
	
    	});
	
    	 // Adding tower 2 and its click listener
    	JLabel imgLabelTower2 = new JLabel(new ImageIcon(towers[1]));
    	towerSelectionArea.add(imgLabelTower2);
    	imgLabelTower2.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
	          selectedTower = towers[1];
    		}
	
    	});
    	
    	 // Adding tower 3 and its click listener
    	JLabel imgLabelTower3 = new JLabel(new ImageIcon(towers[2]));
    	towerSelectionArea.add(imgLabelTower3);
    	imgLabelTower3.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
	          selectedTower = towers[2];
    		}
	
    	});
        
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
