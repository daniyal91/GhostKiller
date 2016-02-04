import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.GameGrid;


public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldLine;
    private JTextField textFieldColumn;

    public JButton buttonCreate;
    public JButton buttonEdit;
    public JButton buttonLoad;

    public MainFrame() {

        setTitle("Tower defense game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        this.buttonCreate = new JButton("Create Map");
        this.buttonCreate.setBounds(36, 11, 110, 23);
        this.buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String lineText = textFieldLine.getText();
                int lineCount = Integer.parseInt(lineText);
                String columnText = textFieldColumn.getText();
                int columnCount = Integer.parseInt(columnText);
                int[][] mapArr = new int[lineCount][columnCount];
                EditMapView mapView = new EditMapView(mapArr);
            }

        });
        contentPane.setLayout(null);
        contentPane.add(this.buttonCreate);

        JLabel lblX = new JLabel("X");
        lblX.setBounds(156, 15, 6, 14);
        contentPane.add(lblX);

        textFieldLine = new JTextField();
        textFieldLine.setBounds(168, 12, 86, 20);
        textFieldLine.setText("10");
        contentPane.add(textFieldLine);
        textFieldLine.setColumns(10);

        JLabel lblY = new JLabel("Y");
        lblY.setBounds(156, 43, 6, 14);
        contentPane.add(lblY);

        textFieldColumn = new JTextField();
        textFieldColumn.setBounds(168, 40, 86, 20);
        textFieldColumn.setText("10");
        contentPane.add(textFieldColumn);
        textFieldColumn.setColumns(10);

        this.buttonEdit = new JButton("Edit Map");
        this.buttonEdit.setBounds(36, 100, 110, 23);
        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileChooser = new JFileChooser();
                File currentDir = new File(System.getProperty("user.dir"));
                fileChooser.setCurrentDirectory(currentDir);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    GameGrid gameGrid = new GameGrid();
                    gameGrid.readFromFile(selectedFile.getName());

                    // isConnected() must be checked when a grid is loaded
                    if (gameGrid.isConnected()) {
                        System.out.println("the path has entrance/exit and it's connected");
                    } else {
                        System.out.println(
                                        "the path is not connected or has not entrance/exit point");
                    }

                    EditMapView mapView = new EditMapView(gameGrid.cases);
                }

            }

        });
        contentPane.setLayout(null);
        contentPane.add(this.buttonEdit);

        this.buttonLoad = new JButton("Play");
        this.buttonLoad.setBounds(36, 180, 110, 23);
        this.buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                File currentDir = new File(System.getProperty("user.dir"));
                fileChooser.setCurrentDirectory(currentDir);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    GameGrid gameGrid = new GameGrid();
                    gameGrid.readFromFile(selectedFile.getName());

                    // isConnected() must be checked when a grid is loaded
                    if (gameGrid.isConnected()) {
                        System.out.println("the path has entrance/exit and it's connected");
                    } else {
                        System.out.println(
                                        "the path is not connected or has not entrance/exit point");
                    }
                    GameView gameView = new GameView(gameGrid);
                }
            }
        });

        contentPane.add(this.buttonLoad);
    }


}
