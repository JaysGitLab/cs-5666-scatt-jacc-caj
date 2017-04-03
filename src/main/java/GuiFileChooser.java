import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
public class GuiFileChooser implements FileChooser{

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
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(jFrame);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        } else {
            System.out.println("User clicked cancel");
        }
        jFrame.setVisible(false);
        jFrame.dispose();
        return file;
    }
}

