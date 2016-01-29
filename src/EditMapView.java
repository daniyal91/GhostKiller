import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.GameGrid;

public class EditMapView implements ActionListener {

    private JFrame frame;
    private JButton saveButton;
    private GameGrid gameGrid;

    private JButton[][] tiles;

    private ImageIcon roadIcon;
    private ImageIcon grassIcon;

    public EditMapView (final int[][] mapArr) {

        this.gameGrid = new GameGrid();
        this.gameGrid.cases = mapArr;

        final int row = mapArr.length;
        final int col = mapArr[0].length;

        this.frame = new JFrame("Create or Edit map");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setBounds(100, 100, 400 * col / 10, 400 * row / 10 + 100);

        // mainPane contains all the other panels
        JPanel mainPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPane.setLayout(new BorderLayout(0, 0));
        this.frame.setContentPane(mainPane);

        JPanel keys = new JPanel();
        mainPane.add(keys, BorderLayout.SOUTH);

        this.saveButton = new JButton("save");
        keys.add(this.saveButton);

        this.saveButton.addActionListener(this);

        JPanel map = new JPanel(new GridLayout(row, col, 2, 2));
        mainPane.add(map, BorderLayout.CENTER);

        this.tiles = new JButton[row][col];

        this.roadIcon = new ImageIcon("icons/road.jpg");
        this.grassIcon = new ImageIcon("icons/grass.jpg");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                this.tiles[i][j] = new JButton();
                JButton currentTile = this.tiles[i][j];

                if (this.gameGrid.cases[i][j] == 0) {
                    currentTile.setIcon(this.grassIcon);
                } else {
                    currentTile.setIcon(this.roadIcon);
                }

                currentTile.setBorderPainted(false);
                currentTile.setContentAreaFilled(false);
                currentTile.setFocusPainted(false);

                // action listener for tiles for changing them to path
                currentTile.addActionListener(this);

                map.add(currentTile);
            }
        }

        frame.setVisible(true);

    }

    private void saveMap() {

        JFileChooser fileChooser = new JFileChooser();
        File currentDir = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(currentDir);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // isConnected() must be checked when a grid is loaded
            if (this.gameGrid.isConnected()) {
                System.out.println("the path has entrance/exit and it's connected");
            } else {
                System.out.println(
                                "the path is not connected or has not entrance/exit point");
            }

            this.gameGrid.writeToFile(selectedFile.getName());

        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == this.saveButton) {
            this.saveMap();
            frame.dispose();
            
        } else {
            this.updateTile(event.getSource());
        }
    }


    private void updateTile(Object source) {
        for (int i = 0; i < this.gameGrid.cases.length; i++) {
            for (int j = 0; j < this.gameGrid.cases[0].length; j++) {
                if(source == this.tiles[i][j]) {
                    this.toggleTile(i, j);
                }
            }
        }
    }

    private void toggleTile(int row, int column){
        if (this.gameGrid.cases[row][column] == 0) {
            this.gameGrid.cases[row][column] = 1;
            this.tiles[row][column].setIcon(this.roadIcon);
        } else {
            this.gameGrid.cases[row][column] = 0;
            this.tiles[row][column].setIcon(this.grassIcon);
        }
    }

    public void show() {
        this.frame.setVisible(true);
    }

}
