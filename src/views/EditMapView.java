package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import misc.utils;
import model.GameGrid;
import model.GameGrid.CASE_TYPES;

public class EditMapView implements ActionListener, MouseListener {

    private JFrame frame;
    public JButton saveButton;
    private GameGrid gameGrid;

    public JButton startPointButton;
    public JButton endPointButton;

    public JButton[][] tiles;
    public CASE_TYPES selectedCaseType;

    public EditMapView(GameGrid gameGrid, ActionListener controller) {

        this.selectedCaseType = CASE_TYPES.NONE;
        this.gameGrid = gameGrid;

        final int row = this.gameGrid.cases.length;
        final int col = this.gameGrid.cases[0].length;

        this.frame = new JFrame("Create or Edit map");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setBounds(100, 100, 400 * col / 10, 400 * row / 10 + 100);

        // mainPane contains all the other panels
        JPanel mainPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPane.setLayout(new BorderLayout(0, 0));
        this.frame.setContentPane(mainPane);

        JPanel keys = new JPanel();
        keys.setBackground(Color.DARK_GRAY);

        mainPane.add(keys, BorderLayout.SOUTH);

        this.saveButton = new JButton("save");
        this.saveButton.addActionListener(this);
        keys.add(this.saveButton);

        this.startPointButton = new JButton("Start Point");
        this.startPointButton.setBackground(Color.WHITE);
        this.startPointButton.addMouseListener(this);
        keys.add(this.startPointButton);

        this.endPointButton = new JButton("Finish Point");
        this.endPointButton.setBackground(Color.WHITE);
        this.endPointButton.addMouseListener(this);
        keys.add(this.endPointButton);

        JPanel map = new JPanel(new GridLayout(row, col, 2, 2));
        mainPane.add(map, BorderLayout.CENTER);

        this.tiles = new JButton[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                String iconPath = GameGrid.CASE_TYPES_ICON_PATHS[this.gameGrid.cases[i][j].ordinal()];
                ImageIcon tileIcon = new ImageIcon(iconPath);

                this.tiles[i][j] = new JButton();
                this.tiles[i][j].setIcon(tileIcon);
                this.tiles[i][j].setBorderPainted(false);
                this.tiles[i][j].setContentAreaFilled(false);
                this.tiles[i][j].setFocusPainted(false);

                // action listener for tiles for changing them to path
                this.tiles[i][j].addActionListener(this);

                map.add(this.tiles[i][j]);

            }
        }

        frame.setVisible(true);

    }

    private boolean saveMap() {


        if (this.gameGrid.isConnected()) {

            String filePath = utils.selectFile();
            if (filePath != null) {
                this.gameGrid.writeToFile(filePath);
                return true;
            }
        }

        else {
            this.showMessage("Sorry, no connecting path between entry point and exit point!", "Invalid map");
        }
        return false;
    }

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message,  title, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == this.saveButton && this.saveMap()) {
            frame.dispose();
        } else {
            this.updateTile(event.getSource());
        }
    }


    private void updateTile(Object source) {

        for (int i = 0; i < this.gameGrid.cases.length; i++) {
            for (int j = 0; j < this.gameGrid.cases[0].length; j++) {
                if (source == this.tiles[i][j]) {
                    this.toggleTile(i, j);
                }
            }
        }
    }

    private void toggleTile(int row, int column) {

        CASE_TYPES selectedCaseType = CASE_TYPES.NONE;

        // A special case type was selected, we place it on the grid.
        if (this.selectedCaseType != CASE_TYPES.NONE) {
            selectedCaseType = this.selectedCaseType;
            this.selectedCaseType = CASE_TYPES.NONE;

        // No special case type was selected, we will toggle between grass and road.
        } else {
            if (this.gameGrid.cases[row][column] == CASE_TYPES.ROAD) {
                selectedCaseType = CASE_TYPES.GRASS;
            } else {
                selectedCaseType = CASE_TYPES.ROAD;
            }
        }

        String iconPath = GameGrid.CASE_TYPES_ICON_PATHS[selectedCaseType.ordinal()];
        this.tiles[row][column].setIcon(new ImageIcon(iconPath));
        this.gameGrid.cases[row][column] = selectedCaseType;

    }

    public void show() {
        this.frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == this.startPointButton) {
            this.selectedCaseType = CASE_TYPES.START;
        } else if (event.getSource() == this.endPointButton) {
            this.selectedCaseType = CASE_TYPES.END;
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
