package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import misc.utils;
import model.GameGrid;
import model.GameGrid.CASE_TYPES;
import views.EditMapView;

public class EditMapController implements ActionListener, MouseListener {

    private EditMapView<EditMapController> editMapView;
    private GameGrid gameGrid;
    public CASE_TYPES selectedCaseType;

    public EditMapController(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.editMapView = new EditMapView<EditMapController>(gameGrid, this);
        this.selectedCaseType = CASE_TYPES.NONE;
    }

    private boolean saveMap() {

        if (this.gameGrid.isConnected()) {

            String filePath = utils.selectFile();
            if (filePath != null) {
                this.gameGrid.writeToFile(filePath);
                return true;
            }
        }

        else {
            this.editMapView.showMessage(
                            "Sorry, no connecting path between entry point and exit point!",
                            "Invalid map");
        }
        return false;

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == this.editMapView.saveButton && this.saveMap()) {
            this.editMapView.frame.dispose();
        } else {
            this.updateTile(event.getSource());
        }

    }

    private void updateTile(Object source) {

        for (int i = 0; i < this.gameGrid.cases.length; i++) {
            for (int j = 0; j < this.gameGrid.cases[0].length; j++) {
                if (source == this.editMapView.tiles[i][j]) {
                    this.toggleTile(i, j);
                }
            }
        }

    }

    private void toggleTile(int row, int column) {

        CASE_TYPES selectedCaseType = CASE_TYPES.NONE;

        // A special case type was selected, we place it on the grid.
        if (this.selectedCaseType != CASE_TYPES.NONE) {
            selectedCaseType = this.selectedCaseType;
            this.selectedCaseType = CASE_TYPES.NONE;

            // No special case type was selected, we will toggle between grass and road.
        } else {
            if (this.gameGrid.cases[row][column] == CASE_TYPES.ROAD) {
                selectedCaseType = CASE_TYPES.GRASS;
            } else {
                selectedCaseType = CASE_TYPES.ROAD;
            }
        }

        String iconPath = GameGrid.CASE_TYPES_ICON_PATHS[selectedCaseType.ordinal()];
        this.editMapView.tiles[row][column].setIcon(new ImageIcon(iconPath));
        this.gameGrid.cases[row][column] = selectedCaseType;

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == this.editMapView.startPointButton) {
            this.selectedCaseType = CASE_TYPES.START;
        } else if (event.getSource() == this.editMapView.endPointButton) {
            this.selectedCaseType = CASE_TYPES.END;
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
