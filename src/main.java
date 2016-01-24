
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class main {

  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Mf frame = new Mf();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });


    //for testing purpose  :  
    //Methods.mainWin();
    //int[][] lArr=Methods.read("file1.txt");
    //Methods.editMap(lArr);
    //Methods.showMap(lArr);

  }
}
