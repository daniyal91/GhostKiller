import java.awt.EventQueue;


public class GameController {

    public GameController() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

    public void startGame(){
        // TODO
    }

    public static void main(String[] args) {

        GameController gameController = new GameController();
        gameController.startGame();

    }

}
