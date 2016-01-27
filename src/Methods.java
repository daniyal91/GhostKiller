import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.GameGrid;

public class Methods {

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
            public void actionPerformed(ActionEvent event) {
                // System.out.println("save button");
                String filename = textField.getText();
                GameGrid newGameGrid = new GameGrid();
                newGameGrid.setCases(mapArr);
                newGameGrid.writeToFile(filename);
            }

        });

        JPanel map = new JPanel(new GridLayout(row, col, 2, 2));
        mainPane.add(map, BorderLayout.CENTER);
        // inserting buttons (tiles)

        for (int i = 0; i < row * col; i++) {

            final Icon gIcon = new ImageIcon("icons/grass.jpg");
            final Icon rIcon = new ImageIcon("icons/road.jpg");

            final JButton tile = new JButton();

            if (mapArr[i / col][i % col] == 0) {
                tile.setIcon(gIcon);
            } else {
                tile.setIcon(rIcon);
            }

            // set an ID for each tile
            tile.putClientProperty("id", Integer.valueOf(i));

            tile.setBorderPainted(false);
            tile.setContentAreaFilled(false);
            tile.setFocusPainted(false);

            // action listener for tiles for changing them to path
            tile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {

                    Object property = tile.getClientProperty("id");
                    int id = ((Integer) property);

                    if (mapArr[id / col][id % col] == 0) {
                        tile.setIcon(rIcon);
                        mapArr[id / col][id % col] = 1;
                    } else {

                        tile.setIcon(gIcon);
                        mapArr[id / col][id % col] = 0;
                    }

                    // System.out.println(id/10+""+id%10);

                }
            });

            map.add(tile);
        }

        frame.setVisible(true);
    }

}
