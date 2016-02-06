import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.GameGrid;
import views.EditMapView;
import views.MainView;


public class MainController implements Runnable, ActionListener {

    private MainView mainFrame;


    public static void main(String[] args) {

        MainController gameController = new MainController();
        EventQueue.invokeLater(gameController);

    }

    @Override
    public void run() {
        try {
            this.mainFrame = new MainView(this);
            this.mainFrame.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.mainFrame.buttonCreate) {

            String lineText = this.mainFrame.textFieldLines.getText();
            int lineCount = Integer.parseInt(lineText);
            String columnText = this.mainFrame.textFieldColumns.getText();
            int columnCount = Integer.parseInt(columnText);

            GameGrid gameGrid = new GameGrid(lineCount, columnCount);
            EditMapView mapView = new EditMapView(gameGrid);

        } else if (event.getSource() == this.mainFrame.buttonEdit) {

            String filePath = this.selectFile();
            if (filePath != null) {

                GameGrid gameGrid = new GameGrid();
                gameGrid.readFromFile(filePath, false);
                EditMapView mapView = new EditMapView(gameGrid);

            }

        } else if (event.getSource() == this.mainFrame.buttonLoad) {
            String filePath = this.selectFile();
            if (filePath != null) {

                GameGrid gameGrid = new GameGrid();
                gameGrid.readFromFile(filePath, true);
                GameController gameController = new GameController(gameGrid);

            }
        }
    }

    /**
     * Wrapper around JFileChooser.
     *
     * @return The path of the selected file, or null if no file selected.
     */
    private String selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        File currentDir = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(currentDir);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getName();
        } else {
            return null;
        }

    }

}
