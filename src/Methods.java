import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Methods {

  // write an array into the text file
  public static void write(int[][] arr, String filename) {
    PrintWriter pr;
    try {
      pr = new PrintWriter(filename);
      pr.print(arr.length + " " + arr[0].length);

      for (int i = 0; i < arr.length; i++) {
        pr.println();
        for (int j = 0; j < arr[0].length; j++) {
          pr.print(arr[i][j] + " ");
        }
      }
      // pr.println(Arrays.deepToString(mapArr));

      pr.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // read and return an array from the text file "filename"
  public static int[][] read(String filename) {

    int linenumber = 0; // line number starts from the second line
    int rows = 0, columns = 0; // n customer, k teams

    int[][] arrRead = new int[1][1]; //
    BufferedReader br = null;

    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      String line = null;

      // read the 1st line, dimensions of the map
      line = br.readLine();
      String[] tokens = line.split("\\s+");
      rows = Integer.parseInt(tokens[0]);
      columns = Integer.parseInt(tokens[1]);

      arrRead = new int[rows][columns];

      // read other lines
      while ((line = br.readLine()) != null) {
        // \\s+ means any number of white spaces between tokens
        tokens = line.split("\\s+");

        for (int i = 0; i < columns; i++) {
          arrRead[linenumber][i] = Integer.parseInt(tokens[i]);
        }

        linenumber = linenumber + 1; // next line (lane) information

      } // while line

      br.close();
    } // try

    catch (IOException e) {
      e.printStackTrace();
    }

    // System.out.println(Arrays.deepToString(arrRead));

    return arrRead;
  }

  // to check if a map in connected, assumed that entrance is at the left edge
  // and exit at the right edge of the map
  public static boolean cntcheck(int [][] map) {

    //	int[][] map;
    //map = Methods.read(filename);

    // a new int[][] with the same dimension for tracking the connectivity
    int[][][] Cn = new int[map.length][map[0].length][1];

    // check if it has a entry point before it :

    if (entP(map) > -1) {
      Cn[entP(map)][0][0] = 1;
      connect(map, Cn, entP(map), 0);
      System.out.println("entrance point: (" + entP(map) + ",0)");
      System.out.println("exit point: (" + extP(map) + "," + map[0].length + ")");
    } else {

      System.out.println("no entrance point");
    }

    /*
     * for (int i=0;i<map.length;i++) { for (int j=0;j<map[0].length;j++) {
     * System.out.print(Cn[i][j][0]); } System.out.println(); }
     */

    if (extP(map) > -1)
      return (Cn[extP(map)][map[0].length - 1][0] == 1);
    else
      return false;

  }

  // side method for cntcheck method, it connects the neighbor of the
  // tile(i,j)
  // together if they are path tiles, from the entrance to the exit point
  public static void connect(int[][] map, int[][][] Cn, int i, int j) {

    // check the right neighbor
    if (j < map[0].length - 1 && map[i][j + 1] == 1 && Cn[i][j + 1][0] != 1) {
      Cn[i][j + 1][0] = 1;
      connect(map, Cn, i, j + 1);
    }

    // check the below neighbor
    if (i < map.length - 1 && map[i + 1][j] == 1 && Cn[i + 1][j][0] != 1) {
      Cn[i + 1][j][0] = 1;
      connect(map, Cn, i + 1, j);
    }

    // check the above neighbor
    if (i > 0 && map[i - 1][j] == 1 && Cn[i - 1][j][0] != 1) {
      Cn[i - 1][j][0] = 1;
      connect(map, Cn, i - 1, j);
    }

    // check the left neighbor
    if (j > 0 && map[i][j - 1] == 1 && Cn[i][j - 1][0] != 1) {
      Cn[i][j - 1][0] = 1;
      connect(map, Cn, i, j - 1);
    }

  }

  // return the entrance point height at the left edge , if not returns -1
  // it has been assumed that the entrance point in always at right edge of
  // the map
  public static int entP(int[][] arr) {
    int entHgth = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i][0] == 1) {
        entHgth = i;
        break;
      }
    }
    return entHgth;
  }

  // return the exit point height at the right edge , if not returns -1
  public static int extP(int[][] arr) {
    int extHgth = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i][arr[0].length - 1] == 1) {
        extHgth = i;
        break;
      }
    }
    return extHgth;
  }

  /**
   * @wbp.parser.entryPoint
   */
  public static void editMap(final int[][] mapArr) {

    final int row = mapArr.length;
    final int col = mapArr[0].length;

    JFrame frame = new JFrame("Create or Edit map");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(100, 100, 400 * col / 10, 400 * row / 10 + 100);

    // mainPane contains all the other panels
    JPanel mainPane = new JPanel();
    mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    mainPane.setLayout(new BorderLayout(0, 0));
    frame.setContentPane(mainPane);

    JPanel keys = new JPanel();
    mainPane.add(keys, BorderLayout.SOUTH);

    JButton save = new JButton("save");
    keys.add(save);
    final JTextField textField = new JTextField();
    keys.add(textField);
    textField.setColumns(20);
    textField.setText("enter the name of the file");

    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("save button");
        String filename = textField.getText();

        Methods.write(mapArr, filename);
      }

    });

    JPanel map = new JPanel(new GridLayout(row, col, 2, 2));
    mainPane.add(map, BorderLayout.CENTER);
    // inserting buttons (tiles)

    for (int i = 0; i < row * col; i++) {

      final Icon gIcon = new ImageIcon("icons/grass.jpg");
      final Icon rIcon = new ImageIcon("icons/road.jpg");

      final JButton tile = new JButton();

      if (mapArr[i / col][i % col] == 0)
        tile.setIcon(gIcon);
      else
        tile.setIcon(rIcon);

      // set an ID for each tile
      tile.putClientProperty("id", Integer.valueOf(i));

      tile.setBorderPainted(false);
      tile.setContentAreaFilled(false);
      tile.setFocusPainted(false);

      // action listener for tiles for changing them to path
      tile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          Object property = tile.getClientProperty("id");
          int id = ((Integer) property);

          if (mapArr[id / col][id % row] == 0) {
            tile.setIcon(rIcon);
            mapArr[id / col][id % row] = 1;
          } else {

            tile.setIcon(gIcon);
            mapArr[id / col][id % row] = 0;
          }

          // System.out.println(id/10+""+id%10);

        }
      });

      map.add(tile);
    }

    frame.setVisible(true);
  }

  public static void showMap(int[][] lArr) {
    int row = lArr.length;
    int col = lArr[0].length;

    final JFrame frame = new JFrame("saved map");
    JPanel map = new JPanel(new GridLayout(row, col, 0, 0));

    for (int i = 0; i < row * col; i++) {
      JButton tile = new JButton();
      tile.setContentAreaFilled(false);
      tile.setFocusPainted(false);
      tile.setOpaque(false);
      tile.setBorderPainted(false);

      if (lArr[i / col][i % row] == 1)
        tile.setIcon(new ImageIcon("icons/road.jpg"));
      else
        tile.setIcon(new ImageIcon("icons/grass.jpg"));

      map.add(tile);
    }
    frame.setContentPane(map);
    frame.setSize(400 * col / 10, 400 * row / 10);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    if (Methods.cntcheck(lArr))
      JOptionPane.showMessageDialog(frame, "the path has entrance/exit and it's connected");
    else
      JOptionPane.showMessageDialog(frame, "the path is not connected or has not entrance/exit point");
  }

}
