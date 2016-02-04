import java.awt.EventQueue;

import javax.swing.JFrame;


public class MainController implements Runnable {

    private JFrame mainFrame;


    public static void main(String[] args) {

        MainController gameController = new MainController();
        EventQueue.invokeLater(gameController);

    }

    @Override
    public void run() {
        try {
            this.mainFrame = new MainFrame();
            this.mainFrame.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
