package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import misc.utils;
import model.Game;
import model.Path;
import model.GameGrid;
import views.MainView;

/**
 * Main controller for the game program. Will listen to action from user on the MainView, and choose what new view to
 * instantiate accordingly
 *
 * @author Team 6
 *
 */
public class MainController implements Runnable, ActionListener {

    private MainView mainFrame;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            this.mainFrame = new MainView(this);
            this.mainFrame.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * {@inheritDoc} Can execute one of the following 3 actions : * Launch the map editor with a new map; * Launch the
     * map editor with an existing map; * Launch the game with a selected map.
     */
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
              
                Path t=new Path(game.grid);
                System.out.println(t.pathList(t.gamegrid.connectivities()));
                System.out.println("xcoordinate :"+game.grid.entryPoint().xCoordinate);
                System.out.println("ycoordinate :"+game.grid.entryPoint().yCoordinate);
            }
        }
    }



}
