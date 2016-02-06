package misc;

import java.io.File;

import javax.swing.JFileChooser;

public class utils {

    /**
     * Wrapper around JFileChooser.
     *
     * @return The path of the selected file, or null if no file selected.
     */
    public static String selectFile() {
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
