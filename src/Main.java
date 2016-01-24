import java.awt.EventQueue;


public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        // for testing purpose :
        // Methods.mainWin();
        // int[][] lArr=Methods.read("file1.txt");
        // Methods.editMap(lArr);
        // Methods.showMap(lArr);

    }
}
