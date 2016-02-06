import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameGrid;
import views.EditMapView;

public class EditMapController implements ActionListener {

    private EditMapView editMapView;

    public EditMapController(GameGrid gameGrid) {
        this.editMapView = new EditMapView(gameGrid, this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}
