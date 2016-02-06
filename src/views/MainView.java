package views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * Main View for the program. Offers the user 3 choices : create a map, edit a map, or play the
 * game.
 *
 */
public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public JButton buttonCreate;
    public JButton buttonEdit;
    public JButton buttonLoad;
    public JTextField textFieldLines;
    public JTextField textFieldColumns;

    public MainView(ActionListener mainController) {

        setTitle("Tower defense main menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        this.buttonCreate = new JButton("Create Map");
        this.buttonCreate.setBounds(36, 11, 110, 23);
        this.buttonCreate.addActionListener(mainController);
        contentPane.add(this.buttonCreate);

        JLabel lblX = new JLabel("X");
        lblX.setBounds(156, 15, 6, 14);
        contentPane.add(lblX);

        this.textFieldLines = new JTextField();
        this.textFieldLines.setBounds(168, 12, 86, 20);
        this.textFieldLines.setText("10");
        this.textFieldLines.setColumns(10);
        contentPane.add(this.textFieldLines);

        JLabel lblY = new JLabel("Y");
        lblY.setBounds(156, 43, 6, 14);
        contentPane.add(lblY);

        this.textFieldColumns = new JTextField();
        this.textFieldColumns.setBounds(168, 40, 86, 20);
        this.textFieldColumns.setText("10");
        this.textFieldColumns.setColumns(10);
        contentPane.add(this.textFieldColumns);

        this.buttonEdit = new JButton("Edit Map");
        this.buttonEdit.setBounds(36, 100, 110, 23);
        this.buttonEdit.addActionListener(mainController);
        contentPane.add(this.buttonEdit);

        this.buttonLoad = new JButton("Play");
        this.buttonLoad.setBounds(36, 180, 110, 23);
        this.buttonLoad.addActionListener(mainController);

        contentPane.add(this.buttonLoad);
    }


}
