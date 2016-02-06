package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.GameGrid;
import model.GameGrid.CASE_TYPES;

public class EditMapView implements ActionListener {

    private JFrame frame;
    private JButton saveButton;
    private GameGrid gameGrid;

    private JButton startPoint;
    private JButton endPoint;
    private String selectedKey = "";

    private JButton[][] tiles;
    private ImageIcon roadIcon;
    private ImageIcon grassIcon;
    private ImageIcon startIcon;
    private ImageIcon finishIcon;

    public EditMapView(final CASE_TYPES[][] mapArr) {

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
        keys.setBackground(Color.DARK_GRAY);

        mainPane.add(keys, BorderLayout.SOUTH);

        this.saveButton = new JButton("save");
        keys.add(this.saveButton);


        this.startPoint = new JButton("Start Point");
        startPoint.setBackground(Color.WHITE);
        this.endPoint = new JButton("Finish Point");
        endPoint.setBackground(Color.WHITE);


        keys.add(startPoint);
        keys.add(endPoint);

        startPoint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedKey = "icons/start.png";
            }

        });

        endPoint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedKey = "icons/end.png";
            }

        });



        this.saveButton.addActionListener(this);

        JPanel map = new JPanel(new GridLayout(row, col, 2, 2));
        mainPane.add(map, BorderLayout.CENTER);

        this.tiles = new JButton[row][col];

        this.roadIcon = new ImageIcon("icons/road.jpg");
        this.grassIcon = new ImageIcon("icons/grass.jpg");
        this.startIcon = new ImageIcon("icons/start.png");
        this.finishIcon = new ImageIcon("icons/end.png");



        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                this.tiles[i][j] = new JButton();
                JButton currentTile = this.tiles[i][j];

                switch (this.gameGrid.cases[i][j]) {

                    case GRASS:
                        currentTile.setIcon(this.grassIcon);
                        break;

                    case ROAD:
                        currentTile.setIcon(this.roadIcon);
                        break;

                    case START:
                        currentTile.setIcon(this.startIcon);
                        break;

                    case END:
                        currentTile.setIcon(this.finishIcon);
                        break;

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

    private boolean saveMap() {


        if (this.gameGrid.isConnected()) {

            System.out.println("the path has entrance/exit and it's connected");
            JFileChooser fileChooser = new JFileChooser();
            File currentDir = new File(System.getProperty("user.dir"));
            fileChooser.setCurrentDirectory(currentDir);
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                this.gameGrid.writeToFile(selectedFile.getName());
                return true;
            }
        }

        else {
            JOptionPane.showMessageDialog(null,
                            "the path is not connected or invalid entrance/exit point(s) ",
                            "Try Again", JOptionPane.WARNING_MESSAGE);
        }
        return false;
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
        if (selectedKey.equals("")) {
            if (this.gameGrid.cases[row][column] != CASE_TYPES.ROAD) {
                this.gameGrid.cases[row][column] = CASE_TYPES.ROAD;
                this.tiles[row][column].setIcon(this.roadIcon);

            } else {
                this.gameGrid.cases[row][column] = CASE_TYPES.GRASS;
                this.tiles[row][column].setIcon(this.grassIcon);

            }
        } else {
            this.tiles[row][column].setIcon(new ImageIcon(selectedKey));
            CASE_TYPES selectedCaseType = selectedKey.equals("icons/start.png") ? CASE_TYPES.START : CASE_TYPES.END;
            this.gameGrid.cases[row][column] = selectedCaseType;
            selectedKey = "";
        }
    }

    public void show() {
        this.frame.setVisible(true);
    }

}
