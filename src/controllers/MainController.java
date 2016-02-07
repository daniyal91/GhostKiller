package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import misc.utils;
import model.Game;
import model.GameGrid;
import views.MainView;


public class MainController implements Runnable, ActionListener {

    private MainView mainFrame;

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
            EditMapController controller = new EditMapController(gameGrid);


        } else if (event.getSource() == this.mainFrame.buttonEdit) {

            String filePath = utils.selectFile();
            if (filePath != null) {

                GameGrid gameGrid = new GameGrid();
                gameGrid.readFromFile(filePath, false);
                EditMapController controller = new EditMapController(gameGrid);

            }

        } else if (event.getSource() == this.mainFrame.buttonLoad) {
            String filePath = utils.selectFile();
            if (filePath != null) {

                Game game = new Game();
                game.grid.readFromFile(filePath, true);
                GameController gameController = new GameController(game);

            }
        }
    }



}
