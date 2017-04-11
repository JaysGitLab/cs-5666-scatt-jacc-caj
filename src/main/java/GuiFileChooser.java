import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Opens a GUI file chooser and allows user to select a directory.
 * @version 1
 * @author Erik Cole
 * @author Clint Hall
 * @author Chris Waldon
 */
public class GuiFileChooser implements FileChooser {

    /**
     * Gets a file from the file chooser.
     * @return The file chosen
     */
    @Override
    public File getDirectoryFromUser() {
        //Create and set up the window.
        JFrame jFrame = new JFrame("FileChooserDemo");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = fc.showOpenDialog(jFrame);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        } else {
            System.out.println("User clicked cancel");
        }
        jFrame.setVisible(false);
        jFrame.dispose();
        if (file != null && file.isFile()) {
	    file = file.getParentFile();
        }
        return file;
    }
}

