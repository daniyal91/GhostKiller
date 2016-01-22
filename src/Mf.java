import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.io.File;

public class Mf extends JFrame {

  private JPanel contentPane;
  private JTextField textField;
  private JTextField textField_1;

  public Mf() {
    setTitle("Main Window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();


    contentPane.setBackground(Color.DARK_GRAY);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    JButton btnCreateMap = new JButton("Create Map");
    btnCreateMap.setBounds(36, 11, 110, 23);
    btnCreateMap.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String xText = textField.getText();
        int X =Integer.parseInt(xText);
        String yText = textField_1.getText();
        int Y = Integer.parseInt(yText);
        int[][] mapArr=new int[X][Y];
        Methods.editMap(mapArr);
      }

    });
    contentPane.setLayout(null);



    contentPane.add(btnCreateMap);

    JLabel lblX = new JLabel("X");
    lblX.setBounds(156, 15, 6, 14);
    contentPane.add(lblX);

    textField = new JTextField();
    textField.setBounds(168, 12, 86, 20);
    textField.setText("10");
    contentPane.add(textField);
    textField.setColumns(10);

    JLabel lblY = new JLabel("Y");
    lblY.setBounds(156, 43, 6, 14);
    contentPane.add(lblY);

    textField_1 = new JTextField();
    textField_1.setBounds(168, 40, 86, 20);
    textField_1.setText("10");
    contentPane.add(textField_1);
    textField_1.setColumns(10);

    JButton btnLoadMap = new JButton("Load Map");
    btnLoadMap.setBounds(36, 136, 110, 23);
    btnLoadMap.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          int[][] lArr=Methods.read(selectedFile.getName());
          Methods.showMap(lArr);
        }
      }
    });


    contentPane.add(btnLoadMap);
  }


}
